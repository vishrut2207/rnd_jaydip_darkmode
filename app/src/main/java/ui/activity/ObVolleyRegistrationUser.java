package ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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
import com.optimumbrew.library.core.volley.PhotoMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import pojo.OTPResponse;
import pojo.ThemeColorHelper;

//import com.optimumbrew.library.core.volley.MyVolley;
//import com.optimumbrew.library.core.volley.PhotoMultipartRequest;

public class ObVolleyRegistrationUser extends AppCompatActivity {
    TextView text_register_user_api;

    private static final String TAG = "ObVolleyRegistrationUser";
    ImageView imageView;
    EditText name, email, country, password;
    RadioButton male, female;
    Button sign_up;
    TextView txt_sign_in;

    String gender = "";

    Bitmap bitmap, bitmap1, new_bitmap, new_bitmap1;
    Uri selectedImageUri, selectedImageUri1;
    String fileStringimage, imageFileName;

    final int ALL_PERMISSIONS_CAMERA = 101;
    final int ALL_PERMISSIONS_GALLERY = 102;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String name_r, email_r, country_r, password_r;
    int i = 472;
    private final String[] option = {"Take from Camera", "Select from Gallery"};

    String api = "http://atmlocal.ngrok.io/ask_question_poll/api/public/api/signup";
    String otp_api = "http://atmlocal.ngrok.io/ask_question_poll/api/public/api/verifyUser";
    File image_file, image_file_new;
    String signup_api;
    String api_otp;

    @SuppressLint({"HardwareIds", "MissingPermission"})
    String deviceId;

