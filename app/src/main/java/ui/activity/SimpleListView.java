package ui.activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;

import pojo.ThemeColorHelper;

public class SimpleListView extends AppCompatActivity {

    String[] Items = {"iteam 1", "iteam 2", "iteam 3", "iteam 4", "iteam 5", "iteam 8", "iteam 7", "iteam 6"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        super.onCreate(savedInstanceState);
        setTitle("Simple List View");
        setContentView(R.layout.simplelist);
        listView = findViewById(R.id.simple_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SimpleListView.this, R.layout.row, Items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SimpleListView.this, Items[position], Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(this, Items[position], Toast.LENGTH_SHORT).show();
    }*/
}