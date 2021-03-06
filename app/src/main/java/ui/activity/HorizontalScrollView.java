package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class HorizontalScrollView extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }


        setContentView(R.layout.horizontalscrollview);
        HorizontalScrollView.this.setTitle("Horizontal ScrollView");


    }
}
