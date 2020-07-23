package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;
import ui.adapter.MyListAdapter;

public class Dynamic_list_view extends AppCompatActivity {

    String[] maintitle = {
            "Rahul", "Dhruvin", "Uttam",
            "Meet", "Jenish", "Yash",
            "Ashish", "Parth", "Jaydip",
            "Ravi", "Chintan", "Maynak",
            "Tushar", "Vivek", "Mitul",
            "Hardik", "Kishan", "Jon",
            "Swar", "Kuldip", "Gautam",
            "Minesh", "Raj", "Bhargav",
            "Rahul", "Dhruvin", "Uttam",
            "Meet", "Jenish", "Yash",
            "Ashish", "Parth", "Jaydip",
            "Ravi", "Chintan", "Maynak",
            "Tushar", "Vivek", "Mitul",
            "Hardik", "Kishan", "Jon",
            "Swar", "Kuldip", "Gautam",
            "Minesh", "Raj", "Bhargav"};

    String[] subtitle = {
            "9586748595", "9913229987",
            "8965327412", "7005802522",
            "7405145823", "9925584125",
            "8866721771", "7621800520",
            "8585856236", "9858575152",
            "6685845858", "9586748595",
            "9913229987", "8965327412",
            "7405145823", "9925584125",
            "8866721771", "7621800520",
            "8585856236", "9858575152",
            "6685845858", "9586748595",
            "9913229987", "9586748595",
            "9586748595", "9913229987",
            "8965327412", "7005802522",
            "7405145823", "9925584125",
            "8866721771", "7621800520",
            "8585856236", "9858575152",
            "6685845858", "9586748595",
            "9913229987", "8965327412",
            "7405145823", "9925584125",
            "8866721771", "7621800520",
            "8585856236", "9858575152",
            "6685845858", "9586748595",
            "9913229987", "9586748595"};

    String[] newcolor = {
            "#448AFF", "#FFC107", "#009688",
            "#ff8000", "#3C9107"/*, "#FF480E",
            "#936c6c", "#7733ff", "#ED8909",
            "#448AFF", "#0000ff", "#448AFF",
            "#FFC107", "#009688", "#ff8000",
            "#3C9107", "#FF480E", "#936c6c",
            "#448AFF", "#0000ff", "#009688",
            "#448AFF", "#FFC107", "#009688"*/};

    Toolbar toolbar;
    private static final String TAG = "Dynamic_list_view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

     /*   switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.listViewTheme);
                Log.i(TAG, "onCreate: yyy::" + "yyyy");
                break;
            case Configuration.UI_MODE_NIGHT_NO:

                Log.i(TAG, "onCreate: yyy::" + "xxx");
                break;
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_list_view);
        setTitle("Dynamic List View");
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ListView listView = (ListView) findViewById(R.id.list);

        final MyListAdapter myListAdapter = new MyListAdapter(Dynamic_list_view.this, maintitle, subtitle, newcolor);

//        setSupportActionBar(toolbar);

//        toolbar.setNavigationIcon(R.drawable.ic_leftmenu);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        listView.setAdapter(myListAdapter);
    }

   /* public void setSupportActionBar(Toolbar toolbar) {
    }*/
}
