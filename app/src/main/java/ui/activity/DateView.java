package ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import java.util.Calendar;

import pojo.ThemeColorHelper;

public class DateView extends AppCompatActivity {
    EditText spinner_date, dialog_date;
    DatePicker datepicker;
    TextView text1;
    Button btn;
    int month, day, year;
    public static final String ORANGE = "orange";
    private static final String GREEN = "green";
    private static final String BLUR = "blue";
    private static final String RED = "red";
    int themeId;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        switch (themePref) {
            case ORANGE:
              themeId=R.style.o_DialogTheme;
                break;
            case GREEN:
                themeId=R.style.g_DialogTheme;
                break;
            case BLUR:
                themeId=R.style.b_DialogTheme;
                break;
            case RED:
                themeId=R.style.r_DialogTheme;
                break;
        }
        setContentView(R.layout.date_view);
        setTitle("Date View");
        dialog_date = (EditText) findViewById(R.id.dialog_date);
        spinner_date = (EditText) findViewById(R.id.spinner_date);
        datepicker = (DatePicker) findViewById(R.id.dp1);
        text1 = (TextView) findViewById(R.id.text1);
        btn = (Button) findViewById(R.id.btn);
        text1.setText(currentDate());
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                month = datepicker.getMonth();
                day = datepicker.getDayOfMonth();
                year = datepicker.getYear();
                text1.setText("Date  " + (month + 1) + "/" + day + "/" + year);
            }
        });

        final Calendar c = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                c.get(Calendar.YEAR);
                c.get(Calendar.MONTH);
                c.get(Calendar.DAY_OF_MONTH);
                dialog_date.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);


            }

        };
        dialog_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(DateView.this, themeId, date, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        spinner_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new DatePickerDialogSpinner();
                dialogfragment.show(getSupportFragmentManager(), "Theme");

            }
        });


    }


    public String currentDate() {
        StringBuilder currentDate = new StringBuilder();
        month = datepicker.getMonth() + 1;
        currentDate.append("Date: " + month + "/" + datepicker.getDayOfMonth() + "/" + datepicker.getYear());
        return currentDate.toString();
    }

    public static class DatePickerDialogSpinner extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public Dialog onCreateDialog(Bundle savedInsranceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.MyDialogTheme, this, year, month, day);
            TextView tv = new TextView(getActivity());

            datePickerDialog.setTitle("Spinner Dialog Date Picker");

            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            EditText editText = (EditText) getActivity().findViewById(R.id.spinner_date);
            month = (month + 1);
            editText.setText(month + "-" + day + "-" + year);
        }
    }


}

