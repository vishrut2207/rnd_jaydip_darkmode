package ui.activity;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import pojo.ThemeColorHelper;


public class SnackbarControl extends AppCompatActivity {
    Button default_snackbar, click_enent_snackbar, custom_snackbar;


    @SuppressLint("SourceLockedOrientationActivity")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.snackbar_control);
        setTitle("SnackBar control");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        default_snackbar = (Button) findViewById(R.id.default_snackbar);
        click_enent_snackbar = (Button) findViewById(R.id.click_enent_snackbar);
        custom_snackbar = (Button) findViewById(R.id.custom_snackbar);
        default_snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout rootlaout = (LinearLayout) findViewById(R.id.rootlaout);
                Snackbar snackbar = Snackbar.make(rootlaout, "Hello...!!!  Default Snackbar example", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        click_enent_snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout rootlaout = (LinearLayout) findViewById(R.id.rootlaout);
                Snackbar snackbar = Snackbar.make(rootlaout, "Hello Click me...!!!", Snackbar.LENGTH_INDEFINITE);
                snackbar.setActionTextColor(Color.parseColor("#FFFFEE19"));
                snackbar.setAction("DISSMISS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                snackbar.show();
            }
        });

    }

    public void click(View view) {
       // final View layout = findViewById(R.id.btn_layout);
        final View viewPos = findViewById(R.id.myCoordinatorLayout);
        final Snackbar snackbar = Snackbar.make(viewPos, "Hello Custom SnackBar...!!!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("DISSMISS", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        snackbar.setActionTextColor(Color.parseColor("#FFFFEE19"));
        snackbar.show();
    }
}


