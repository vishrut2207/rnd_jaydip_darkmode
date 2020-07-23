package ui.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class DropDownMenuN extends AppCompatActivity {
    Button subject, mark;
    TextView txtsubject, txtmark;
    LinearLayout layout;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.drop_down_menu_n);
        setTitle("Drop Down Menu");
        subject = (Button) findViewById(R.id.btn_subject);
        mark = (Button) findViewById(R.id.btn_mark);
        txtsubject = (TextView) findViewById(R.id.text_subject);
        txtmark = (TextView) findViewById(R.id.text_mark);
        subject.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //   pw.showAtLocation(viewPos, Gravity.CENTER, 0, 0);
                ContextThemeWrapper ctw = new ContextThemeWrapper(DropDownMenuN.this, R.style.CustomPopupTheme);
                final PopupMenu dropPopupMenu = new PopupMenu(ctw, subject);
                dropPopupMenu.getMenuInflater().inflate(R.menu.drop_down_subject, dropPopupMenu.getMenu());
                subject.setText("Subject");

                dropPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        subject.setBackgroundColor(Color.RED);
                        txtsubject.setText(item.getTitle());
                        return false;
                    }
                });

                dropPopupMenu.show();
            }
        });
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextThemeWrapper ctw = new ContextThemeWrapper(DropDownMenuN.this, R.style.CustomPopupTheme);
                final PopupMenu dropPopupMenu = new PopupMenu(ctw, mark);
                dropPopupMenu.getMenuInflater().inflate(R.menu.drop_down_mark, dropPopupMenu.getMenu());
                mark.setText("Percentage");

                dropPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        mark.setBackgroundColor(Color.rgb(25, 118, 210));
                        txtmark.setText("Percentage between\n               " + item.getTitle());
                        return false;
                    }
                });
                dropPopupMenu.show();
            }
        });
    }

}