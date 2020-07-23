package ui.activity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;


public class ToggleB extends AppCompatActivity {
    ToggleButton tb1, tb2, tb3, tb4, tb5;
    TextView text1, text2, text3, text4;
    WifiManager wifiManager;
    BluetoothAdapter adapter;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.toggle_button);
        setTitle("Toggle Button");
        tb1 = (ToggleButton) findViewById(R.id.tb1);
        tb2 = (ToggleButton) findViewById(R.id.tb2);
        tb3 = (ToggleButton) findViewById(R.id.tb3);
        tb4 = (ToggleButton) findViewById(R.id.tb4);
        tb5 = (ToggleButton) findViewById(R.id.tb5);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);

        wifiManager = (WifiManager) this.getApplicationContext().getSystemService(WIFI_SERVICE);

        adapter = BluetoothAdapter.getDefaultAdapter();
        tb1.setChecked(false);
        tb1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View view) {

                if (tb1.isChecked()) {
                    text1.setText("Toggle is on");
                } else {
                    text1.setText("Toggle is off");
                }
            }
        });

        if (tb1.isChecked()) {
            text1.setText("Toggle is on");
        } else {
            text1.setText("Toggle is off");
        }

        //**********************

        tb2.setChecked(true);
        tb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (tb2.isChecked()) {
                    text2.setText("Toggle is on");
                } else {
                    text2.setText("Toggle is off");
                }
            }
        });

        if (tb2.isChecked()) {
            text2.setText("Toggle is on");
        } else {
            text2.setText("Toggle is off");
        }

        //********************* WiFi

        if (wifiManager.isWifiEnabled()) {
            text3.setText("Wifi is on");
            tb3.setChecked(true);
        } else {
            text3.setText("Wifi is off");
            tb3.setChecked(false);
        }
        tb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    wifiManager.setWifiEnabled(true);
                    text3.setText("Wifi is on");
                } else {
                    wifiManager.setWifiEnabled(false);
                    text3.setText("Wifi is off");
                }
            }
        });


        //*******************Bluetooth


        tb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {

//                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (ischecked) {
                    text4.setText("Bluetooth is ON");
                    adapter.enable();
                } else {
                    adapter.disable();
                    text4.setText("Bluetooth is OFF");


                }
            }
        });
        try {
            if (adapter.isEnabled()) {
                tb4.setChecked(true);
                text4.setText("Bluetooth is ON");

            } else {
                tb4.setChecked(false);
                text4.setText("Bluetooth is OFF");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
