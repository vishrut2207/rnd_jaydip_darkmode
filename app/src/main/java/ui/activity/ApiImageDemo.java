package ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.optimumbrew.library.core.volley.GsonRequest;
import com.optimumbrew.library.core.volley.MyVolley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import pojo.Token;

public class ApiImageDemo extends AppCompatActivity {

    private static final String TAG = "ApiImageDemo";
    Button btn_take_image, btn_upload_image;
    ImageView img_take_image, img_upload_image;

    Bitmap bitmap;
    Uri selectedImageUri;
    String fileStringimage, imageFileName;

    final int ALL_PERMISSIONS_CAMERA = 101;
    final int ALL_PERMISSIONS_GALLERY = 102;


    private final String[] option = {"Take from Camera", "Select from Gallery"};

    String api_image = "http://0a02c72073a2.ngrok.io/demo_project/api/public/api/uploadImage";
    String api_token = "http://0a02c72073a2.ngrok.io/demo_project/api/public/api/doLoginForGuestUser";
    File image_file, image_file_new;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_image_demo_volley);
        setTitle("Image Demo using Volley");

        btn_take_image = (Button) findViewById(R.id.btn_api_take_image);
        btn_upload_image = (Button) findViewById(R.id.btn_api_upload_image);

        img_take_image = (ImageView) findViewById(R.id.api_take_image);
        img_upload_image = (ImageView) findViewById(R.id.api_upload_image);
        String xx = "{}";
        MyVolley.getInstance(this);

        GsonRequest<Token> tokenGsonRequest = new GsonRequest<Token>(Request.Method.POST,
                api_token, xx, Token.class, null,

                new Response.Listener<Token>() {
                    @Override
                    public void onResponse(Token response) {

                        Log.i(TAG, "onResponse: token::-" + response.toString());
//                        Log.i(TAG, "onResponse: token::-" + response.getData());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: error::-" + error);

            }
        });
        tokenGsonRequest.setShouldCache(false);
    /*    tokenGsonRequest.setRetryPolicy(new DefaultRetryPolicy(SyncStateContract.Constants.REQUEST_TIMEOUT_FOR_JSON,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolley.getInstance(ApiImageDemo.this).addToRequestQueue(tokenGsonRequest);*/

    }

    public void uploadImage() {

        MyVolley.getInstance(this);
//        PhotoMultipartRequest request =
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                Toast.makeText(ApiImageDemo.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == ALL_PERMISSIONS_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            } else {
                Toast.makeText(ApiImageDemo.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void takeImageApiVolley(final View view) {

        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(ApiImageDemo.this, permissions, ALL_PERMISSIONS_CAMERA);

                }
//......................................................................................................................

                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(ApiImageDemo.this, permissions, ALL_PERMISSIONS_GALLERY);

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

                    img_take_image.setImageBitmap(bitmap);

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

                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                            img_take_image.setImageBitmap(bitmap);

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