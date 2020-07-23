package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class Radiob extends AppCompatActivity {

    private RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12,r13,r14,r15;
    private TextView tv_display;
    RadioGroup rg2, rg3,rg4;
    Button btn;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.radio_button);
        setTitle("Radio Button");
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        r4 = (RadioButton) findViewById(R.id.r4);
        r5 = (RadioButton) findViewById(R.id.r5);
        r6 = (RadioButton) findViewById(R.id.r6);
        r7 = (RadioButton) findViewById(R.id.r7);
        r8 = (RadioButton) findViewById(R.id.r8);
        r9 = (RadioButton) findViewById(R.id.r9);
        r10 = (RadioButton) findViewById(R.id.r10);
        r11 = (RadioButton) findViewById(R.id.r11);
        r12 = (RadioButton) findViewById(R.id.r12);
        r13 = (RadioButton) findViewById(R.id.r13);
        r14 = (RadioButton) findViewById(R.id.r14);
        r15 = (RadioButton) findViewById(R.id.r15);
        rg4 = (RadioGroup)findViewById(R.id.rg4);
        rg2 = (RadioGroup)findViewById(R.id.rg2);
        rg3 = (RadioGroup)findViewById(R.id.rg3);
        btn = (Button) findViewById(R.id.btn_radio_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedRadio( v);
            }
        });
        tv_display = (TextView) findViewById(R.id.display);

        r7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg3.clearCheck();
                rg4.clearCheck();

            }
        });
        r8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg3.clearCheck();
                rg4.clearCheck();
            }
        });
        r9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg3.clearCheck();
                rg4.clearCheck();
            }
        });
        r10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg2.clearCheck();
                rg4.clearCheck();
            }
        });
        r11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg2.clearCheck();
                rg4.clearCheck();
            }
        });

        r12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg2.clearCheck();
                rg4.clearCheck();
         }
        });
        r13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg2.clearCheck();
                rg3.clearCheck();
            }
        });
        r14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg2.clearCheck();
                rg3.clearCheck();
            }
        });
        r15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg2.clearCheck();
                rg3.clearCheck();
            }
        });
    }

    public void checkedRadio(View v) {
        String display = "";
        if (r1.isChecked()) {
            display = display + r1.getText() + "\n";
        }
        if (r2.isChecked()) {
            display = display + r2.getText() + "\n";
        }
        if (r3.isChecked()) {
            display = display + r3.getText() + "\n";
        }
        if (r4.isChecked()) {
            display = display + r4.getText() + "\n";
        }
        if (r5.isChecked()) {
            display = display + r5.getText() + "\n";
        }
        if (r6.isChecked()) {
            display = display + r6.getText() + "\n";
        }
        if (r7.isChecked()) {
            display = display + r7.getText() + "\n";
        }
        if (r8.isChecked()) {
            display = display + r8.getText() + "\n";
        }
        if (r9.isChecked()) {
            display = display + r9.getText() + "\n";
        }
        if (r10.isChecked()) {
            display = display + r10.getText() + "\n";
        }
        if (r11.isChecked()) {
            display = display + r11.getText() + "\n";
        }
        if (r12.isChecked()) {
            display = display + r12.getText() + "\n";
        }
        if (r13.isChecked()) {
            display = display + r13.getText() + "\n";
        }
        if (r14.isChecked()) {
            display = display + r14.getText() + "\n";
        }
        if (r15.isChecked()) {
            display = display + r15.getText() + "\n";
        }
        tv_display.setText(display);
    }
}