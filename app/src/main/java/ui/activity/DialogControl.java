package ui.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;


public class DialogControl extends AppCompatActivity {
    Button singal, multi_dialog, custom_dialog, option_dialog;
    TextView text;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.alert_dialog);
        setTitle("Alert Dialog");
        singal = (Button) findViewById(R.id.single_choice_dialog);
        multi_dialog = (Button) findViewById(R.id.multi_choice_dialog);
        custom_dialog = (Button) findViewById(R.id.custom_dialog);
        option_dialog = (Button) findViewById(R.id.option_dialog);
        text = (TextView) findViewById(R.id.text_custom);
    }

    //**********************  Simple Dialog
    public void open(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Do you want to close this Dialog ?");
        dialog.setTitle("Single Choice Alert Dialog");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You Clicked OK", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    //********************************** MultiChoice
    public void multiChoice(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Do you want to close this Dialog ?");
        dialogBuilder.setTitle("Multi Choice Alert Dialog");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You Clicked OK", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You Clicked CANCEL", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNeutralButton("NEUTRAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You Clicked NEUTRAL", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    //******************************************** Custom Dialog

    public void customDialog(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(DialogControl.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogview = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_alert_dialog, viewGroup, false);
        Button yes = dialogview.findViewById(R.id.btn_1);
        Button no = dialogview.findViewById(R.id.btn_2);
        Button cancel_btn = dialogview.findViewById(R.id.cancel_btn);
        builder.setView(dialogview);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "purchase To unlock all catelog", Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "WATCH A VIDEO AD TO GET THESE THEME", Toast.LENGTH_SHORT).show();
            }
        });
        final AlertDialog alertDialog = builder.create();
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        SpannableStringBuilder button_1_span = new SpannableStringBuilder("PURCHASE\nTO UNLOCK ALL CATALOG");
        button_1_span.setSpan(new StyleSpan(Typeface.BOLD), 0, 8, SPAN_EXCLUSIVE_EXCLUSIVE);
        button_1_span.setSpan(new AbsoluteSizeSpan(17, true), 0, 8, SPAN_EXCLUSIVE_EXCLUSIVE);
        button_1_span.setSpan(new AbsoluteSizeSpan(11, true), 9, 30, SPAN_EXCLUSIVE_EXCLUSIVE);
        // button_1_span.setSpan(new ForegroundColorSpan(Color.parseColor("#0000")),9,30,SPAN_EXCLUSIVE_EXCLUSIVE);
        button_1_span.length();
        ((Button) dialogview.findViewById(R.id.btn_1)).append(button_1_span);
        //**********************
        SpannableStringBuilder button_2_span = new SpannableStringBuilder("WATCH A VIDEO AD\nTO GET THESE THEME");
        button_2_span.setSpan(new StyleSpan(Typeface.BOLD), 0, 16, SPAN_EXCLUSIVE_EXCLUSIVE);
        button_2_span.setSpan(new AbsoluteSizeSpan(17, true), 0, 16, SPAN_EXCLUSIVE_EXCLUSIVE);

        button_2_span.setSpan(new AbsoluteSizeSpan(11, true), 16, 35, SPAN_EXCLUSIVE_EXCLUSIVE);
        button_2_span.length();
        ((Button) dialogview.findViewById(R.id.btn_2)).append(button_2_span);
        //**********************
        SpannableStringBuilder text = new SpannableStringBuilder("*Note: You will only get 1 PRO theme per day");
        text.setSpan(new ForegroundColorSpan(Color.RED), 0, 6,
                SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(Color.BLUE), 25, 30,
                SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new AbsoluteSizeSpan(12, true), 0, 44, SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new StyleSpan(Typeface.BOLD), 25, 30, SPAN_EXCLUSIVE_EXCLUSIVE);
        text.length();
        ((TextView) dialogview.findViewById(R.id.text_custom)).append(text);
        //************************

        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    //***********************************************    Option Dialog
    public void optionDialog(final View view) {
        final String[] color = {"Red", "Green", "Yellow", "Pink", "Black", "Blue", "Orange", "White", "Red", "Green", "Yellow", "Pink", "Black"
                , "Red", "Green", "Yellow", "Pink", "Black", "Blue", "Orange", "White", "Red", "Green", "Yellow", "Pink", "Black"};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Option Dialog");
        dialogBuilder.setItems(color, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(DialogControl.this, color[which], Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
