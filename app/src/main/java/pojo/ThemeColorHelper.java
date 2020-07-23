package pojo;

import android.app.Activity;
import android.util.Log;

import com.example.myapplication.R;


public class ThemeColorHelper {
    private static final String TAG = "ThemeColorHelper";
    public static final String ORANGE = "orange";
    private static final String GREEN = "green";
    private static final String BLUR = "blue";
    private static final String RED = "red";


    public static void applyThemeColor(Activity activity, String themeColor) {
        Log.i(TAG, "applyThemeColor: theme_color::-" + themeColor);
        switch (themeColor) {
            case ORANGE: {
                activity.setTheme(R.style.OrangeTheme);
                break;
            }
            case GREEN: {
                activity.setTheme(R.style.GreenTheme);

                break;
            }
            case BLUR: {
                activity.setTheme(R.style.BlueTheme);
                break;
            }
            case RED: {
                activity.setTheme(R.style.RedTheme);
                break;
            }
            default:
                activity.setTheme(R.style.AppTheme);
        }
    }
}
