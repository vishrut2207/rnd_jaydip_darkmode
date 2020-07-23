package ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;


public class ApiLoginDemo extends AppCompatActivity {

    private static final String TAG = "ApiLoginDemo";
    EditText email, password;
    LinearLayout sign_up;

    Button login;
    String api = "https://ed4144410abd.ngrok.io/ask_question_poll/api/public/api/doLogin";
    String email_e, password_e;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean savelogin;

    @SuppressLint("CommitPrefEdits")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_login_demo);
        setTitle("Login User");

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        sign_up = (LinearLayout) findViewById(R.id.txt_sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApiLoginDemo.this, ApiRegistrationUser.class);
                startActivity(i);


            }
        });

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = preferences.edit();

        savelogin = preferences.getBoolean("isLogin", false);
        if (savelogin == true) {
            ApiLoginDemo.this.finish();
            Intent intent = new Intent(ApiLoginDemo.this, ApiLogOutActivity.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    public void userLogin() throws JSONException {

        final ProgressDialog progressDialog = new ProgressDialog(ApiLoginDemo.this);
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
        } else {
            progressDialog.show();

            JSONObject JObj = new JSONObject();

            JObj.put("email_id", email_e);
            JObj.put("password", password_e);

            Log.i(TAG, "userLogin: json::" + JObj);
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, api, JObj, new Response.Listener<JSONObject>() {
                @SuppressLint("CommitPrefEdits")
                @Override
                public void onResponse(JSONObject response) {

                    progressDialog.dismiss();

                    Log.i(TAG, "onResponse: response::" + response);
                    try {

                        JSONObject object = new JSONObject(String.valueOf(response));
                        Log.i(TAG, "onResponse: response::" + object.getString("message"));
                        String msg = object.getString("message");

//                        Toast.makeText(ApiLoginDemo.this, object.getString("message"), Toast.LENGTH_SHORT).show();


                        if ("User login successfully.".equals(msg)) {

                            Toast.makeText(ApiLoginDemo.this, "User login successfully.", Toast.LENGTH_SHORT).show();

                            editor = preferences.edit();
                            editor.putString("username", email_e);
                            editor.putBoolean("isLogin", true);
                            editor.apply();

                            Intent intent = new Intent(ApiLoginDemo.this, ApiLogOutActivity.class);
                            intent.putExtra("username", email_e);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ApiLoginDemo.this, "Invalid User Name And PassWord", Toast.LENGTH_SHORT).show();
//                            password.setText("");

                            password.getText().clear();

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    NetworkResponse networkResponse = error.networkResponse;
                    String errorMessage = "Unknown error";
                    if (networkResponse == null) {
                        if (error.getClass().equals(TimeoutError.class)) {
                            errorMessage = "Request timeout";
                        } else if (error.getClass().equals(NoConnectionError.class)) {
                            errorMessage = "Failed to connect server";
                        }
                    } else {
                        String result = new String(networkResponse.data);
                        try {
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
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("Error...", errorMessage);
                    Toast.makeText(ApiLoginDemo.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });

            objectRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(objectRequest);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
