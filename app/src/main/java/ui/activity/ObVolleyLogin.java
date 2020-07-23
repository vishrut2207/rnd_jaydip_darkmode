package ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.optimumbrew.library.core.volley.GsonRequest;
import com.optimumbrew.library.core.volley.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

import pojo.ThemeColorHelper;


public class ObVolleyLogin extends AppCompatActivity {

    TextView text_login;
    private static final String TAG = "ObVolleyLogin";
    EditText email, password;


    Button login;

    String api = "https://atmlocal.ngrok.io/ask_question_poll/api/public/api/loginForUser";

    String email_e, password_e;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean savelogin;
    String s;
    String n;
    LinearLayout sign_up;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @SuppressLint({"HardwareIds", "CommitPrefEdits", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.ob_api_login_demo);
        setTitle("Login Demo Using OBVolley");

        ActivityCompat.requestPermissions(ObVolleyLogin.this, new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.LOCATION_HARDWARE,
                Manifest.permission.READ_SMS}, 100);

        text_login = (TextView) findViewById(R.id.text_login_ob);

        s = getResources().getString(R.string.login_signUp_api);

        n = s + "/ask_question_poll/api/public/api/loginForUser";


        email = (EditText) findViewById(R.id.email_ob);
        password = (EditText) findViewById(R.id.password_ob);
        login = (Button) findViewById(R.id.login_ob);

        sign_up = (LinearLayout) findViewById(R.id.txt_sign_up_ob);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ObVolleyLogin.this, ObVolleyRegistrationUser.class);
                startActivity(i);
                finish();


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginn(v);

            }

        });
        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = preferences.edit();

        savelogin = preferences.getBoolean("isLogin", false);
        if (savelogin == true) {
            ObVolleyLogin.this.finish();
            Intent intent = new Intent(ObVolleyLogin.this, ApiLogOutActivity.class);
            startActivity(intent);
        }


    }


    @SuppressLint("HardwareIds")
    public void userLoginn(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

        }
        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Context.
                TELEPHONY_SERVICE);
        String deviceId = null;
        if (telephonyManager != null) {
            deviceId = telephonyManager.getDeviceId();
        }

        Log.i(TAG, "onCreate: id::;;;;" + deviceId);
        final ProgressDialog progressDialog = new ProgressDialog(ObVolleyLogin.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please wait...");

        email_e = email.getText().toString();
        password_e = password.getText().toString();

        if (!email.getText().toString().matches(emailPattern)) {
            email.setError("Invalid Email address");
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Please enter your password");
        } else if (password.getText().length() <= 5) {
            password.setError("Please set minimum 6-digit password");
        }  else {
            progressDialog.show();

            JSONObject JObj = new JSONObject();

            try {
                JObj.put("email_id", email_e);

                JObj.put("password", password_e);

                JSONObject device_info = new JSONObject();

                device_info.put("device_reg_id", "");
                device_info.put("device_platform", "");
                device_info.put("device_model_name", "");
                device_info.put("device_vendor_name", "");
                device_info.put("device_os_version", "");
                device_info.put("device_udid", deviceId);
                device_info.put("device_resolution", "");
                device_info.put("device_carrier", "");
                device_info.put("device_country_code", "");
                device_info.put("device_language", "");
                device_info.put("$device_local_code", "");
                device_info.put("device_default_time_zone", "");
                device_info.put("device_library_version", "");
                device_info.put("device_application_version", "");
                device_info.put("device_type", "");
                device_info.put("device_registration_date", "");
                device_info.put("is_active", "");


                JObj.put("device_info", device_info);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String xx = String.valueOf(JObj);

            Log.i(TAG, "userLogin: json::" + JObj);


            MyVolley.getInstance(ObVolleyLogin.this);
            Log.i(TAG, "onCreate: s::=" + n);
            GsonRequest<pojo.LoginResponse> gs = new GsonRequest<pojo.LoginResponse>(Request.Method.POST,
                    n,
                    xx,
                    pojo.LoginResponse.class,
                    null,
                    new Response.Listener<pojo.LoginResponse>() {
                @Override
                public void onResponse(pojo.LoginResponse response) {
                    progressDialog.dismiss();
                    Log.i(TAG, "onResponse: respone:::" + response.toString());

                    Log.i(TAG, "onResponse: success:::" + response.getMessage());

                    Toast.makeText(ObVolleyLogin.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                    String msg = response.getMessage();
                    if ("User login successfully".equals(msg)) {

                        Toast.makeText(ObVolleyLogin.this, "User login successfully.", Toast.LENGTH_SHORT).show();

                        editor = preferences.edit();
                        editor.putString("username", email_e);
                        editor.putBoolean("isLogin", true);
                        editor.apply();

                        Intent intent = new Intent(ObVolleyLogin.this, ApiLogOutActivity.class);
                        intent.putExtra("username", email_e);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ObVolleyLogin.this, "Invalid User Name And PassWord", Toast.LENGTH_SHORT).show();
                        password.getText().clear();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.i(TAG, "onErrorResponse: error::" + error);

                    NetworkResponse networkResponse = error.networkResponse;

                    if (networkResponse == null) {
                        String errorMessage = "Invalid User Name And PassWord";
                        if (error.getClass().equals(TimeoutError.class)) {
                            errorMessage = "Request timeout";
                        } else if (error.getClass().equals(NoConnectionError.class)) {
                            errorMessage = "Failed to connect server";
                        }
                        password.getText().clear();
                        Log.i("Error...", errorMessage);
                        Toast.makeText(ObVolleyLogin.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        String result = new String(networkResponse.data);
                        try {
                            String errorMessage = "Unknown error";
                            JSONObject response = new JSONObject(result);
                            String status = response.getString("status");
                            String message = response.getString("message");

                            Log.e("Error Status", status);
                            Log.e("Error Message", message);

                            if (networkResponse.statusCode == 404) {
                                errorMessage = "Resource not found";
                            } else if (networkResponse.statusCode == 401) {
                                errorMessage = message + " Please login again";
                            } else if (networkResponse.statusCode == 400) {
                                errorMessage = message + " Check your inputs";
                            } else if (networkResponse.statusCode == 500) {
                                errorMessage = message + " Something is getting wrong";
                            } else if (networkResponse.statusCode == 201) {
                                errorMessage = message + " Invalid User Name And PassWord";
                            }
                            Log.i("Error...", errorMessage);
                            Toast.makeText(ObVolleyLogin.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    error.printStackTrace();
                }

            });

            gs.setShouldCache(false);
            gs.setRetryPolicy(new DefaultRetryPolicy(60000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyVolley.getInstance(this).addToRequestQueue(gs);

        }
    }

    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }
}