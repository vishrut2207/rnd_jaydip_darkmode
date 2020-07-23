package ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pojo.ThemeColorHelper;
import pojo.UseDatabaseHelper;
import pojo.UserDataPojo;
import pojo.UserTemp;
import ui.adapter.UserDataAdapter;

import static android.view.View.VISIBLE;

public class SQLiteDatabaseMainActivity extends AppCompatActivity implements UserDataAdapter.UserInterfaceEvents {
    UserDataAdapter userDataAdapter;
    List<UserDataPojo> pojoList;
    List<UserDataPojo> olddata;
    List<UserDataPojo> mylist;
    UseDatabaseHelper useDatabaseHelper;
    Button btn_add;
    UseDatabaseHelper db;
    TextView textView, empty_search_view;
    EditText search_user_edittext;
    UserDataPojo user = new UserDataPojo();
    int lpos;
    RecyclerView recyclerView;
    String u_name, u_email, u_age, u_gender, u_image, u_id, u_phone_no;
    String text = null;
    int i = 0;
    private static final String TAG = "SQLiteDatabaseMainActivity";

    @SuppressLint("LongLogTag")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.sqlite_mainactivity);
        setTitle("SQLite Database");

        pojoList = new ArrayList<>();
        olddata = new ArrayList<>();
        useDatabaseHelper = UserTemp.getUseDatabaseHelper();
//..........................................................Stetho...............................................................
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(SQLiteDatabaseMainActivity.this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(SQLiteDatabaseMainActivity.this));
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(SQLiteDatabaseMainActivity.this));
        Stetho.Initializer initializer = initializerBuilder.build();
        Stetho.initialize(initializer);
//.................................................Add user activity call ....................................................................................
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SQLiteDatabaseMainActivity.this, UserAddDataBaseActivity.class);
                startActivityForResult(i, 2);

            }
        });
        int v = 24;
        db = new UseDatabaseHelper(this, "userdatadb", null, v);

        Log.i(TAG, "onCreate:  Version::=" + v);


//.....................................................Updata method call user information..............................


//................................get all data method call ...................
        pojoList.addAll(db.getAllUserData());

