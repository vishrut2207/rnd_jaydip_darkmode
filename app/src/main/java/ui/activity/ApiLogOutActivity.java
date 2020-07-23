package ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class ApiLogOutActivity extends AppCompatActivity {
    TextView name;
    Button logout_btn;
    SharedPreferences preferences;
    @SuppressLint("CommitPrefEdits")
    SharedPreferences.Editor editor;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.api_log_out_activity);

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = preferences.edit();

        name = (TextView) findViewById(R.id.username);
        logout_btn = (Button) findViewById(R.id.logout);

        String username = preferences.getString("username","");
        setTitle("User Profile");

        name.setText("Welcome "+username);


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.apply();
                finish();

                Intent intent = new Intent(ApiLogOutActivity.this, ObVolleyLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
