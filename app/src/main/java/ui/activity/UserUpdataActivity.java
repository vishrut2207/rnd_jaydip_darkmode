package ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.ThemeColorHelper;
import pojo.UseDatabaseHelper;
import pojo.UserDataPojo;
import pojo.UserTemp;

public class UserUpdataActivity extends AppCompatActivity {

    private static final String TAG = "UserUpdataActivity";
    private static final int ALL_PERMISSIONS_CAMERA = 101;
    private static final int ALL_PERMISSIONS_GALLERY = 102;
    EditText name, email, age, phone_no;
    RadioGroup radioGroup;
    Button btn_register;
    ImageView take_image;
    RadioButton male, female;
    Bitmap bitmap;
    Uri selectedImageUri;
    private final String[] option = {"Take from Camera",
            "Select from Gallery"};
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    UseDatabaseHelper useDatabaseHelper;
    String fileStringimage;

    List<UserDataPojo> pojoList;

    String display = "";
    String d_image, d_name, d_email, d_age, d_gender, d_id, d_phone_no;
    private static String username, useremail, userage, usermale, userfemale, useImage, userphone_no, userId;
    String d, b_gender = "";
    String b_image;


    @SuppressLint("SourceLockedOrientationActivity")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", null);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }
        setContentView(R.layout.sqlite_layout_data_add);
        setTitle("Update Data");

        pojoList = new ArrayList<>();


        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        age = (EditText) findViewById(R.id.age);
        phone_no = (EditText) findViewById(R.id.phone_no_);
        radioGroup = (RadioGroup) findViewById(R.id.radio_gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        take_image = (ImageView) findViewById(R.id.take_image);
        btn_register = (Button) findViewById(R.id.btn_register);
        useDatabaseHelper = UserTemp.getUseDatabaseHelper();


        d_name = getIntent().getStringExtra("name");
        d_email = getIntent().getStringExtra("email");
        d_age = getIntent().getStringExtra("age");
        d_phone_no = getIntent().getStringExtra("phone_no");
        d_gender = getIntent().getStringExtra("gender");
        d_image = getIntent().getStringExtra("image");
        d_id = getIntent().getStringExtra("id");
        userId = d_id;

        if (male.getText().toString().equals(d_gender)) {
            male.setChecked(true);
            display = male.getText().toString();

        } else {
            female.setChecked(true);
            display = female.getText().toString();

        }
        name.setText(d_name);
        email.setText(d_email);
        age.setText(d_age);
        phone_no.setText(d_phone_no);

       /* if (fileStringimage != null) {
            take_image.setImageBitmap(bitmap);
            Glide.with(this)
                    .load(fileStringimage)
                    .into(take_image);
            Log.i(TAG, "onCreate: imag::"+fileStringimage);
        }  else{*/
        Glide.with(this)
                .load(d_image)
                .into(take_image);
        Log.i(TAG, "onCreate: imag_d::" + d_image);

//        }

        if (d_image == null) {
            Glide.with(this)
                    .load(R.drawable.men)
                    .into(take_image);
        }

        username = name.getText().toString();
        useremail = email.getText().toString();
        userage = age.getText().toString();
        usermale = male.getText().toString();
        userfemale = female.getText().toString();
//..........................................................BackPress Dialog ...........................................................
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_gender = male.getText().toString();
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_gender = female.getText().toString();
            }
        });

        if (male.isChecked())
            b_gender = usermale;
        if (female.isChecked())
            b_gender = userfemale;

      /*  if (fileStringimage == null) {
            fileStringimage = d_image;
            Log.i(TAG, "onCreate: image:.df....:" + fileStringimage);

        }*/
