package ui.fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;
import pojo.ThemeHelper;


public class PreferenceFragment extends PreferenceFragmentCompat {

    private String themeOption;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);


        ListPreference themePreference = findPreference("themePref");

        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    themeOption = (String) newValue;
                    ThemeHelper.applyTheme(themeOption);
                    return true;
                }
            });
        }

        final ListPreference themePreferencecolor = findPreference("themeColor");
        if (themePreferencecolor != null) {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            final String themePref = sharedPreferences.getString("themeColor", null);

            themePreferencecolor.setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            String themeColor = (String) newValue;
                            if (!"dark".equals(themeOption) || !"default".equals(themeOption)) {

                                ThemeColorHelper.applyThemeColor(getActivity(), themeColor);

                                if (!themePref.equals(themeColor)) {
                                    getActivity().recreate();

                                }
                            }
                            return true;
                        }
                    }
            );
        }
        final PreferenceCategory category = findPreference("setting");
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                category.setVisible(false);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                category.setVisible(true);
                break;
        }
    }

}
