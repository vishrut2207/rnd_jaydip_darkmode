package ui.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import pojo.EmployeeDetailsItem;
import pojo.Response;
import pojo.ThemeColorHelper;
import ui.adapter.EmployeeAdapter;


public class CollapsingToolbar extends AppCompatActivity {
    private static final String TAG = "CollapsingToolbar";

    CollapsingToolbarLayout collapsingToolbarLayout;
    EmployeeAdapter employeeAdapter;

    private final static String json_file = "sample_json.json";

    public static final String ORANGE = "orange";
    private static final String GREEN = "green";
    private static final String BLUR = "blue";
    private static final String RED = "red";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);
        switch (themePref) {
            case ORANGE:
                setTheme(R.style.Collasping_orange);
                break;
            case GREEN:
                setTheme(R.style.Collasping_green);
                break;
            case BLUR:
                setTheme(R.style.Collasping_blue);
                break;
            case RED:
                setTheme(R.style.Collasping_red);
                break;
        }

        setContentView(R.layout.collapsing_toolbar);
        RecyclerView recyclerView = findViewById(R.id.collasping_recyclerView);


        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Collapsing Toolbar");
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLUE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);


        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        employeeAdapter = new EmployeeAdapter(CollapsingToolbar.this, getEmployeeData());

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
