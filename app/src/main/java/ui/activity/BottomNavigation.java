package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import pojo.ThemeColorHelper;
import ui.fragment.FragmentButton;
import ui.fragment.FragmentInputControl;
import ui.fragment.FragmentToastDialogSnackbar;

public class BottomNavigation extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment fragment = null;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.bottom_navigation);
        setTitle("Demo Task");
        fragment = new FragmentButton();
        FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
        tf.replace(R.id.fragment_container, fragment);
        tf.commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.demo_task:
                        fragment = new FragmentButton();
                        setTitle("Basic Layout");
                        break;
                    case R.id.input_control:
                        fragment = new FragmentInputControl();
                        setTitle("Input Control");
                        break;
                    case R.id.toast_control:
                        fragment = new FragmentToastDialogSnackbar();
                        setTitle("Toast,Dialog and SnackBar");
                        break;
                }

                FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
                tf.replace(R.id.fragment_container, fragment);
                tf.commit();
                return true;
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}