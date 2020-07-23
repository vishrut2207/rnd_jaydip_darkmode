package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import pojo.ThemeHelper;

public class DarkThemeApplication extends Application {
    private static final String TAG = "DarkThemeApplication";

    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE);

        Log.i(TAG, "onCreate: theme::" + themePref);
        if(themePref!=null)
        ThemeHelper.applyTheme(themePref);

    }
}
