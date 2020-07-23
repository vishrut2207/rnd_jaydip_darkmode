package ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import java.util.Calendar;

import pojo.ThemeColorHelper;

import static java.util.Calendar.AM;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.PM;

public class TimeView extends AppCompatActivity {
    TimePicker timepicker;
    TextView displaytime, newformate;
    EditText edittext;
    Button btn;
    String format = "";
    int hour;
    int min, AMPM;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.time_view);
        setTitle("Time View");
        timepicker = (TimePicker) findViewById(R.id.time1);

        displaytime = (TextView) findViewById(R.id.text1);
        newformate = (TextView) findViewById(R.id.format24);
        edittext = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.btn);
        Calendar c = Calendar.getInstance();
        hour = c.get(HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        AMPM = c.get(Calendar.AM_PM);
    }

    public void editTextSet(View view) {


        TimePickerDialog timePickerDialog = new TimePickerDialog(TimeView.this, AlertDialog.THEME_HOLO_DARK,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        hour = hourOfDay;
                        String ampn;
                        if (hourOfDay < 12) {
                            ampn = "AM";

                        } else {
                            ampn = "PM";
                            hour = hour - 12;
                        }
                        edittext.setText(new StringBuilder().append("12 Hour Format:").append(hour).append(":")
                                .append(minute).append(":").append(ampn).append("\n")
                                .append("24 Hour Format:").append(hourOfDay).append(":").append(minute));
                    }

                }, hour, min, true);

        timePickerDialog.show();
    }


    public void setTime(View view) {
        hour = timepicker.getCurrentHour();
        min = timepicker.getCurrentMinute();
        showTime(hour, min);
        format24(hour, min);
    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        displaytime.setText(new StringBuilder().append("12 Hour Format:").append(hour).append(":").append(min).append(":").append(format));

    }

    public void format24(int hour, int min) {
        Calendar c = Calendar.getInstance();
        int AMPM = c.get(Calendar.AM_PM);
        if (AMPM == AM && hour == 12) {
            hour -= 12;
        }
        if (AMPM == PM && hour < 12) {
            hour += 12;
        }
        newformate.setText(new StringBuilder().append("24 Hour Format:").append(hour).append(":").append(min));

    }
}
