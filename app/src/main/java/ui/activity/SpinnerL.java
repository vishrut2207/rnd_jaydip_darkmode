package ui.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;


public class    SpinnerL extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1, s2, s3, s4;
    TextView text1, text2;
    Button btn;
    String[] country = {"India", "Australia"};
    String[] india_state = {"Gujarat", "Bihar"};
    String[] gujarat_city = {"Surat", "Bharuch", "Rajkot", "Vadodara"};
    String[] bihar_city = {"Patna", "Goya"};
    String[] australia_state = {"Queensland", "SouthAustralia"};
    String[] queensland_city = {"Brisbane", "Cairns"};
    String[] southAustralia_city = {"Adelaide", "Portvictoria"};
    int countryiteme, indiastates, gujratstates, biharstates, queenlandstates, southaustraliastates, australiastates = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.spinner);
        setTitle("Spinner");
        s1 = (Spinner) findViewById(R.id.s1);
        s2 = (Spinner) findViewById(R.id.s2);
        s3 = (Spinner) findViewById(R.id.s3);
        s4 = (Spinner) findViewById(R.id.s4);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        btn = (Button) findViewById(R.id.btn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, country) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getView(position, convertView, parent);
                if (position == countryiteme) {
                    v.setBackgroundColor(Color.rgb(64, 134, 204));

                } else {
                    v.setBackgroundColor(Color.TRANSPARENT);

                }
                return v;
            }
        };

        s2.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        s3.setOnItemSelectedListener(this);
        s4.setOnItemSelectedListener(this);

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryiteme = position;

                //************    india states  *************
                if (position == 0) {
                    ArrayAdapter<String> india_state1 = new ArrayAdapter<String>(SpinnerL.this,
                            android.R.layout.simple_spinner_dropdown_item, india_state) {
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View v = null;
                            v = super.getView(position, convertView, parent);
                            if (position == indiastates) {
                                v.setBackgroundColor(Color.rgb(64, 134, 204));

                            } else {
                                v.setBackgroundColor(Color.TRANSPARENT);

                            }
                            return v;
                        }
                    };

                    s3.setAdapter(india_state1);
                    s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            indiastates = position;
//**********************   gujarat ************************
                            if (position == 0) {

                                ArrayAdapter<String> gujarat_city1 = new ArrayAdapter<String>(SpinnerL.this,
                                        android.R.layout.simple_spinner_dropdown_item, gujarat_city) {
                                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                        View v = null;

                                        v = super.getView(position, convertView, parent);
                                        if (position == gujratstates) {
                                            v.setBackgroundColor(Color.rgb(64, 134, 204));

                                        } else {
                                            v.setBackgroundColor(Color.TRANSPARENT);

                                        }
                                        return v;
                                    }
                                };
                                s4.setAdapter(gujarat_city1);
                                s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        gujratstates = position;
                                        if (position == 0) {

                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });

                            }
//**********************  bihar  *************************************
                            if (position == 1) {
                                final ArrayAdapter<String> bihar_city1 = new ArrayAdapter<String>(SpinnerL.this, android.R.layout.simple_spinner_dropdown_item, bihar_city) {
                                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                        View v = null;

                                        v = super.getView(position, convertView, parent);
                                        if (position == biharstates) {
                                            v.setBackgroundColor(Color.rgb(64, 134, 204));

                                        } else {
                                            v.setBackgroundColor(Color.TRANSPARENT);

                                        }
                                        return v;
                                    }
                                };
                                s4.setAdapter(bihar_city1);
                                s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        biharstates = position;
                                        if (position == 0) {

                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                //********************** Australia states **************
                if (position == 1) {
                    ArrayAdapter<String> australia_state1 = new ArrayAdapter<String>(SpinnerL.this,
                            android.R.layout.simple_spinner_dropdown_item, australia_state) {
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View v = null;
                            v = super.getView(position, convertView, parent);
                            if (position == australiastates) {
                                v.setBackgroundColor(Color.rgb(64, 134, 204));

                            } else {
                                v.setBackgroundColor(Color.TRANSPARENT);

                            }
                            return v;
                        }
                    };
                    s3.setAdapter(australia_state1);
                    s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            australiastates = position;
                            //****************************     queensland    **************
                            if (position == 0) {
                                ArrayAdapter<String> queensland_city1 = new ArrayAdapter<String>(SpinnerL.this,
                                        android.R.layout.simple_spinner_dropdown_item, queensland_city) {
                                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                        View v = null;
                                        v = super.getView(position, convertView, parent);
                                        if (position == queenlandstates) {
                                            v.setBackgroundColor(Color.rgb(64, 134, 204));

                                        } else {
                                            v.setBackgroundColor(Color.TRANSPARENT);

                                        }
                                        return v;
                                    }
                                };
                                s4.setAdapter(queensland_city1);
                                s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        queenlandstates = position;
                                        if (position == 0) {

                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                            // **************************  southAustralia  ****************
                            if (position == 1) {
                                ArrayAdapter<String> southAustralia_city1 = new ArrayAdapter<String>(SpinnerL.this,
                                        android.R.layout.simple_spinner_dropdown_item, southAustralia_city) {
                                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                        View v = null;
                                        v = super.getView(position, convertView, parent);
                                        if (position == southaustraliastates) {
                                            v.setBackgroundColor(Color.rgb(64, 134, 204));

                                        } else {
                                            v.setBackgroundColor(Color.TRANSPARENT);

                                        }
                                        return v;
                                    }
                                };
                                s4.setAdapter(southAustralia_city1);
                                s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        southaustraliastates = position;
                                        if (position == 0) {

                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String spin = "";
                        String country = String.valueOf(s2.getSelectedItem());
                        String states = String.valueOf(s3.getSelectedItem());
                        String city = String.valueOf(s4.getSelectedItem());
                        if (country.equals(String.valueOf(s2.getSelectedItem()))) {
                            spin = spin + s2.getSelectedItem().toString() + "->";
                        }
                        if (states.equals(String.valueOf(s3.getSelectedItem()))) {
                            spin = spin + s3.getSelectedItem().toString() + "->";
                        }
                        if (city.equals(String.valueOf(s4.getSelectedItem()))) {

                            spin = spin + s4.getSelectedItem().toString();
                        }
                        text2.setText(spin);
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        //**********  spinner 1
        String firstItem = String.valueOf(s1.getSelectedItem());
        if (firstItem.equals(String.valueOf(s1.getSelectedItem()))) {
            String spin = s1.getSelectedItem().toString();
            text1.setText("you have selected : " + spin);
            setProgressBarVisibility(true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg) {

    }

    //*************************


}