package pojo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UseDatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "user";
    private static final String TABLE_NAME1 = "ob";


    private static final String COLUMN_ID = "id_user";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_IMAGE = "image_data";
    private static final String COLUMN_PHONE_NO = "phone_no";
    private static final String TAG = "UseDatabaseHelper";


    public UseDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_use = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_AGE + " TEXT,"
                + COLUMN_GENDER + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_PHONE_NO + " TEXT" + ")";
        db.execSQL(create_use);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: version::" + oldVersion + " ....." + newVersion);
        if (newVersion == 18) {
            String ob = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_EMAIL + " TEXT" + ")";

            db.execSQL("alter table " + TABLE_NAME + " add column " + COLUMN_PHONE_NO + " TEXT");

            db.execSQL(ob);

        }
        if (newVersion == 22)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        if (newVersion == 24) {
            String create_use = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_AGE + " TEXT,"
                    + COLUMN_GENDER + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_PHONE_NO + " TEXT" + ")";
            db.execSQL(create_use);
        }

    }

    //...............................insert ....................................

    public void insertUserData(UserDataPojo userData) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, userData.getName());
        values.put(COLUMN_EMAIL, userData.getEmail());
        values.put(COLUMN_AGE, userData.getAge());
        values.put(COLUMN_GENDER, userData.getGender());
        values.put(COLUMN_IMAGE, userData.getImage());
        values.put(COLUMN_PHONE_NO, userData.getPhone_no());


        db.insert(TABLE_NAME, null, values);

    }

    //.................................... Delete .............................
    public void deleteUser(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + id + "'");

        db.close();

    }

    //......................................Update.............................
    public void userDataUpadate(UserDataPojo userData, String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, userData.getName());
        values.put(COLUMN_EMAIL, userData.getEmail());
        values.put(COLUMN_AGE, userData.getAge());
        values.put(COLUMN_GENDER, userData.getGender());
        values.put(COLUMN_IMAGE, userData.getImage());
        values.put(COLUMN_PHONE_NO, userData.getPhone_no());

        db.update(TABLE_NAME, values, COLUMN_ID + "= ?",
                new String[]{id});

    }

    //.................................... get all data....................................
    public List<UserDataPojo> getAllUserData() {

        List<UserDataPojo> userDataPojos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        if (cursor.moveToLast()) {
            do {
                UserDataPojo pojo = new UserDataPojo();
                pojo.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                pojo.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                pojo.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                pojo.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
                pojo.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                pojo.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                pojo.setPhone_no(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NO)));


                userDataPojos.add(pojo);
            } while (cursor.moveToPrevious());
        }
        db.close();

        return userDataPojos;
    }

    //......................................... data count.......................................
    public int getUseDataCount() {

        String countQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }


}
