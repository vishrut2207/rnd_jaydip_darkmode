package ui.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import pojo.ThemeColorHelper;
import ui.fragment.FragmentBottomSheet;

public class BottomSheet extends AppCompatActivity {
    Button btn, expand;
    BottomSheetDialog dialog;
    BottomSheetBehavior behavior;
    View bottomsheet;
    TextView note, bluetooth, mail, share, copy, delete;

    @SuppressLint({"ResourceType", "SourceLockedOrientationActivity"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setTitle("Bottom Sheet");

        setContentView(R.layout.bottom_main_sheet);
        note = (TextView) findViewById(R.id.note);
        bluetooth = (TextView) findViewById(R.id.bluetooth);
        mail = (TextView) findViewById(R.id.mail);
        share = (TextView) findViewById(R.id.share);
        copy = (TextView) findViewById(R.id.copy);
        delete = (TextView) findViewById(R.id.delete);
        btn = (Button) findViewById(R.id.btn1);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        bottomsheet = findViewById(R.id.bottomsheetlayout);
        behavior = BottomSheetBehavior.from(bottomsheet);
        behavior.setHideable(true);
        behavior.setPeekHeight(100);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.bottom_sheet);
        dialog.setCanceledOnTouchOutside(false);

        expand = (Button) findViewById(R.id.expand);
        expand.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    bottomsheet.setBackgroundColor(Color.parseColor("#1F466C"));
                    expand.setText("Collapse sheet");
                } else if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    bottomsheet.setBackgroundColor(Color.parseColor("#31531C"));
                    expand.setText("hide sheet");
                } else if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    expand.setText("expand sheet");
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new FragmentBottomSheet();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

            }
        });


        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Note", Toast.LENGTH_SHORT).show();
            }
        });
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Bluetooth", Toast.LENGTH_SHORT).show();
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Mail", Toast.LENGTH_SHORT).show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Share", Toast.LENGTH_SHORT).show();
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Copy", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }

}