    @SuppressLint({"SetTextI18n", "MissingPermission", "HardwareIds", "LongLogTag"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.ob_user_register);
        setTitle("Register Demo Using OBVolley");

        text_register_user_api = (TextView) findViewById(R.id.text_register_user_api);
        text_register_user_api.setText("User Registration Using ObVolley");

        imageView = (ImageView) findViewById(R.id.image_register);
        name = (EditText) findViewById(R.id.name_register);
        email = (EditText) findViewById(R.id.email_register);
        country = (EditText) findViewById(R.id.country_register);
        password = (EditText) findViewById(R.id.password_register);
        male = (RadioButton) findViewById(R.id.male_register);
        female = (RadioButton) findViewById(R.id.female_register);
        sign_up = (Button) findViewById(R.id.sing_up_register);
        LinearLayout sign_In = (LinearLayout) findViewById(R.id.sign_in_register);
        sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ObVolleyRegistrationUser.this, ObVolleyLogin.class);
                startActivity(i);
                finish();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    registerUserr(v);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                addProfileDialog(v);
            }
        });
        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Context.
                TELEPHONY_SERVICE);


        deviceId = telephonyManager.getDeviceId();
        if (savedInstanceState != null) {
            String s;
            s = savedInstanceState.getString("image_bitmap", null);

            if (s == null) {
                imageView.setImageResource(R.drawable.men);
            } else {
                new_bitmap = StringToBitMap(s);
                imageView.setImageBitmap(new_bitmap);
                image_file = new File(savedInstanceState.getString("image_file", null));
            }

        }
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (new_bitmap != null) {
            outState.putString("image_bitmap", BitMapToString(new_bitmap));
                outState.putString("image_file", String.valueOf(image_file));
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @SuppressLint("LongLogTag")
    private void registerUserr(final View v) throws JSONException {
        final ProgressDialog progressDialog = new ProgressDialog(ObVolleyRegistrationUser.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please wait...");

        name_r = name.getText().toString();
        email_r = email.getText().toString();
        country_r = country.getText().toString();
        password_r = password.getText().toString();

        if (male.isChecked()) {
            gender = male.getText().toString();
            gender = String.valueOf(1);
        }
        if (female.isChecked()) {
            gender = female.getText().toString();
            gender = String.valueOf(2);
        }

        if (image_file == null) {
            Toast.makeText(ObVolleyRegistrationUser.this, "Please Select Image ", Toast.LENGTH_SHORT).show();
        } else if (name.getText().toString().isEmpty()) {
            name.setError("Please Enter your Name");
        } else if (email.getText().toString().isEmpty()) {
            email.setError("Please Enter Email Address");
        } else if (!email.getText().toString().matches(emailPattern)) {
            email.setError("Invalid Email address");
        } else if (country.getText().toString().isEmpty()) {
            country.setError("Please enter your country");
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Please enter your password");
        } else if (password.getText().length() <= 5) {
            password.setError("Please set minimum 6-digit password");
        } else if (gender.isEmpty()) {
            Toast.makeText(ObVolleyRegistrationUser.this, "Please select Gender", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();

            MyVolley.getInstance(ObVolleyRegistrationUser.this);


            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("first_name", name_r);
                jsonObject.put("last_name", "abc");
                jsonObject.put("email_id", email_r);
                jsonObject.put("password", password_r);
                jsonObject.put("gender", gender);
                jsonObject.put("country", country_r);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String s = getResources().getString(R.string.login_signUp_api);

            signup_api = s + "/ask_question_poll/api/public/api/signup";
            api_otp = s + "/ask_question_poll/api/public/api/verifyUser";

            Log.i(TAG, "registerUserr: s_api::=" + signup_api);
            Log.i(TAG, "registerUserr: v_api::=" + otp_api);

            final PhotoMultipartRequest<pojo.RegistrationResponse> request = new PhotoMultipartRequest<pojo.RegistrationResponse>(signup_api,
                    "profile_img",
                    image_file,
                    "request_data",
                    String.valueOf(jsonObject),
                    pojo.RegistrationResponse.class, null,
                    new Response.Listener<pojo.RegistrationResponse>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(pojo.RegistrationResponse response) {

                            progressDialog.dismiss();


                            String msg = response.getMessage();
                            Log.i(TAG, "onResponse: ssss:" + response.toString());


                            final String token_id = String.valueOf(response.getData());

                            Log.i(TAG, "onResponse: t:::-" + response.getData());

//.........................................................................  OTP  .......................................................................................

                            if ("Thank you from confirming your account and opt has been sent to your email".equals(msg)) {
                                Toast.makeText(ObVolleyRegistrationUser.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                                final AlertDialog.Builder builder = new AlertDialog.Builder(ObVolleyRegistrationUser.this);
                                ViewGroup viewGroup = findViewById(android.R.id.content);
                                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.api_otp_for_user, viewGroup, false);
                                builder.setView(dialogView);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.show();

                                final EditText text = (EditText) dialogView.findViewById(R.id.api_otp);
                                Button btn = (Button) dialogView.findViewById(R.id.btn_opt);
                                TextView txt = (TextView) dialogView.findViewById(R.id.otp_email);
                                txt.setText("Email ID : " + email_r);


                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        progressDialog.show();


                                        if (text.getText().toString().isEmpty()) {
                                            text.setError("Please enter OTP");
                                        }
                                        JSONObject job = new JSONObject();

                                        try {
                                            job.put("user_reg_temp_id", token_id);
                                            job.put("token", text.getText().toString());
                                            i++;
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


                                            job.put("device_info", device_info);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                        String xxx = String.valueOf(job);
                                        Log.i(TAG, "onClick: json::" + xxx);

                                        MyVolley.getInstance(ObVolleyRegistrationUser.this);
                                        GsonRequest<OTPResponse> gs = new GsonRequest<OTPResponse>(Request.Method.POST, api_otp, xxx, OTPResponse.class, null,
                                                new Response.Listener<OTPResponse>() {
                                                    @Override
                                                    public void onResponse(OTPResponse response) {
                                                        progressDialog.dismiss();
                                                        Log.i(TAG, "onResponse: respone:::" + response.toString());

                                                        Log.i(TAG, "onResponse: success:::" + response.getMessage());

//                                                        Toast.makeText(ObVolleyRegistrationUser.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                                                        String msg = response.getMessage();
                                                        if ("Login successfully.".equals(msg)) {

//                                                            Toast.makeText(ObVolleyRegistrationUser.this, "User login successfully.", Toast.LENGTH_SHORT).show();
                                                            Intent i = new Intent(ObVolleyRegistrationUser.this, ObVolleyLogin.class);
                                                            startActivity(i);
                                                            finish();

                                                        } else {
                                                            Toast.makeText(ObVolleyRegistrationUser.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                                                        }


                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                progressDialog.dismiss();
                                                Log.i(TAG, "onErrorResponse: error::" + error);

                                                NetworkResponse networkResponse = error.networkResponse;

                                                if (networkResponse == null) {
                                                    String errorMessage = "unknown error";
                                                    if (error.getClass().equals(TimeoutError.class)) {
                                                        errorMessage = "Request timeout";
                                                    } else if (error.getClass().equals(NoConnectionError.class)) {
                                                        errorMessage = "Failed to connect server";
                                                    }
                                                    Log.i("Error...", errorMessage);
                                                    Toast.makeText(ObVolleyRegistrationUser.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                                                } else {
                                                    String result = new String(networkResponse.data);
                                                    try {
                                                        String errorMessage = "unknown error";
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
                                                        Log.i("Error...", errorMessage);
                                                        Toast.makeText(ObVolleyRegistrationUser.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
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
                                        MyVolley.getInstance(ObVolleyRegistrationUser.this).addToRequestQueue(gs);
                                    }

                                });


                            }


                        }
//..........................................................................................................................................................................
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.i(TAG, "onErrorResponse: error::" + error);

                    NetworkResponse networkResponse = error.networkResponse;

                    if (networkResponse == null) {
                        String errorMessage = "";
                        if (error.getClass().equals(TimeoutError.class)) {
                            errorMessage = "Request timeout";
                            Toast.makeText(ObVolleyRegistrationUser.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i("Error...", errorMessage);
                        } else if (error.getClass().equals(NoConnectionError.class)) {
                            errorMessage = "Failed to connect server";
                            Toast.makeText(ObVolleyRegistrationUser.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i("Error...", errorMessage);
                        }

                    } else {
                        String errorMessage = "";
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
                            Toast.makeText(ObVolleyRegistrationUser.this, "00" + errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i("Error...", errorMessage);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    error.printStackTrace();
                }

            });

            request.setShouldCache(false);
            request.setRetryPolicy(new DefaultRetryPolicy(6000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyVolley.getInstance(this).addToRequestQueue(request);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                Toast.makeText(ObVolleyRegistrationUser.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == ALL_PERMISSIONS_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            } else {
                Toast.makeText(ObVolleyRegistrationUser.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addProfileDialog(View view) {

        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(ObVolleyRegistrationUser.this, permissions, ALL_PERMISSIONS_CAMERA);

                }
//......................................................................................................................

                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(ObVolleyRegistrationUser.this, permissions, ALL_PERMISSIONS_GALLERY);

                }
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.show();

    }


    private void callCamera() {

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(takePicture, 0);
    }

    @SuppressLint("IntentReset")
    private void callGallery() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.setType("image/*");
        startActivityForResult(pickPhoto, 1);
    }


    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case 0:

                    selectedImageUri = data.getData();
                    bitmap = (Bitmap) data.getExtras().get("data");

                    new_bitmap = getResizedBitmap(bitmap, 300);

                    int width = new_bitmap.getWidth();
                    int height = new_bitmap.getHeight();

                    Log.i(TAG, "onActivityResult: size_image::=" + width + "..*.." + height);

                    imageView.setImageBitmap(new_bitmap);

                    Uri tempUri = getImageUri(ObVolleyRegistrationUser.this, new_bitmap);

                    image_file = new File(getRealPathFromURI(tempUri));

                    fileStringimage = image_file.toString();

                    imageFileName = fileStringimage.substring(fileStringimage.lastIndexOf("/") + 1);

                    Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);


                    //********************************************************************************

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {

                        try {
                            selectedImageUri1 = data.getData();

                            bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri1);
                            new_bitmap = getResizedBitmap(bitmap1, 300);

                            int width1 = new_bitmap.getWidth();
                            int height1 = new_bitmap.getHeight();

                            int width1o = bitmap1.getWidth();
                            int height1o = bitmap1.getHeight();

                            Log.i(TAG, "onActivityResult: size_image_compress::=" + width1 + "..*.." + height1);
                            Log.i(TAG, "onActivityResult: size_image_original::=" + width1o + "..*.." + height1o);
                            imageView.setImageBitmap(new_bitmap);
//                            img_take_image.setImageBitmap(bitmap);

                            Uri tempUri1 = getImageUri(ObVolleyRegistrationUser.this, new_bitmap);

                            image_file = new File(getRealPathFromURI(tempUri1));

                            fileStringimage = image_file.toString();

                            Log.i(TAG, "onActivityResult: file::" + image_file);
                            imageFileName = fileStringimage.substring(fileStringimage.lastIndexOf("/") + 1);

                            image_file_new = new File(imageFileName);

                            Log.i(TAG, "onActivityResult: image_file::" + image_file);
                            Log.i(TAG, "onActivityResult: tempUri::" + fileStringimage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 0, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "", null);
        return Uri.parse(path);
    }

    @SuppressLint("LongLogTag")
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        Log.i(TAG, " size_image_original::=" + width + "..*.." + height);

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";

        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);

                cursor.close();
            }
        }
        return path;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ObVolleyRegistrationUser.this, ObVolleyLogin.class);
        startActivity(intent);
        finish();

    }
}