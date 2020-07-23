package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class ButtonAndroid extends AppCompatActivity {
    Button simple;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }


        setContentView(R.layout.android_button);
        setTitle("Android Button");
        simple=(Button)findViewById(R.id.simple_btn);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Simple Android Button", Toast.LENGTH_SHORT).show();
           //    simple.setBackgroundColor(Color.GRAY);
            }
        });
    }


    public void leftIcon(View view) {
        Toast.makeText(getApplicationContext(), "Button with left icon", Toast.LENGTH_SHORT).show();
    }

    public void rightIcon(View view) {
        Toast.makeText(getApplicationContext(), "Button with right icon", Toast.LENGTH_SHORT).show();
    }

    public void backgroundImage(View view) {
        Toast.makeText(getApplicationContext(), "Button with Background Image", Toast.LENGTH_SHORT).show();
    }

    public void simpleBorder(View view) {
        Toast.makeText(getApplicationContext(), "Button with Border", Toast.LENGTH_SHORT).show();
    }

    public void radiusborder(View view) {
        Toast.makeText(getApplicationContext(), "Button with Radius border", Toast.LENGTH_SHORT).show();
    }

    public void roundBorder(View view) {
        Toast.makeText(getApplicationContext(), "Button with Round border", Toast.LENGTH_SHORT).show();
    }


}


