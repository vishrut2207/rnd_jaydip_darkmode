package ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class NavigatioActionMenuFloatingButton extends AppCompatActivity {
    Button b1,b2,b3,b4;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigatio_action_menu_flotion_button);
        setTitle("Navigation Action Menu");
        b1 = (Button) findViewById(R.id.navigation_drawer);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigatioActionMenuFloatingButton.this, NavigationMainActivity.class);
                startActivity(intent);
            }
        });
        b2 = (Button) findViewById(R.id.sliding_menu_with_web_view);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigatioActionMenuFloatingButton.this, SlidingMenuWithWebView.class);
                startActivity(intent);
            }
        });
        b3 = (Button) findViewById(R.id.drop_down_menu_n);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigatioActionMenuFloatingButton.this, DropDownMenuN.class);
                startActivity(intent);
            }
        });
        b4 = (Button) findViewById(R.id.floating_action_button);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigatioActionMenuFloatingButton.this, FloatinActionButton.class);
                startActivity(intent);
            }
        });
    }
}