package ui.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import ui.fragment.FragmentButton;
import ui.fragment.FragmentInputControl;

public class CommonNavigationDrawer extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment=new Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();
        setTitle("Navigation Drawer");
        setContentView(R.layout.navigation_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerToggle = new ActionBarDrawerToggle(CommonNavigationDrawer.this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigation = (NavigationView) findViewById(R.id.nav_view);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Fragment fragment = null;
                switch (id) {
                    case R.id.basic_layout:
                        fragment = new FragmentButton();

                        break;
                    case R.id.input_control:
                        fragment = new FragmentInputControl();

                        break;
                    case R.id.toast_control:
                        //  fragment = new FragmentToastDialogSnackbar();
                        break;
                    case R.id.navigation_menu_floating:
                        case R.id.demo_task:
                        break;
                    case R.id.action_bar_fix_tab:
                         break;
                }


                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_view_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.basic_layout:
                return true;
            case R.id.input_control:
                return true;
            case R.id.toast_control:
                return true;
            case R.id.navigation_menu_floating:
                return true;
            case R.id.demo_task:
                return true;
            case R.id.action_bar_fix_tab:
              return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}