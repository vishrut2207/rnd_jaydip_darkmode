package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import pojo.ThemeColorHelper;
import ui.adapter.MyAdapter;

public class ActionBarScrollableTab extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Fragment fragment = null;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        ThemeColorHelper.applyThemeColor(ActionBarScrollableTab.this, themePref);
        setContentView(R.layout.actionbar_scrollable_tab);
        setTitle("ActionBar Scrollable Tab");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tablayout1);

        tabLayout.addTab(tabLayout.newTab().setText("one"));
        tabLayout.addTab(tabLayout.newTab().setText("two"));
        tabLayout.addTab(tabLayout.newTab().setText("three"));
        tabLayout.addTab(tabLayout.newTab().setText("one"));
        tabLayout.addTab(tabLayout.newTab().setText("two"));
        tabLayout.addTab(tabLayout.newTab().setText("three"));
        tabLayout.addTab(tabLayout.newTab().setText("one"));

        viewPager = (ViewPager) findViewById(R.id.viewpage);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        MyAdapter adapter = new MyAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}