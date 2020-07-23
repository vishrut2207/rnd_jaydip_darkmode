package ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DemoTask extends AppCompatActivity {
    Button b1,b2,b3;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_task);
        setTitle("Demo Task");
        b1 = (Button) findViewById(R.id.button_demo);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoTask.this, DemoLayout.class);
                startActivity(intent);
            }
        });
        b2 = (Button)findViewById(R.id.table_demo);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(DemoTask.this, DemoTable.class);
                startActivity(intent);
            }
        });
        b3 = (Button)findViewById(R.id.view_position_demo);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(DemoTask.this, DemoViewPosition.class);
                startActivity(intent);
            }
        });

    }
}
