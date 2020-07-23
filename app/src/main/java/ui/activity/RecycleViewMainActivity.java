package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import pojo.Person;
import pojo.ThemeColorHelper;
import ui.adapter.RecyclerAdapter;

public class RecycleViewMainActivity extends AppCompatActivity {
    //private List<Person> personList;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    String url1 = "https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg";
    String url2 = "https://upload.wikimedia.org/wikipedia/commons/6/61/Rainbow_Rose_%283366550029%29.jpg";
    String url3 = "https://cdn.pixabay.com/photo/2018/04/20/07/18/bird-3335264_960_720.png";
    String url4 = "https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg";
    String url5 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRJpPa3z1MqIGMKnwER6J-h1RP_A-itRKwyj20ci3Gv1-L5qgB_";
    String url6 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTdttPjpV32w11539IhV0Xm0VCtaDww6w6L__0HiGTW673jdX2W";
    String url7 = "https://i.pinimg.com/originals/be/7b/b6/be7bb60848542683ce9f2a90aea28809.jpg";
    String url8 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTdttPjpV32w11539IhV0Xm0VCtaDww6w6L__0HiGTW673jdX2W";
    private List<Person> personList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.recycleview_content_main);
        setTitle("Recycle View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewmain);
        RecyclerAdapter adapter = new RecyclerAdapter(personList, RecycleViewMainActivity.this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listItem();

    }

    private void listItem() {
        Person person = new Person("Uttam", "9914356320", url1);
        personList.add(person);

        person = new Person("Bhavin", "8574968542", url2);
        personList.add(person);

        person = new Person("Jaydip", "6352417896", url3);
        personList.add(person);

        person = new Person("Chintan", "7896451263", url4);
        personList.add(person);

        person = new Person("Dhruvin", "8980915809", url5);
        personList.add(person);

        person = new Person("Rahul", "8866711742", url6);
        personList.add(person);

        person = new Person("Dhruvin", "8980915809", url7);
        personList.add(person);

        person = new Person("Milan", "8980915809", url8);
        personList.add(person);

        person = new Person("Gopi", "9914357320", url1);
        personList.add(person);

        person = new Person("Shivam", "8574938542", url2);
        personList.add(person);

        person = new Person("Jignesh", "6352717896", url3);
        personList.add(person);

        person = new Person("Manthan", "7896951263", url4);
        personList.add(person);

        person = new Person("Milan", "8980910809", url5);
        personList.add(person);

        person = new Person("Bhargav", "8866311742", url6);
        personList.add(person);

        person = new Person("Tushar", "8980905809", url7);
        personList.add(person);

        person = new Person("Vivek", "8980914809", url8);
        personList.add(person);

        //*************************
        person = new Person("Uttam", "9914356320", url1);
        personList.add(person);

        person = new Person("Bhavin", "8574968542", url2);
        personList.add(person);

        person = new Person("Jaydip", "6352417896", url3);
        personList.add(person);

        person = new Person("Chintan", "7896451263", url4);
        personList.add(person);

        person = new Person("Dhruvin", "8980915809", url5);
        personList.add(person);

        person = new Person("Rahul", "8866711742", url6);
        personList.add(person);

        person = new Person("Dhruvin", "8980915809", url7);
        personList.add(person);

        person = new Person("Milan", "8980915809", url8);
        personList.add(person);

        person = new Person("Gopi", "9914357320", url1);
        personList.add(person);

        person = new Person("Shivam", "8574938542", url2);
        personList.add(person);

        person = new Person("Jignesh", "6352717896", url3);
        personList.add(person);

        person = new Person("Manthan", "7896951263", url4);
        personList.add(person);

        person = new Person("Milan", "8980910809", url5);
        personList.add(person);

        person = new Person("Bhargav", "8866311742", url6);
        personList.add(person);

        person = new Person("Tushar", "8980905809", url7);
        personList.add(person);

        person = new Person("Vivek", "8980914809", url8);
        personList.add(person);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
