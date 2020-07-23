package ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ToastDialogSnackBar extends AppCompatActivity {
    Button toast, dialog, snackbar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_dialog_snackbar_control);
        setTitle("Toast Dialog SnackBar Control ");
        toast = (Button) findViewById(R.id.btn_toast);
        dialog = (Button) findViewById(R.id.btn_dialog);
        snackbar = (Button) findViewById(R.id.btn_snackbar);
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToastDialogSnackBar.this, ToastControl.class);
                startActivity(intent);
            }
        });
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToastDialogSnackBar.this, DialogControl.class);
                startActivity(intent);
            }
        });
        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToastDialogSnackBar.this, SnackbarControl.class);
                startActivity(intent);
            }
        });
    }
}
