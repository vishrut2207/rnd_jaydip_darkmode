package ui.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pojo.ThemeColorHelper;

public class FloatinActionButton extends AppCompatActivity {
    FloatingActionButton plus, minus;
    int counter = 0;
    TextView text;
    Toast toast;
    private static final String STATE_COUNTER = "counter";

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.floating_button);
        setTitle("Floating Button");
        plus = (FloatingActionButton) findViewById(R.id.plus);
        minus = (FloatingActionButton) findViewById(R.id.minus);
        text = (TextView) findViewById(R.id.text_count);
        text.setText("0");
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(STATE_COUNTER, 0);
            text.setText(Integer.toString(counter));
        }
        plus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (v == plus) {
                    counter++;
                    text.setText(Integer.toString(counter));
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                counter--;
                if (counter < 0) {
                    counter = 0;
                    toast = Toast.makeText(getApplicationContext(), "No more decrement", Toast.LENGTH_SHORT);
                    toast.show();
                }
                text.setText(Integer.toString(counter));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_COUNTER, counter);
    }
}