//        b_image = fileStringimage;


        btn_register.setText("Update");
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = name.getText().toString();
                useremail = email.getText().toString();
                userage = age.getText().toString();
                userphone_no = phone_no.getText().toString();
                usermale = male.getText().toString();
                userfemale = female.getText().toString();

                if (male.isChecked()) {
                    d = usermale;
                }
                if (female.isChecked()) {
                    d = userfemale;
                }

                if (fileStringimage == null) {
                    fileStringimage = d_image;
                }
                useImage = fileStringimage;


                if (username.isEmpty()) {
                    name.setError("Please Enter Valid Name");
                } else if (useremail.isEmpty()) {
                    email.setError("Please Enter Email Address");
                } else if (!email.getText().toString().matches(emailPattern)) {
                    email.setError("Invalid email address");
                } else if (age.getText().toString().equals("0")
                        || age.getText().toString().equals("00") || age.getText().toString().equals("000")) {
                    age.setError("Please Enter Valid Age");
                } else if (age.getText().toString().isEmpty()) {
                    age.setError("Please Enter Age");
                } else if (phone_no.getText().toString().isEmpty()) {
                    phone_no.setError("Please Enter Phone No");
                } else if (useImage == null) {
                    Toast.makeText(UserUpdataActivity.this, "Please Select Image ", Toast.LENGTH_SHORT).show();
                } else {


                    Intent intent = new Intent();

                    intent.putExtra("d_name", username);
                    intent.putExtra("d_email", useremail);
                    intent.putExtra("d_age", userage);
                    intent.putExtra("d_phone_no", userphone_no);
                    intent.putExtra("d_gender", d);
                    intent.putExtra("d_image", useImage);
                    intent.putExtra("d_id", userId);

                    setResult(1, intent);
                    finish();

                    if (d_name.equals(username) && d_email.equals(useremail) && d_age.equals(userage) && d_phone_no.equals(userphone_no) && d_gender.equals(d) && getIntent().getStringExtra("image").equals(useImage)) {
                        Toast.makeText(UserUpdataActivity.this, "Not Need to Update Data..", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UserUpdataActivity.this, "User Information is Update..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        if (savedInstanceState != null) {
            String b;
            b = savedInstanceState.getString("image_bitmap", null);
            Log.i(TAG, "onCreate: b::=" + b);
            if (b != null) {
                bitmap = StringToBitMap(b);
                take_image.setImageBitmap(bitmap);
                fileStringimage = savedInstanceState.getString("image_file", null);
                Log.i(TAG, "onCreate: b::=" + fileStringimage);
                Glide.with(this)
                        .load(fileStringimage)
                        .into(take_image);
                d_image = null;

            } else {
                take_image.setImageResource(R.drawable.men);
            }

        }
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (bitmap != null) {
            outState.putString("image_bitmap", BitMapToString(bitmap));
            outState.putString("image_file", fileStringimage);
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public Bitmap StringToBitMap(String s) {
        try {
            byte[] encodeByte = Base64.decode(s, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void onBackPressed() {

        if (!name.getText().toString().equals(d_name) || !email.getText().toString().equals(d_email) || !age.getText().toString().equals(d_age) || !b_gender.equals(d_gender)
                || !phone_no.getText().toString().equals(d_phone_no) || fileStringimage != null && !fileStringimage.equals(d_image)) {
            Log.i(TAG, "onBackPressed: qqqqq::" + fileStringimage + "\n" + d_image);

            final AlertDialog.Builder dialoguilder = new AlertDialog.Builder(this);
            dialoguilder.setMessage("Are you sure ?");
            dialoguilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    finish();
                }
            });
            dialoguilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialoguilder.show();
        } else finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                Toast.makeText(UserUpdataActivity.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == ALL_PERMISSIONS_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            } else {
                Toast.makeText(UserUpdataActivity.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
    }

    //.....................................................................................................................................
    //.................................................. Image handle.............................................
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void optionDialogg(final View view) {

        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(UserUpdataActivity.this, permissions, ALL_PERMISSIONS_CAMERA);
//                    callCamera();
                }
                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(UserUpdataActivity.this, permissions, ALL_PERMISSIONS_GALLERY);
//                    callGallery();
                }
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    private void callCamera() {

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(takePicture, 0);
    }

    private void callGallery() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case 0:

                    selectedImageUri = data.getData();
                    bitmap = (Bitmap) data.getExtras().get("data");
                    take_image.setImageBitmap(bitmap);
                    Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                    File file1 = new File(getRealPathFromURI(tempUri));
                    fileStringimage = file1.toString();
                    Log.i(TAG, "onActivityResult: tempUri::" + fileStringimage);

                    //********************************************************************************

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {

                        try {
                            selectedImageUri = data.getData();
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                            take_image.setImageBitmap(bitmap);

                            File file = new File(getRealPathFromURI(selectedImageUri));
                            fileStringimage = file.toString();

                            Log.i(TAG, "onActivityResult: tempUri::" + fileStringimage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }


}