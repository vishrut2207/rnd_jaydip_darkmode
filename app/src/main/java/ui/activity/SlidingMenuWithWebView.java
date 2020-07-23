package ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import pojo.ThemeColorHelper;


public class SlidingMenuWithWebView extends AppCompatActivity {
    WebView webView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    TextView text;
    View retry, progress_network;
    LinearLayout progress_layout;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    String url="https://www.youtube.com/";
    private static final String TAG = "SlidingMenuWithWebView";

    @SuppressLint({"SetJavaScriptEnabled", "CutPasteId"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.sliding_menu_with_web_view);
        setTitle("Sliding Menu With WebView");

        text = (TextView) findViewById(R.id.text);

        webView = (WebView) findViewById(R.id.web_View);

        retry = findViewById(R.id.network_layout);
        progress_layout = (LinearLayout) findViewById(R.id.progressBar_layout);
        final WebSettings webSettings = webView.getSettings();

        webSettings.setLoadsImagesAutomatically(true);

        webSettings.setJavaScriptEnabled(true);

//        webSettings.setPluginState(WebSettings.PluginState.ON);
        progressDialog = new ProgressDialog(SlidingMenuWithWebView.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progress_network = findViewById(R.id.progressBar_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//***********************   !!!!!!!

        if (!isConnection()) {
            progressDialog.dismiss();
            retry.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "internet is not available", Toast.LENGTH_SHORT).show();

            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    if (!isConnection()) {
                        Toast.makeText(getApplicationContext(), "internet is not available", Toast.LENGTH_SHORT).show();
                    }
                    if (isConnection()) {
                        webView.loadUrl("http://www.optimumbrew.com/");

                        progress_layout.setVisibility(View.VISIBLE);

                        retry.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        webView.setWebViewClient(new WebViewClient() {
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                view.loadUrl(url);
                                return true;
                            }

                            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                super.onPageStarted(view, url, favicon);

                            }

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);

                                retry.setVisibility(View.GONE);
                                setProgressBarIndeterminateVisibility(false);
                                progress_network.setVisibility(View.GONE);
                            }
                        });
                    }

                }
            });
        } else {
            progressBar.setVisibility(View.VISIBLE);
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl("http://www.optimumbrew.com/");

        }
        //***********************************

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });
        //********************************Sliding Drawer ********************
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout1);
        drawerToggle = new ActionBarDrawerToggle(SlidingMenuWithWebView.this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigation = (NavigationView) findViewById(R.id.nav_view1);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
//**********************************************************************************************

    public boolean isConnection() {
        ConnectivityManager connection = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connection != null) {
            NetworkInfo net = connection.getActiveNetworkInfo();
            if (net != null) {
                if (net.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_view_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
