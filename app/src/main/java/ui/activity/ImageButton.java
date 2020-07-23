package ui.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class ImageButton extends AppCompatActivity {
    @SuppressLint("SourceLockedOrientationActivity")
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.image_button);
        setTitle("Image Button");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void firstButton(View view)
    {
        Toast.makeText(getApplicationContext(),"You click First button ",Toast.LENGTH_SHORT).show();
    }
    public void secondButton(View view)
    {
        Toast.makeText(getApplicationContext(),"You click Second button ",Toast.LENGTH_SHORT).show();
    }
}