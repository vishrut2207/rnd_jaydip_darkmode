package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import pojo.EmployeeDetailsItem;
import pojo.Response;
import pojo.ThemeColorHelper;
import ui.adapter.EmployeeAdapter;

public class GsonEmployeeDetailMainActivity extends AppCompatActivity {
    private static final String TAG = GsonEmployeeDetailMainActivity.class.getSimpleName();
        EmployeeAdapter employeeAdapter;
        List<EmployeeDetailsItem> itemList = new ArrayList<>();
        private final static String json_file = "sample_json.json";
    RecyclerView recyclerView;


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
        setTitle("USer Details get Using Gson");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewmain);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        employeeAdapter = new EmployeeAdapter(GsonEmployeeDetailMainActivity.this, getEmployeeData());

        recyclerView.setAdapter(employeeAdapter);


    }

    public List<EmployeeDetailsItem> getEmployeeData() {
        String jsonString = getAssertJson(json_file);

        Gson gson = new Gson();

        Response response = gson.fromJson(jsonString, Response.class);
        Log.i(TAG, "getEmployeeData: gson File data:- " + response);

        return response.getEmployeeDetails();
    }

    public String getAssertJson(String fileName) {
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
