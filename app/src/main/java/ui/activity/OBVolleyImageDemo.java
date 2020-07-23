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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.optimumbrew.library.core.volley.GsonRequest;
import com.optimumbrew.library.core.volley.MyVolley;
import com.optimumbrew.library.core.volley.PhotoMultipartRequest;
import com.optimumbrew.obglide.core.imageloader.GlideApp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pojo.ImageResponse;
import pojo.ThemeColorHelper;
import pojo.Token;

public class OBVolleyImageDemo extends AppCompatActivity {

    TextView txt_take_image, txt_upload_image;
    private static final String TAG = "ApiImageDemo";
    Button btn_take_image, btn_upload_image;
    ImageView img_take_image, img_upload_image;

    Bitmap bitmap, new_bitmap;
    Uri selectedImageUri;
    String fileStringimage, imageFileName;

    final int ALL_PERMISSIONS_CAMERA = 101;
    final int ALL_PERMISSIONS_GALLERY = 102;


    private final String[] option = {"Take from Camera", "Select from Gallery"};

    String api_image = "http://docurl.ngrok.io/demo_project/api/public/api/uploadImage";
    String api_token = "http://docurl.ngrok.io/demo_project/api/public/api/doLoginForGuestUser";
    File image_file, image_file_new;
    String token_str;
    String api_t;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.api_image_demo_volley);
        setTitle("Image Demo Using OB Volley");
        ActivityCompat.requestPermissions(OBVolleyImageDemo.this, new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.LOCATION_HARDWARE,
                Manifest.permission.READ_SMS}, 100);

        txt_take_image = (TextView) findViewById(R.id.txt_take_image);
        txt_upload_image = (TextView) findViewById(R.id.txt_upload_image);

        btn_take_image = (Button) findViewById(R.id.btn_api_take_image);
        btn_take_image.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                takeImageApiVolley(v);
            }
        });

        btn_upload_image = (Button) findViewById(R.id.btn_api_upload_image);
        btn_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (image_file == null) {
                    Toast.makeText(OBVolleyImageDemo.this, "Please select Image", Toast.LENGTH_SHORT).show();
                } else
                    uploadImage(v);
            }
        });

        img_take_image = (ImageView) findViewById(R.id.api_take_image);
        img_upload_image = (ImageView) findViewById(R.id.api_upload_image);

        api_t = getResources().getString(R.string.Image_video_Token_api);
        String n = api_t + "/demo_project/api/public/api/doLoginForGuestUser";

        String xx = "{}";
        MyVolley.getInstance(this);
        GsonRequest<Token> tokenGsonRequest = new GsonRequest<Token>(Request.Method.POST,
                n, xx, Token.class, null,

                new Response.Listener<Token>() {
                    @Override
                    public void onResponse(Token response) {

                        Log.i(TAG, "onResponse: token::-" + response.toString());
                        Log.i(TAG, "onResponse: token::-" + response.getData());
                        token_str = String.valueOf(response.getData());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: error::-" + error);

            }
        });
        tokenGsonRequest.setShouldCache(false);
        tokenGsonRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolley.getInstance(OBVolleyImageDemo.this).addToRequestQueue(tokenGsonRequest);

        if (savedInstanceState != null) {
            String s;
            s = savedInstanceState.getString("image_bitmap", null);

            if (s == null) {
                img_take_image.setImageResource(R.drawable.men);
            } else {
                new_bitmap = StringToBitMap(s);
                img_take_image.setImageBitmap(new_bitmap);
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

    public void uploadImage(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(OBVolleyImageDemo.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Map<String, String> params = new HashMap<>();
        params.put("Authorization", "Bearer " + token_str);

        Log.i(TAG, "uploadImage: param::-" + params);

        String a = api_t + "/demo_project/api/public/api/uploadImage";

        MyVolley.getInstance(this);
        String s = "s";
        PhotoMultipartRequest<ImageResponse> request = new PhotoMultipartRequest<ImageResponse>(a,
                "file",
                image_file,
                s,
                s,
                ImageResponse.class,
                params, new Response.Listener<ImageResponse>() {
            @Override
            public void onResponse(ImageResponse response) {
                progressDialog.dismiss();
                Log.i(TAG, "onResponse: response::-" + response.toString());

                String image = String.valueOf(response.getData());
                Log.i(TAG, "onResponse: image::-" + image);

                Toast.makeText(OBVolleyImageDemo.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                GlideApp.with(OBVolleyImageDemo.this).load("http://docurl.ngrok.io/demo_project/image_bucket/original/" + image)
                        .into(img_upload_image);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(OBVolleyImageDemo.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onErrorResponse: error::-" + error);
            }
        });
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolley.getInstance(OBVolleyImageDemo.this).addToRequestQueue(request);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                Toast.makeText(OBVolleyImageDemo.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == ALL_PERMISSIONS_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            } else {
                Toast.makeText(OBVolleyImageDemo.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void takeImageApiVolley(View view) {

        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(OBVolleyImageDemo.this, permissions, ALL_PERMISSIONS_CAMERA);

                }
//......................................................................................................................
                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(OBVolleyImageDemo.this, permissions, ALL_PERMISSIONS_GALLERY);

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

                    img_take_image.setImageBitmap(new_bitmap);

                    Uri tempUri = getImageUri(OBVolleyImageDemo.this, new_bitmap);

                    image_file = new File(getRealPathFromURI(tempUri));

//                    fileStringimage = image_file.toString();

//                    imageFileName = fileStringimage.substring(fileStringimage.lastIndexOf("/") + 1);

//                    Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);


                    //********************************************************************************

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {

                        try {
                            selectedImageUri = data.getData();

                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                            new_bitmap = getResizedBitmap(bitmap, 300);

                            img_take_image.setImageBitmap(new_bitmap);
//                            img_take_image.setImageBitmap(bitmap);

                            Uri tempUri1 = getImageUri(OBVolleyImageDemo.this, new_bitmap);

                            image_file = new File(getRealPathFromURI(tempUri1));

//                            fileStringimage = image_file.toString();

//                            Log.i(TAG, "onActivityResult: file::" + image_file);
//                            imageFileName = fileStringimage.substring(fileStringimage.lastIndexOf("/") + 1);

//                            image_file_new = new File(imageFileName);

//                            Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);
//                            Log.i(TAG, "onActivityResult: tempUri::" + fileStringimage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

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

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "", null);
        return Uri.parse(path);
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
