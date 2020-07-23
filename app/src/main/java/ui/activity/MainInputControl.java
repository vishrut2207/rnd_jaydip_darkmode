package ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainInputControl  extends AppCompatActivity {
   Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_control);
        MainInputControl.this.setTitle("Input Control");
        b1 = (Button) findViewById(R.id.android_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainInputControl.this, ButtonAndroid.class);
                startActivity(intent);
            }
        });
        b2 = (Button)findViewById(R.id.image_button);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainInputControl.this,ImageButton.class);
                startActivity(intent);
            }
        });
        b3 = (Button)findViewById(R.id.edit_text);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainInputControl.this, Edittext.class);
                startActivity(intent);
            }
        });
        b4 = (Button)findViewById(R.id.checkbox_button);
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainInputControl.this, Checkbox.class);
                startActivity(intent);
            }
        });
        b5 = (Button)findViewById(R.id.radio_button);
        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainInputControl.this, Radiob.class);
                startActivity(intent);
            }
        });
        b6 = (Button)findViewById(R.id.switch_button);
        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainInputControl.this, SwitchB.class);
                startActivity(intent);
            }
        });
        b7 = (Button)findViewById(R.id.toggle) ;
        b7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainInputControl.this, ToggleB.class);
                startActivity(intent);
            }
        });
        b8 = (Button)findViewById(R.id.rating_bar) ;
        b8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainInputControl.this, RatingB.class);
                startActivity(intent);
            }
        });
        b9 = (Button)findViewById(R.id.spinner) ;
        b9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainInputControl.this, SpinnerL.class);
                startActivity(intent);
            }
        });
        b10 = (Button)findViewById(R.id.date) ;
        b10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainInputControl.this, DateView.class);
                startActivity(intent);
            }
        });
        b11 = (Button)findViewById(R.id.time) ;
        b11.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainInputControl.this, TimeView.class);
                startActivity(intent);
            }
        });


    }
}
