package ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiRegistrationUser extends AppCompatActivity {

    private static final String TAG = "ApiRegistrationUser";
    ImageView imageView;
    EditText name, email, country, password;
    RadioButton male, female;
    Button sign_up;


    String gender = "";

    Bitmap bitmap;
    Uri selectedImageUri;
    String fileStringimage, imageFileName;

    final int ALL_PERMISSIONS_CAMERA = 101;
    final int ALL_PERMISSIONS_GALLERY = 102;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String name_r, email_r, country_r, password_r;

    private final String[] option = {"Take from Camera", "Select from Gallery"};

    String api = "http://ed4144410abd.ngrok.io/ask_question_poll/api/public/api/signup";
    //    private static final String ROOT_URL = "http://seoforworld.com/api/v1/file-upload.php";
    File image_file, image_file_new;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_user_registration_activity);
        setTitle("Register User");

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
             /*   Intent i = new Intent(ApiRegistrationUser.this, ApiLoginDemo.class);
                startActivity(i);*/
                finish();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    registerUser(bitmap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void registerUser(final Bitmap bitmap) throws JSONException {
        final ProgressDialog progressDialog = new ProgressDialog(ApiRegistrationUser.this);
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

        if (imageFileName == null) {
            Toast.makeText(ApiRegistrationUser.this, "Please Select Image ", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ApiRegistrationUser.this, "Please select Gender", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();

            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, api,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject obj = new JSONObject(new String(response.data));

                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                Log.i(TAG, "onResponse: code::" + obj.getString("code"));

                                Log.i(TAG, "onResponse: message::" + obj.getString("message"));
                                String msg = obj.getString("message");
                                if ("Email already Exist.".equals(msg)) {
                                    email.getText().clear();
                                    password.getText().clear();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(TAG, "onErrorResponse: " + error.getMessage());
                            progressDialog.dismiss();

                            NetworkResponse networkResponse = error.networkResponse;
                            String errorMessage = "Oops..! Unknown error";
                            if (networkResponse == null) {
                                if (error.getClass().equals(TimeoutError.class)) {
                                    errorMessage = "Oops..! Request timeout";
                                } else if (error.getClass().equals(NoConnectionError.class)) {
                                    errorMessage = "Oops..! Failed to connect server";
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
                                        errorMessage = "Oops..! Resource not found";
                                    } else if (networkResponse.statusCode == 401) {
                                        errorMessage = message + "Oops..! Please login again";
                                    } else if (networkResponse.statusCode == 400) {
                                        errorMessage = message + "Oops..! Check your inputs";
                                    } else if (networkResponse.statusCode == 500) {
                                        errorMessage = message + "Oops..! Something is getting wrong";
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Log.i("Error...", errorMessage);
                            Toast.makeText(ApiRegistrationUser.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

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
                    params.put("request_data", String.valueOf(jsonObject));

                    Log.i(TAG, "getParams: param::" + params);

                    return params;
                }

                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();

                    params.put("profile_img", new DataPart(imageFileName, getFileDataFromDrawable(bitmap)));

                    Log.i(TAG, "getParams: param::" + params);

                    Log.i(TAG, "getParams: param_file name::" + imageFileName);

                    return params;
                }

            };

            volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(this).add(volleyMultipartRequest);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                Toast.makeText(ApiRegistrationUser.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == ALL_PERMISSIONS_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            } else {
                Toast.makeText(ApiRegistrationUser.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addProfileDialog(final View view) {

        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(ApiRegistrationUser.this, permissions, ALL_PERMISSIONS_CAMERA);

                }
//......................................................................................................................

                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(ApiRegistrationUser.this, permissions, ALL_PERMISSIONS_GALLERY);

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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case 0:

                    selectedImageUri = data.getData();
                    bitmap = (Bitmap) data.getExtras().get("data");

                    imageView.setImageBitmap(bitmap);

                    Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                    image_file = new File(getRealPathFromURI(tempUri));
                    fileStringimage = image_file.toString();
                    imageFileName = fileStringimage.substring(fileStringimage.lastIndexOf("/") + 1);
                    Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);


                    //********************************************************************************

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {

                        try {
                            selectedImageUri = data.getData();
//                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                            imageView.setImageBitmap(bitmap);

                            image_file = new File(getRealPathFromURI(selectedImageUri));

                            fileStringimage = image_file.toString();

                            Log.i(TAG, "onActivityResult: file::" + image_file);
                            imageFileName = fileStringimage.substring(fileStringimage.lastIndexOf("/") + 1);

                            image_file_new = new File(imageFileName);
                            Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);
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
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "", null);
        return Uri.parse(path);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();

    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
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
        finish();

    }
}