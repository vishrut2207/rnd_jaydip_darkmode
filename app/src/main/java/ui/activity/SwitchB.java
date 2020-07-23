package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class SwitchB extends AppCompatActivity {
    TextView d1, d2, d3, d4, d5, d6;
    Switch s1, s2, s3, s4, s5, s6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setTitle("Switch Button");
        setContentView(R.layout.switch_button);
        d1 = (TextView) findViewById(R.id.text1);
        s1 = (Switch) findViewById(R.id.s1);
        s1.setChecked(false);
        s1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (s1.isChecked()) {

                    d1.setText("Switch On");

                } else {
                    d1.setText("Switch Off");

                }

            }
        });
        if (s1.isChecked()) {
            d1.setText("Switch  On");
        } else {
            d1.setText("Switch  Off");
        }


        //.....................


        d2 = (TextView) findViewById(R.id.text2);
        s2 = (Switch) findViewById(R.id.s2);
        s2.setChecked(true);
        s2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (s2.isChecked()) {
                    d2.setText("Switch On");

                } else {
                    d2.setText("Switch Off");
                }

            }
        });
        if (s2.isChecked()) {
            d2.setText("Switch  On");
        } else {
            d2.setText("Switch  Off");
        }


        //**************************


        d3 = (TextView) findViewById(R.id.text3);
        s3 = (Switch) findViewById(R.id.s3);
        s3.setChecked(false);
        s3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (s3.isChecked()) {
                    d3.setText("Switch 1:On");

                } else {
                    d3.setText("Switch 1:Off");
                }

            }
        });
        if (s3.isChecked()) {
            d3.setText("Switch 1:On");
        } else {
            d3.setText("Switch 1:Off");
        }


        //**************************


        d4 = (TextView) findViewById(R.id.text4);
        s4 = (Switch) findViewById(R.id.s4);
        s4.setChecked(false);
        s4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (s4.isChecked()) {
                    d4.setText("Switch 2:On");

                } else {
                    d4.setText("Switch 2:Off");
                }

            }
        });
        if (s4.isChecked()) {
            d4.setText("Switch 2:On");
        } else {
            d4.setText("Switch 2:Off");
        }


        //**************************


        d5 = (TextView) findViewById(R.id.text5);
        s5 = (Switch) findViewById(R.id.s5);
        s5.setChecked(true);
        s5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (s5.isChecked()) {
                    d5.setText("Switch 3:On");

                } else {
                    d5.setText("Switch 3:Off");
                }

            }
        });
        if (s5.isChecked()) {
            d5.setText("Switch 3:On");
        } else {
            d5.setText("Switch 3:Off");
        }


        //**************************


        d6 = (TextView) findViewById(R.id.text6);
        s6 = (Switch) findViewById(R.id.s6);
        s6.setChecked(false);
        s6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (s6.isChecked()) {
                    d6.setText("Switch 4:On");

                } else {
                    d6.setText("Switch 4:Off");
                }

            }
        });
        if (s6.isChecked()) {
            d6.setText("Switch 4:On");
        } else {
            d6.setText("Switch 4:Off");
        }
    }
}