package ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.optimumbrew.library.core.volley.MyVolley;
import com.optimumbrew.library.core.volley.PhotoMultipartRequest;

import java.io.File;

import pojo.ThemeColorHelper;
import pojo.VideoResponse;

public class OBVolleyVideoDemo extends AppCompatActivity {
    private static final String TAG = "OBVolleyVideoDemo";
    VideoView take_video, upload_video;
    Button btn_take_video, btn_upload_video;

    final int ALL_PERMISSIONS_CAMERA = 101;
    final int ALL_PERMISSIONS_GALLERY = 102;

    private final String[] option = {"Take from Camera", "Select from Gallery"};
    File file_video;
    String api_video = "http://docurl.ngrok.io/demo_project/api/public/api/uploadMediaFile";
    String api_v;
    Uri path, uri;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.ob_volley_video_demo);
        setTitle("OB Volley using API Video Demo");

        take_video = (VideoView) findViewById(R.id.api_take_video);
        upload_video = (VideoView) findViewById(R.id.api_upload_video);

        final MediaController mediaController_1 = new MediaController(OBVolleyVideoDemo.this);
        final MediaController mediaController_2 = new MediaController(OBVolleyVideoDemo.this);
        take_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        upload_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        mediaController_1.setAnchorView(take_video);
        mediaController_2.setAnchorView(upload_video);

        take_video.setMediaController(mediaController_1);
        upload_video.setMediaController(mediaController_2);

        mediaController_1.setAnchorView(mediaController_1);
        mediaController_2.setAnchorView(mediaController_2);

        btn_take_video = (Button) findViewById(R.id.btn_api_take_video);
        btn_take_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeVideoMethod(v);
            }
        });


        btn_upload_video = (Button) findViewById(R.id.btn_api_upload_video);
        btn_upload_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file_video == null)
                    Toast.makeText(OBVolleyVideoDemo.this, "Please select Video", Toast.LENGTH_SHORT).show();
                else
                    uploadVideo();
            }
        });

        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("path", null);
            Log.i(TAG, "onCreate: uri::=" + s);
            if (s != null) {
                path = Uri.parse(s);
                file_video = new File(savedInstanceState.getString("file_video", null));
                take_video.setVideoURI(path);
                take_video.start();
                take_video.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (path != null) {
            outState.putString("path", String.valueOf(path));
            outState.putString("file_video", String.valueOf(file_video));
            Log.i(TAG, "onSaveInstanceState: uri::" + String.valueOf(path));
        }
    }


    public void uploadVideo() {
        final ProgressDialog progressDialog = new ProgressDialog(OBVolleyVideoDemo.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        api_v = getResources().getString(R.string.Image_video_Token_api);
        String n = api_v + "/demo_project/api/public/api/uploadMediaFile";
        Log.i(TAG, "uploadVideo: API::"+n);
        MyVolley.getInstance(this);

        PhotoMultipartRequest<VideoResponse> request = new PhotoMultipartRequest<VideoResponse>(n,
                "file",
                file_video,
                "a",
                "b", VideoResponse.class, null, new Response.Listener<VideoResponse>() {
            @Override
            public void onResponse(VideoResponse response) {
                progressDialog.dismiss();
                Log.i(TAG, "onResponse: response::-" + response.toString());

                Toast.makeText(OBVolleyVideoDemo.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                String image = String.valueOf(response.getData());
                Log.i(TAG, "onResponse:Video::-" + image);

                upload_video.setVideoPath(image);
                upload_video.start();
                upload_video.setBackgroundColor(Color.TRANSPARENT);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(OBVolleyVideoDemo.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onErrorResponse: error::-" + error);
            }
        });
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolley.getInstance(OBVolleyVideoDemo.this).addToRequestQueue(request);


    }

    public void takeVideoMethod(View view) {
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(OBVolleyVideoDemo.this, permissions, ALL_PERMISSIONS_CAMERA);

                }
//......................................................................................................................

                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(OBVolleyVideoDemo.this, permissions, ALL_PERMISSIONS_GALLERY);

                }
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.show();

    }

    private void callCamera() {

        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, 1);
        }
    }

    @SuppressLint("IntentReset")
    private void callGallery() {

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("video/*");
        startActivityForResult(pickIntent, 2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                Toast.makeText(OBVolleyVideoDemo.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == ALL_PERMISSIONS_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            } else {
                Toast.makeText(OBVolleyVideoDemo.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case 1:
                    uri = data.getData();
                    //File
                    file_video = new File(getPath(data.getData()));
                    path = Uri.parse(String.valueOf(file_video));
                    take_video.setVideoURI(path);

                    take_video.start();
                    take_video.setBackgroundColor(Color.TRANSPARENT);
                    Log.i(TAG, "onActivityResult: video::-" + uri);
                    break;
                case 2:
                    uri = data.getData();
                    /* File */
                    file_video = new File(getPath(data.getData()));
                    path = Uri.parse(String.valueOf(file_video));

                    take_video.setVideoURI(path);

                    take_video.start();
                    take_video.setBackgroundColor(Color.TRANSPARENT);
                    Log.i(TAG, "onActivityResult: video::-" + uri);
                    Log.i(TAG, "onActivityResult: video::-" + file_video);
                    Log.i(TAG, "onActivityResult: video::-" + path);
                    break;
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}
