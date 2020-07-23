package ui.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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
import ui.fragment.FragmentSlideItem1;
import ui.fragment.FragmentSlideitem2;

import static android.view.Gravity.LEFT;

public class SlideItHomeScreenDesign extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    Fragment fragment = null;
    ImageView imageView, imgleft, upload, ic_contact, Ic_contact_select, ic_slideit, ic_slideit_select, ic_scan, ic_scan_select;
    public static final String ORANGE = "orange";
    private static final String GREEN = "green";
    private static final String BLUR = "blue";
    private static final String RED = "red";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);
        switch (themePref) {
            case ORANGE:
                setTheme(R.style.Collasping_orange);
                break;
            case GREEN:
                setTheme(R.style.Collasping_green);
                break;
            case BLUR:
                setTheme(R.style.Collasping_blue);
                break;
            case RED:
                setTheme(R.style.Collasping_red);
                break;
        }
        setContentView(R.layout.slideit_home_screen_design);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        drawerLayout = (DrawerLayout) findViewById(R.id.slideit_drawer);
        drawerToggle = new ActionBarDrawerToggle(SlideItHomeScreenDesign.this, drawerLayout, R.string.open, R.string.close) {
            public void onDrawerClosed(View drawerView) {
                imgleft.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                imageView.setVisibility(View.GONE);
                imgleft.setVisibility(View.VISIBLE);
                super.onDrawerOpened(drawerView);
            }

        };
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.slideit_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        imageView = (ImageView) findViewById(R.id.img_mnenu);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(LEFT);

                imageView.setVisibility(View.GONE);
                imgleft.setVisibility(View.VISIBLE);

            }
        });

        imgleft = (ImageView) findViewById(R.id.img_leftmenu);
        imgleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();

                imgleft.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);

            }
        });

        upload = (ImageView) findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SlideItHomeScreenDesign.this, "Upload", Toast.LENGTH_SHORT).show();

            }
        });
//************************     bottom navigation
        fragment = new FragmentSlideitem2();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.slideit_frame, fragment);
        ft.commit();

        ic_contact = (ImageView) findViewById(R.id.ic_contact);
        Ic_contact_select = (ImageView) findViewById(R.id.ic_contacts_select);
        ic_slideit = (ImageView) findViewById(R.id.ic_slideit);
        ic_slideit_select = (ImageView) findViewById(R.id.ic_slideit_select);
        ic_scan = (ImageView) findViewById(R.id.ic_scan);
        ic_scan_select = (ImageView) findViewById(R.id.ic_scan_select);

        ic_slideit_select.setVisibility(View.VISIBLE);
        ic_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new FragmentSlideItem1();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.slideit_frame, fragment);
                ft.commit();

                Ic_contact_select.setVisibility(View.VISIBLE);
                ic_scan.setVisibility(View.VISIBLE);
                ic_slideit.setVisibility(View.VISIBLE);

                ic_contact.setVisibility(View.GONE);
                ic_scan_select.setVisibility(View.GONE);
                ic_slideit_select.setVisibility(View.GONE);
            }
        });

        ic_slideit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new FragmentSlideitem2();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.slideit_frame, fragment);
                ft.commit();

                ic_slideit_select.setVisibility(View.VISIBLE);
                ic_contact.setVisibility(View.VISIBLE);
                ic_scan.setVisibility(View.VISIBLE);

                ic_slideit.setVisibility(View.GONE);
                Ic_contact_select.setVisibility(View.GONE);
                ic_scan_select.setVisibility(View.GONE);

            }
        });

        ic_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new FragmentSlideItem1();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.slideit_frame, fragment);
                ft.commit();

                ic_scan_select.setVisibility(View.VISIBLE);
                ic_contact.setVisibility(View.VISIBLE);
                ic_slideit.setVisibility(View.VISIBLE);

                ic_scan.setVisibility(View.GONE);
                Ic_contact_select.setVisibility(View.GONE);
                ic_slideit_select.setVisibility(View.GONE);
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

    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

}

