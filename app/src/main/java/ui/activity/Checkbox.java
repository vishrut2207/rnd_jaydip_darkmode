package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class Checkbox extends AppCompatActivity {
    CheckBox dance, cricket, football, basketball, cycling, writing, reading, php, android1, c, swimming, drawing, html;
    Button b11;
    TextView tv_display;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.checkbox);
        setTitle("Check Box");
        dance = (CheckBox) findViewById(R.id.c1);
        cricket = (CheckBox) findViewById(R.id.c2);
        football = (CheckBox) findViewById(R.id.c3);
        basketball = (CheckBox) findViewById(R.id.c4);
        cycling = (CheckBox) findViewById(R.id.c5);
        writing = (CheckBox) findViewById(R.id.c6);
        reading = (CheckBox) findViewById(R.id.c7);
        swimming = (CheckBox) findViewById(R.id.c8);
        drawing = (CheckBox) findViewById(R.id.c9);
        android1 = (CheckBox) findViewById(R.id.c10);
        php = (CheckBox) findViewById(R.id.c11);
        html = (CheckBox) findViewById(R.id.c12);
        c = (CheckBox) findViewById(R.id.c13);
        b11 = (Button) findViewById(R.id.bb);
        tv_display = (TextView) findViewById(R.id.display);
        tv_display.setOnClickListener(null);
        android1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                php.setChecked(false);
                android1.setChecked(true);
                html.setChecked(false);
                c.setChecked(false);
            }
        });

        php.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                android1.setChecked(false);
                html.setChecked(false);
                c.setChecked(false);
            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                android1.setChecked(false);
                php.setChecked(false);
                c.setChecked(false);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                android1.setChecked(false);
                php.setChecked(false);
                html.setChecked(false);

            }
        });
    }

    public void checked(View v) {
        String display = "";
        if (dance.isChecked()) {
            display = display + dance.getText() + "\n";
        }
        if (cricket.isChecked()) {
            display = display + cricket.getText() + "\n";
        }
        if (football.isChecked()) {
            display = display + football.getText() + "\n";
        }
        if (basketball.isChecked()) {
            display = display + basketball.getText() + "\n";
        }
        if (cycling.isChecked()) {
            display = display + cycling.getText() + "\n";
        }
        if (writing.isChecked()) {
            display = display + writing.getText() + "\n";
        }
        if (reading.isChecked()) {
            display = display + reading.getText() + "\n";
        }
        if (swimming.isChecked()) {
            display = display + swimming.getText() + "\n";
        }
        if (drawing.isChecked()) {
            display = display + drawing.getText() + "\n";
        }
        if (android1.isChecked()) {

            display = display + android1.getText() + "\n";
        }
        if (php.isChecked()) {

            display = display + php.getText() + "\n";
        }
        if (html.isChecked()) {

            display = display + html.getText() + "\n";
        }
        if (c.isChecked()) {

            display = display + c.getText() + "\n";
        }
        tv_display.setText(display);

    }

}



