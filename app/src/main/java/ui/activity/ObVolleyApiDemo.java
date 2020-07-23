package ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.R;

public class ObVolleyApiDemo extends AppCompatActivity {
    Button login_demo, image_demo, vedio_demo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ob_volley_lib_using_post_api);
        setTitle("Login, Image and Video post using ObVolley");

        ActivityCompat.requestPermissions(ObVolleyApiDemo.this, new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.LOCATION_HARDWARE,
                Manifest.permission.READ_SMS}, 100);
        login_demo = (Button) findViewById(R.id.login_demo);
        login_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObVolleyApiDemo.this, ObVolleyLogin.class);
                startActivity(intent);
            }
        });

        image_demo = (Button) findViewById(R.id.image_demo);
        image_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObVolleyApiDemo.this, OBVolleyImageDemo.class);
                startActivity(intent);
            }
        });
        vedio_demo = (Button) findViewById(R.id.video_demoo);
        vedio_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObVolleyApiDemo.this, OBVolleyVideoDemo.class);
                startActivity(intent);
            }
        });
    }
}