//........................................................Adapater...................................................
        userDataAdapter = new UserDataAdapter(SQLiteDatabaseMainActivity.this, pojoList, this);


        recyclerView = findViewById(R.id.sqlite_recyclerviewmain);

        recyclerView.setHasFixedSize(true);
       /* final RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(SQLiteDatabaseMainActivity.this) {

                    private static final float SPEED = 100f;

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }

                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(userDataAdapter);

        textView = findViewById(R.id.empty_notes_view);
        empty_search_view = findViewById(R.id.empty_search_view);
        toggleEmptyNotes();
//...........................................Search View......................................................................................................
        empty_search_view = findViewById(R.id.empty_search_view);
        search_user_edittext = findViewById(R.id.search_user);


        search_user_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    userDataAdapter.notifyDataSetChanged();
                }
                text = search_user_edittext.getText().toString().toLowerCase(Locale.getDefault());
                filter(text, pojoList);
                i = 1;

            }
        });

    }


    @SuppressLint("LongLogTag")
    public void filter(String NewText, List<UserDataPojo> pojoList) {

        NewText = NewText.toLowerCase();
        mylist = new ArrayList<>();

        String name = null;

        for (UserDataPojo up : pojoList) {
            if (up != null)
                if (up.getName() != null)
                    name = up.getName().toLowerCase();

            if (name.contains(NewText)) {
                mylist.add(up);
                empty_search_view.setVisibility(View.GONE);
            } else {
                if (mylist.isEmpty())
                    empty_search_view.setVisibility(VISIBLE);
            }
        }
        userDataAdapter.setSearchOpration(mylist);

        userDataAdapter.notifyDataSetChanged();
    }

    // ...............................................................deleteNote................................................

    @SuppressLint("LongLogTag")
    public void deleteNote(String id, int position) {
        pojoList.clear();
        Log.i(TAG, "deleteNote: position::=" + position);

        db.deleteUser(id);
        pojoList.addAll(db.getAllUserData());
//        pojoList.remove(position);
//        userDataAdapter.notifyItemRemoved(position);
        userDataAdapter.notifyDataSetChanged();

        if (i == 1)
            filter(text, pojoList);

        toggleEmptyNotes();
    }

    //...............................................................Update activity call......................................
    @Override
    public void onUpdateEvents(UserDataPojo pojo) {

        Intent intent = new Intent(SQLiteDatabaseMainActivity.this, UserUpdataActivity.class);

        intent.putExtra("name", pojo.getName());
        intent.putExtra("email", pojo.getEmail());
        intent.putExtra("age", pojo.getAge());
        intent.putExtra("phone_no", pojo.getPhone_no());
        intent.putExtra("gender", pojo.getGender());
        intent.putExtra("image", pojo.getImage());
        intent.putExtra("id", pojo.getId());

        startActivityForResult(intent, 1);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onDeleteEvents(final UserDataPojo pojo, final int pos) {
        Log.i(TAG, "deleteNote: position::=" + pos);
        if (i == 1)
            user = mylist.get(pos);
        else
            user = pojoList.get(pos);

        AlertDialog.Builder dialog = new AlertDialog.Builder(SQLiteDatabaseMainActivity.this);
        dialog.setMessage("Do you want to Delete " + pojo.getName() + " User Information ?");
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteNote(pojo.getId(), pos);
                userDataAdapter.notifyDataSetChanged();
            }

        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }

    @Override
    public void messageBackground(RelativeLayout relativeLayout, int pos) {

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                if (pos % 2 == 1) {
                    relativeLayout.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    relativeLayout.setBackgroundColor(Color.TRANSPARENT);
                }
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                if (pos % 2 == 1) {
                    relativeLayout.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    relativeLayout.setBackgroundColor(Color.parseColor("#d9d9d9"));
                }
                break;
        }
    }


    @SuppressLint({"MissingSuperCall", "LongLogTag"})
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//.........................................Update.........................................
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case 1:
                    pojoList.clear();

                    u_name = data.getStringExtra("d_name");
                    u_email = data.getStringExtra("d_email");
                    u_age = data.getStringExtra("d_age");
                    u_phone_no = data.getStringExtra("d_phone_no");
                    u_gender = data.getStringExtra("d_gender");
                    u_image = data.getStringExtra("d_image");
                    u_id = data.getStringExtra("d_id");


                    user.setName(u_name);
                    user.setEmail(u_email);
                    user.setAge(u_age);
                    user.setPhone_no(u_phone_no);
                    user.setGender(u_gender);
                    user.setImage(u_image);


                    db.userDataUpadate(user, u_id);

                    pojoList.addAll(db.getAllUserData());
                    Log.i(TAG, "onActivityResult: size::" + pojoList.size());
                    if (i == 1)
                        filter(text, pojoList);
                    pojoList.contains(user);
                    Log.i(TAG, "onActivityResult: size::" + pojoList.size());

                    userDataAdapter.notifyItemChanged(lpos);
                    userDataAdapter.notifyDataSetChanged();

                    Log.i(TAG, "onActivityResult: pos" + lpos);
                    break;

//..................................................Add..............................................
                case 2:
                    pojoList.clear();

                    String i_name = data.getStringExtra("i_name");
                    String i_email = data.getStringExtra("i_email");
                    String i_age = data.getStringExtra("i_age");
                    String i_phone_no = data.getStringExtra("i_phone_no");
                    String i_gender = data.getStringExtra("i_gender");
                    String i_image = data.getStringExtra("i_image");


                    UserDataPojo user_i = new UserDataPojo();

                    user_i.setName(i_name);
                    user_i.setEmail(i_email);
                    user_i.setAge(i_age);
                    user_i.setPhone_no(i_phone_no);
                    user_i.setGender(i_gender);
                    user_i.setImage(i_image);

                    db.insertUserData(user_i);


                    pojoList.addAll(db.getAllUserData());
                    if (i == 1)
                        filter(text, pojoList);
                    Log.i(TAG, "onActivityResult: size::" + pojoList.size());
                    if (pojoList.size() == 0) {
                        userDataAdapter = new UserDataAdapter(SQLiteDatabaseMainActivity.this, pojoList, this);
                        recyclerView.setAdapter(userDataAdapter);

                    }
                    userDataAdapter.notifyItemChanged(0);
                    userDataAdapter.notifyItemInserted(0);
                    userDataAdapter.notifyDataSetChanged();
                    toggleEmptyNotes();
                    break;

            }
        }
    }


    //    .................................................................toggleEmptyNotes...............................................................
    private void toggleEmptyNotes() {

        if (db.getUseDataCount() > 0) {
            textView.setVisibility(View.GONE);
        } else {

            textView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
