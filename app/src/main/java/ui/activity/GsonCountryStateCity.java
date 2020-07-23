package ui.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pojo.ThemeColorHelper;

public class GsonCountryStateCity extends AppCompatActivity {
    Spinner sp_country, sp_city, sp_state;
    ArrayList<String> CountryNamelist;
    ArrayList<String> StateNamelist;
    ArrayList<String> CityNamelist;
    private final static String JSON_FILE_ANDROID_WEAR = "country_list.json";
    private static final String TAG = GsonCountryStateCity.class.getSimpleName();
    String state_name;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> city_adapter;
    JSONArray array;
    String[] nocity = {"No City Available"};
    int select_country, select_state, select_city = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.gson_country_state_city);
        setTitle("Country State City Json");
        CountryNamelist = new ArrayList<>();
        StateNamelist = new ArrayList<>();
        CityNamelist = new ArrayList<>();
        sp_country = (Spinner) findViewById(R.id.gson_country);
        sp_state = (Spinner) findViewById(R.id.goson_state);
        sp_city = (Spinner) findViewById(R.id.gson_city);
        try {
            JSONObject jsonObject = new JSONObject(getAssetsJSON(JSON_FILE_ANDROID_WEAR));
            array = jsonObject.getJSONArray("Countries");
            //******************************   Country   **********************************************************************************
            for (int i = 0; i < array.length(); i++) {

                final JSONObject object_country = array.getJSONObject(i);

                String country_name = object_country.getString("CountryName");
                CountryNamelist.add(country_name);

            }

            ArrayAdapter<String> adapter_country = new ArrayAdapter<String>(GsonCountryStateCity.this,
                    android.R.layout.simple_spinner_dropdown_item, CountryNamelist) {
                @Override
                public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View v = null;
                    v = super.getView(position, convertView, parent);

                    if (position == select_country) {

                        v.setBackgroundColor(Color.rgb(64, 134, 204));

                    } else {
                        v.setBackgroundColor(Color.TRANSPARENT);
                    }
                    return v;
                }
            };
            sp_country.setAdapter(adapter_country);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //************************************************* set state adapter  ***************************
        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StateNamelist.clear();


                try {

                    if (position >= 0) {

                        select_country = position;
                        Log.i(TAG, "onItemSelected: position:-" + select_country);
                        final JSONObject jsonnewobj = array.getJSONObject(position);
                        Log.i(TAG, "onItemSelected: jsonnewobj:-" + jsonnewobj);
                        final JSONArray arrayCountry = jsonnewobj.getJSONArray("States");   //246....

                        for (int a = 0; a < arrayCountry.length(); a++) {

                            JSONObject stateobject = arrayCountry.getJSONObject(a);
                            state_name = stateobject.getString("StateName");

                            StateNamelist.add(state_name);

                        }

                        adapter = new ArrayAdapter<String>(GsonCountryStateCity.this,
                                android.R.layout.simple_spinner_dropdown_item, StateNamelist) {
                            @Override
                            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View v = null;
                                v = super.getView(position, convertView, parent);
                                if (position == select_state) {

                                    v.setBackgroundColor(Color.rgb(64, 134, 204));

                                } else {
                                    v.setBackgroundColor(Color.TRANSPARENT);
                                }
                                return v;
                            }
                        };
                        sp_state.setAdapter(adapter);

//........................................................set city spinner.......................

                        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                select_state = position;

                                CityNamelist.clear();
                                try {

                                    if (position >= 0) {

                                        JSONObject cityobject = arrayCountry.getJSONObject(position);
                                        JSONArray array = cityobject.getJSONArray("Cities");
                                        for (int b = 0; b < array.length(); b++) {

                                            CityNamelist.add(array.getString(b));
                                            Log.i(TAG, "onItemSelected: city_name:-" + array.length());

                                        }
                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GsonCountryStateCity.this,
                                                android.R.layout.simple_spinner_dropdown_item, CityNamelist) {
                                            @Override
                                            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                                View v = null;
                                                v = super.getView(position, convertView, parent);
                                                if (position == select_city) {

                                                    v.setBackgroundColor(Color.rgb(64, 134, 204));
                                                } else {

                                                    v.setBackgroundColor(Color.TRANSPARENT);
                                                }
                                                return v;
                                            }
                                        };

                                        sp_city.setAdapter(adapter);
//................................................. CITY SPINNER ...................................
                                        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                select_city = position;
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                        if (adapter.isEmpty()) {
                                            city_adapter = new ArrayAdapter<String>(GsonCountryStateCity.this,
                                                    android.R.layout.simple_spinner_dropdown_item, nocity);

                                            sp_city.setAdapter(city_adapter);
                                        }
// ............................................... END ..........................................................
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }


                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}

