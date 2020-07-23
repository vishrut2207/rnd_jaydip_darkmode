package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class Drawable extends AppCompatActivity implements Animation.AnimationListener {
    String[] list = {"Zoom", "Blink", "fade"};
    ArrayAdapter<String> adapter;
    Spinner spinner;
    int pos = 0;
    Animation zoomin, fade, blink;
    ImageView imageView;
    TextView nine;
    EditText edittext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.drawable_image);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Drawable");
        spinner = (Spinner) findViewById(R.id.spinner_d);
        imageView = (ImageView) findViewById(R.id.img1);
        nine = (TextView) findViewById(R.id.nine);
        edittext = (EditText) findViewById(R.id.edittext);

//        nine.setText(edittext.getText().toString());
        TextWatcher watcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {
                nine.setText(edittext.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        };

        edittext.addTextChangedListener(watcher);
        zoomin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);

        zoomin.setAnimationListener((Animation.AnimationListener) this);
        blink.setAnimationListener((Animation.AnimationListener) this);
        fade.setAnimationListener((Animation.AnimationListener) this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;

                if (position == 0) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.startAnimation(zoomin);

                }
                if (position == 1) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.startAnimation(blink);
                }
                if (position == 2) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.startAnimation(fade);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
