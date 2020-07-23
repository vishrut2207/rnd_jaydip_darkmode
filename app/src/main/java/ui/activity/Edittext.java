package ui.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class Edittext extends AppCompatActivity {
    EditText name, phone_no, email,editText;
    TextView display;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button btn;

    @SuppressLint("SourceLockedOrientationActivity")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.edit_text);
        setTitle("Edit Text");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name = (EditText) findViewById(R.id.name);
        phone_no = (EditText) findViewById(R.id.phone_no);
        email = (EditText) findViewById(R.id.email);
        display = (TextView) findViewById(R.id.display);
        btn = (Button) findViewById(R.id.btn_button);

        btn.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("SetTextI18n")
            public void onClick(View view) {
               // boolean flag = true;
                if (name.getText().toString().isEmpty()) {
                    name.setError("Please Enter Valid Name");
                }

                if (phone_no.getText().length() <= 9) {
                    phone_no.setError("Please Enter Valid Number");
                }

                if (email.getText().toString().isEmpty()) {
                    email.setError("Enter Email Address");
                } else {
                    if (email.getText().toString().matches(emailPattern)) {

                    } else {
                        email.setError("Invalid email address");
                    }
                }
                if (name.getText().toString().isEmpty() || phone_no.getText().toString().isEmpty() ||
                        email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please fill all the Details",Toast.LENGTH_SHORT).show();
                } else {
                    String getName = name.getText().toString();
                    String getPhoneno = phone_no.getText().toString();
                    String getEmail = email.getText().toString();
                    display.setText("Name:" + getName + "\n" + "Phone no:" + getPhoneno + "\n" + "Email ID:" + getEmail);
                }

            }
        });

    }


}