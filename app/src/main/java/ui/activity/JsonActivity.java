package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pojo.ThemeColorHelper;
import ui.adapter.JsonAdapter;

public class JsonActivity extends AppCompatActivity {

    private static final String TAG = JsonActivity.class.getSimpleName();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();

    ArrayList<String> nameuser1 = new ArrayList<>();
    ArrayList<String> strength1 = new ArrayList<>();
    ArrayList<String> dose1 = new ArrayList<>();
    ArrayList<String> route1 = new ArrayList<>();
    ArrayList<String> sig1 = new ArrayList<>();
    ArrayList<String> pillCount1 = new ArrayList<>();
    ArrayList<String> refills1 = new ArrayList<>();

    TextView textView;

    String json_string = "{ \"Labs\" :[{\"name\":\"Arterial Blood Gas\"," + "\"jay\":\"Arterial Blood Gas\"," + "\"time\":\"Today\"," + "\"location\":\"Main Hospital Lab\"}," +
            " {\n" +
            "      \"name\": \"BMP\",\n" +
            "      \"time\": \"Today\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"BNP\",\n" +
            "      \"time\": \"3 Weeks\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"BUN\",\n" +
            "      \"time\": \"1 Year\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Cardiac Enzymes\",\n" +
            "      \"time\": \"Today\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"CBC\",\n" +
            "      \"time\": \"1 Year\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Creatinine\",\n" +
            "      \"time\": \"1 Year\",\n" +
            "      \"location\": \"Main Hospital Lab\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Electrolyte Panel\",\n" +
            "      \"time\": \"1 Year\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Glucose\",\n" +
            "      \"time\": \"1 Year\",\n" +
            "      \"location\": \"Main Hospital Lab\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"PT/INR\",\n" +
            "      \"time\": \"3 Weeks\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"PTT\",\n" +
            "      \"time\": \"3 Weeks\",\n" +
            "      \"location\": \"Coumadin Clinic\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"TSH\",\n" +
            "      \"time\": \"1 Year\",\n" +
            "      \"location\": \"Primary Care Clinic\"\n" +
            "    }]}";

    String user = "{ \"aceInhibitors\":[{\"name\":\"lisinopril\",\n" +
            "                \"strength\":\"10 mg Tab\",\n" +
            "                \"dose\":\"1 tab\",\n" +
            "                \"route\":\"PO\",\n" +
            "                \"sig\":\"daily\",\n" +
            "                \"pillCount\":\"#90\",\n" +
            "                \"refills\":\"Refill 3\"\n" +
            "            }, " +
            "          {\n" +
            "          \"name\": \"nitroglycerin\",\n" +
            "          \"strength\": \"0.4 mg Sublingual Tab\",\n" +
            "          \"dose\": \"1 tab\",\n" +
            "          \"route\": \"SL\",\n" +
            "          \"sig\": \"q15min PRN\",\n" +
            "          \"pillCount\": \"#30\",\n" +
            "          \"refills\": \"Refill 1\"\n" +
            "        },{\n" +
            "          \"name\": \"warfarin sodium\",\n" +
            "          \"strength\": \"3 mg Tab\",\n" +
            "          \"dose\": \"1 tab\",\n" +
            "          \"route\": \"PO\",\n" +
            "          \"sig\": \"daily\",\n" +
            "          \"pillCount\": \"#90\",\n" +
            "          \"refills\": \"Refill 3\"\n" +
            "        }, {\n" +
            "          \"name\": \"metoprolol tartrate\",\n" +
            "          \"strength\": \"25 mg Tab\",\n" +
            "          \"dose\": \"1 tab\",\n" +
            "          \"route\": \"PO\",\n" +
            "          \"sig\": \"daily\",\n" +
            "          \"pillCount\": \"#90\",\n" +
            "          \"refills\": \"Refill 3\"\n" +
            "        }, {\n" +
            "          \"name\": \"furosemide\",\n" +
            "          \"strength\": \"40 mg Tab\",\n" +
            "          \"dose\": \"1 tab\",\n" +
            "          \"route\": \"PO\",\n" +
            "          \"sig\": \"daily\",\n" +
            "          \"pillCount\": \"#90\",\n" +
            "          \"refills\": \"Refill 3\"\n" +
            "        }, {\n" +
            "          \"name\": \"potassium chloride ER\",\n" +
            "          \"strength\": \"10 mEq Tab\",\n" +
            "          \"dose\": \"1 tab\",\n" +
            "          \"route\": \"PO\",\n" +
            "          \"sig\": \"daily\",\n" +
            "          \"pillCount\": \"#90\",\n" +
            "          \"refills\": \"Refill 3\"\n" +
            "        }, {\n" +
            "          \"name\": \"1111111111111\",\n" +
            "          \"strength\": \"40 mg Tab\",\n" +
            "          \"dose\": \"1 tab\",\n" +
            "          \"route\": \"PO\",\n" +
            "          \"sig\": \"daily\",\n" +
            "          \"pillCount\": \"#90\",\n" +
            "          \"refills\": \"Refill 3\"\n" +
            "        }]}";


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.recycleview_content_main);
        setTitle("Json Parsing");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewmain);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        try {

            JSONObject jsonRootObject = new JSONObject(json_string);
            JSONArray array = jsonRootObject.getJSONArray("Labs");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                Log.i(TAG, "onCreate: nameuser1 :- " + jsonObject);
                name.add(jsonObject.getString("name"));
                time.add(jsonObject.getString("time"));
                location.add(jsonObject.getString("location"));

            }


            JSONObject userobject = new JSONObject(user);
            JSONArray arrayuser = userobject.getJSONArray("aceInhibitors");
            int lab = array.length();
            int user = arrayuser.length();
            user = lab;
            Log.i(TAG, "onCreate: user:-" + user);

            for (int i = 0; i < arrayuser.length(); i++) {
                JSONObject jsonObject1 = arrayuser.getJSONObject(i);

                nameuser1.add(jsonObject1.getString("name"));
                strength1.add(jsonObject1.getString("strength"));
                dose1.add(jsonObject1.getString("dose"));
                route1.add(jsonObject1.getString("route"));
                sig1.add(jsonObject1.getString("sig"));
                pillCount1.add(jsonObject1.getString("pillCount"));
                refills1.add(jsonObject1.getString("refills"));

                Log.i(TAG, "onCreate: nameuser1 :- " + nameuser1.get(i));

            }
            for (int a = user; a > arrayuser.length(); a--) {
                Log.i(TAG, "onCreate: size:-" + a);

                nameuser1.add("no data");
                strength1.add("no data");
                dose1.add("no data");
                route1.add("no data");
                sig1.add("no data");
                pillCount1.add("no data");
                refills1.add("no data");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonAdapter jsonAdapter =
                new JsonAdapter(JsonActivity.this, name, time, location, nameuser1, strength1, dose1, route1, sig1, pillCount1, refills1);
        recyclerView.setAdapter(jsonAdapter);

    }

}