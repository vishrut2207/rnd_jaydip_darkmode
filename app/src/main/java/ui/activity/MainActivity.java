package ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import pojo.ThemeColorHelper;
import ui.fragment.FragmentButton;
import ui.fragment.FragmentDemoTask;
import ui.fragment.FragmentInputControl;
import ui.fragment.FragmentToastDialogSnackbar;
import ui.fragment.FragmnetNavigationActionMenu;
import ui.fragment.PreferenceFragment;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    Fragment fragment = null;
    private boolean exit = false;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.common_navigation_main);
        setTitle("Basic layout");
        drawerLayout = findViewById(R.id.drawerlayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigation = findViewById(R.id.nav_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.setting:
                        fragment = new PreferenceFragment();
                        setTitle("Basic layout");
                        i = 1;
                        break;
                    case R.id.basic_layout:
                        fragment = new FragmentButton();
                        setTitle("Basic layout");
                        break;
                    case R.id.input_control:
                        fragment = new FragmentInputControl();
                        setTitle("Input Control");
                        break;
                    case R.id.toast_control:
                        fragment = new FragmentToastDialogSnackbar();
                        setTitle("Toast,Dialog and SnackBar");
                        break;
                    case R.id.navigation_menu_floating:
                        fragment = new FragmnetNavigationActionMenu();
                        setTitle("Navigation");
                        break;
                    case R.id.demo_task:
                        fragment = new FragmentDemoTask();
                        setTitle("Demo task");
                        break;
                    case R.id.action_bar_fix_tab:
                        Intent intent = new Intent(MainActivity.this, ActionBarFixTab.class);
                        startActivity(intent);
                        break;
                    case R.id.action_bar_scrollable_tab:
                        Intent intent1 = new Intent(MainActivity.this, ActionBarScrollableTab.class);
                        startActivity(intent1);
                        break;
                    case R.id.image_load_using_glide:
                        Intent intent2 = new Intent(MainActivity.this, ImageLoadUsingGlide.class);
                        startActivity(intent2);
                        break;
                    case R.id.task_draweble:
                        Intent intent3 = new Intent(MainActivity.this, Drawable.class);
                        startActivity(intent3);
                        break;
                    case R.id.bottom_navigation:
                        Intent intent4 = new Intent(MainActivity.this, BottomNavigation.class);
                        startActivity(intent4);
                        break;
                    case R.id.bottom_sheet:
                        Intent intent5 = new Intent(MainActivity.this, BottomSheet.class);
                        startActivity(intent5);
                        break;
                    case R.id.cardview_nav:
                        Intent intent6 = new Intent(MainActivity.this, CardView.class);
                        startActivity(intent6);
                        break;
                    case R.id.slidet_main:
                        Intent intent7 = new Intent(MainActivity.this, SlideItHomeScreenDesign.class);
                        startActivity(intent7);
                        break;
                    case R.id.dynamic_list:
                        Intent intent8 = new Intent(MainActivity.this, Dynamic_list_view.class);
                        startActivity(intent8);
                        break;
                    case R.id.recyclerview:
                        Intent intent9 = new Intent(MainActivity.this, RecycleViewMainActivity.class);
                        startActivity(intent9);
                        break;
                    case R.id.nv_collapsing_toolbar:
                        Intent intent10 = new Intent(MainActivity.this, CollapsingToolbar.class);
                        startActivity(intent10);
                        break;
                    case R.id.nav_json:
                        Intent intent11 = new Intent(MainActivity.this, JsonActivity.class);
                        startActivity(intent11);
                        break;
                    case R.id.nav_gson_item_country:
                        Intent intent12 = new Intent(MainActivity.this, GsonCountryStateCity.class);
                        startActivity(intent12);
                        break;
                    case R.id.nav_gson_item_user_details:
                        Intent intent13 = new Intent(MainActivity.this, GsonEmployeeDetailMainActivity.class);
                        startActivity(intent13);
                        break;
                    case R.id.nv_sqlite_database:
                        Intent intent14 = new Intent(MainActivity.this, SQLiteDatabaseMainActivity.class);
                        startActivity(intent14);
                        break;
                    case R.id.nv_login_demo_api:
                        Intent intent15 = new Intent(MainActivity.this, ObVolleyLogin.class);
                        startActivity(intent15);
                        break;
                    case R.id.nv_image_demo_api:
                        Intent intent17 = new Intent(MainActivity.this, OBVolleyImageDemo.class);
                        startActivity(intent17);
                        break;
                    case R.id.nav_ob_volley_lib_post_api:
                        Intent intent16 = new Intent(MainActivity.this, OBVolleyVideoDemo.class);
                        startActivity(intent16);
                        break;

                    case R.id.nv_woc_design:
                        Intent intent18 = new Intent(MainActivity.this, WocDesignMainActivitiy.class);
                        startActivity(intent18);
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
        if (savedInstanceState != null) {

            String s = savedInstanceState.getString("fragment");
            if (s != null) {
                fragment = new PreferenceFragment();
                FragmentTransaction tf1 = getSupportFragmentManager().beginTransaction();
                tf1.replace(R.id.content_frame, fragment);
                tf1.commit();
            }
        } else {
            fragment = new FragmentButton();
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }

        }

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        if (i == 1) {
            outState.putString("fragment", "yes");
            super.onSaveInstanceState(outState, outPersistentState);
        }
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (exit) {
            super.onBackPressed();
            return;
        }
        this.exit = true;
        Toast.makeText(this, "Press Back again to Exit..", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 2000);
    }


    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_view_item, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        switch (id) {
            case R.id.setting:
                fragment = new PreferenceFragment();
                setTitle("Dark Theme");
                break;
            case R.id.basic_layout:
                fragment = new FragmentButton();
                break;
            case R.id.input_control:
                fragment = new FragmentInputControl();
                break;
            case R.id.toast_control:
                fragment = new FragmentToastDialogSnackbar();
                break;
            case R.id.navigation_menu_floating:
                fragment = new FragmnetNavigationActionMenu();
                break;
            case R.id.demo_task:
                fragment = new FragmentDemoTask();
                break;
            case R.id.action_bar_fix_tab:
                Intent intent = new Intent(MainActivity.this, ActionBarFixTab.class);
                startActivity(intent);
                break;
            case R.id.action_bar_scrollable_tab:
                Intent intent1 = new Intent(MainActivity.this, ActionBarScrollableTab.class);
                startActivity(intent1);
                break;
            case R.id.image_load_using_glide:
                Intent intent2 = new Intent(MainActivity.this, ImageLoadUsingGlide.class);
                startActivity(intent2);
                break;
            case R.id.task_draweble:
                Intent intent3 = new Intent(MainActivity.this, Drawable.class);
                startActivity(intent3);
                break;
            case R.id.bottom_navigation:
                Intent intent4 = new Intent(MainActivity.this, BottomNavigation.class);
                startActivity(intent4);
                break;
            case R.id.bottom_sheet:
                Intent intent5 = new Intent(MainActivity.this, BottomSheet.class);
                startActivity(intent5);
                break;
            case R.id.cardview_nav:
                Intent intent6 = new Intent(MainActivity.this, CardView.class);
                startActivity(intent6);
                break;
            case R.id.slidet_main:
                Intent intent7 = new Intent(MainActivity.this, SlideItHomeScreenDesign.class);
                startActivity(intent7);
                break;
            case R.id.dynamic_list:
                Intent intent8 = new Intent(MainActivity.this, Dynamic_list_view.class);
                startActivity(intent8);
                break;
            case R.id.recyclerview:
                Intent intent9 = new Intent(MainActivity.this, RecycleViewMainActivity.class);
                startActivity(intent9);
                break;
            case R.id.nv_collapsing_toolbar:
                Intent intent10 = new Intent(MainActivity.this, CollapsingToolbar.class);
                startActivity(intent10);
                break;
            case R.id.nav_json:
                Intent intent11 = new Intent(MainActivity.this, JsonActivity.class);
                startActivity(intent11);
                break;
            case R.id.nav_gson_item_country:
                Intent intent12 = new Intent(MainActivity.this, GsonCountryStateCity.class);
                startActivity(intent12);
                break;
            case R.id.nav_gson_item_user_details:
                Intent intent13 = new Intent(MainActivity.this, GsonEmployeeDetailMainActivity.class);
                startActivity(intent13);
                break;
            case R.id.nv_sqlite_database:
                Intent intent14 = new Intent(MainActivity.this, SQLiteDatabaseMainActivity.class);
                startActivity(intent14);
                break;
          /*  case R.id.nv_login_demo_api:
                Intent intent15 = new Intent(MainActivity.this, ApiLoginDemo.class);
                startActivity(intent15);
                break;*/
            case R.id.nav_ob_volley_lib_post_api:
                Intent intent16 = new Intent(MainActivity.this, ObVolleyApiDemo.class);
                startActivity(intent16);
                break;
         /*   case R.id.nv_image_demo_api:
                Intent intent17 = new Intent(MainActivity.this, ApiImageDemo.class);
                startActivity(intent17);
                break;*/
            case R.id.nv_woc_design:
                Intent intent18 = new Intent(MainActivity.this, WocDesignMainActivitiy.class);
                startActivity(intent18);
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


}
