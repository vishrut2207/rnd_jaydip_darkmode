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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.optimumbrew.obglide.core.imageloader.GlideApp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojo.WocPojo;
import ui.adapter.WocMultiAdapter;

public class WocDesignMainActivitiy extends AppCompatActivity implements WocMultiAdapter.WocMultiInterfaceEvents {
    private static final String TAG = "WocDesignMainActivitiy";
    EditText enter_msg;
    CheckBox checkBox;
    ImageView btn_image, chat_image;
    Button btn_send;


    Bitmap bitmap, bitmap1;
    Uri selectedImageUri, selectedImageUri1;
    String fileStringimage, imageFileName, dialogImageStrign;
    String edittext_msg;
    final int ALL_PERMISSIONS_CAMERA = 101;
    final int ALL_PERMISSIONS_GALLERY = 102;
    int i;

    private final String[] option = {"Take from Camera", "Select from Gallery"};
    File image_file;
    WocMultiAdapter wocMultiAdapter;
    List<WocPojo> wocPojoList;
    RecyclerView recyclerView;

    View dialogView;
    ImageView dialog_image, btn_dialog_cancel;
    EditText dialog_editText;
    Button dialog_btn_save, dialog_btn_add_imag;
//    ImageView btn_cancel;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    SharedPreferences preferencesGet;
    SharedPreferences.Editor editorGet;
    String PREF_NAME = "chatMessage";
    WocPojo pojo;
    int viewType = 0;
    LinearLayoutManager layoutManager1;
    Gson gson;
    String setjson;
    int itemPosition, btn_visibility;
    String getjson;

    @SuppressLint("CommitPrefEdits")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.woc_design);
        setTitle("Woc Design");
        enter_msg = (EditText) findViewById(R.id.woc_enter_message);
        checkBox = (CheckBox) findViewById(R.id.woc_checkbox);
        btn_image = (ImageView) findViewById(R.id.woc_image);
        chat_image = (ImageView) findViewById(R.id.woc_imageview);
//        btn_cancel = (ImageView) findViewById(R.id.woc_btn_cancel_image);
        btn_send = (Button) findViewById(R.id.woc_btn_send);

        /* RecyclerView */
        recyclerView = (RecyclerView) findViewById(R.id.woc_recyclerviewmain);
        recyclerView.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setStackFromEnd(true);
        recyclerView.smoothScrollToPosition(recyclerView.getBottom());

        /* Image View Add   */
        chat_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 2;
                addImage(v);
            }
        });
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 2;
                addImage(v);
            }
        });


        checkBox.setChecked(true);
        itemPosition = Gravity.END;
        btn_visibility = View.VISIBLE;
        /* Check Box */
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    Toast.makeText(WocDesignMainActivitiy.this, "Admin", Toast.LENGTH_SHORT).show();
                    viewType = WocPojo.ADMIN;
                    itemPosition = Gravity.END;

                } else {
                    Toast.makeText(WocDesignMainActivitiy.this, "User", Toast.LENGTH_SHORT).show();
                    viewType = WocPojo.USER;
                    itemPosition = Gravity.START;

                }
            }
        });

        wocPojoList = new ArrayList<>();


        enter_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (enter_msg.getText().toString().length() > 0) {
                    btn_send.setVisibility(View.VISIBLE);
                    wocMultiAdapter.notifyDataSetChanged();
                } else {
                    btn_send.setVisibility(View.GONE);
                    wocMultiAdapter.notifyDataSetChanged();
                }
            }
        });

        if (fileStringimage == null) {
            btn_send.setVisibility(View.GONE);
//            btn_cancel.setVisibility(View.GONE);
        } else {
            btn_send.setVisibility(View.VISIBLE);
//            btn_cancel.setVisibility(View.VISIBLE);
        }



        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = preferences.edit();

        /* Message Send button */
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager1.setStackFromEnd(true);
                recyclerView.smoothScrollToPosition(recyclerView.getBottom());
                /* Time */
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                String chat_time = dateFormat.format(new Date()).toString();

                /* Date */
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
                String s_date = date1.format(new Date()).toString();

                Log.i(TAG, "onClick: time::" + s_date);

                edittext_msg = enter_msg.getText().toString();

                if (edittext_msg.length() == 0) {
                    edittext_msg = null;
                }
                pojo = new WocPojo(viewType,
                        edittext_msg,
                        fileStringimage,
                        chat_time,
                        s_date,
                        itemPosition);
                wocPojoList.add(pojo);

                enter_msg.getText().clear();
                fileStringimage = null;
                chat_image.setImageResource(0);
                btn_send.setVisibility(View.GONE);
                chat_image.setVisibility(View.GONE);

                /* Gson Using Store Data */
                gson = new Gson();
                setjson = gson.toJson(wocPojoList);
                editor.putString("admin", setjson);
                editor.putBoolean("isboolean", true);
                editor.apply();
                Log.i(TAG, "onClick: setjson::=" + setjson + "\n");
                wocMultiAdapter.notifyDataSetChanged();

            }
        });





        /* Get Data in SharedPreference */
        preferencesGet = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editorGet = preferencesGet.edit();
        boolean isBoolean = preferencesGet.getBoolean("isboolean", Boolean.parseBoolean(""));
        if (isBoolean == true) {
            Gson gsonn = new Gson();
            getjson = preferencesGet.getString("admin", null);
            Type type = new TypeToken<ArrayList<WocPojo>>() {
            }.getType();
            wocPojoList = gsonn.fromJson(getjson, type);

            Log.i(TAG, "onCreate: getJson::=" + getjson + "\n");
        }

        /* Adapter Call */
        wocMultiAdapter = new WocMultiAdapter(wocPojoList, this, this);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(wocMultiAdapter);
        wocMultiAdapter.notifyDataSetChanged();


    }


    /* Adapter InterFace onEditEvents */
    @Override
    public void onEditEvents(View v, final WocPojo wocPojo, final int position) {
        i = 1;
        final AlertDialog.Builder builder = new AlertDialog.Builder(WocDesignMainActivitiy.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.woc_edit_message_dialog, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

        dialog_image = (ImageView) dialogView.findViewById(R.id.woc_dialog_edit_imageView);
        btn_dialog_cancel = (ImageView) dialogView.findViewById(R.id.woc_dialog_edit_btn_cancel);
        dialog_editText = (EditText) dialogView.findViewById(R.id.woc_dialog_editText);
        dialog_btn_save = (Button) dialogView.findViewById(R.id.woc_dialog_edite_btn_save);
        dialog_btn_add_imag = (Button) dialogView.findViewById(R.id.woc_dialog_edite_btn_image_add);

        if (wocPojo.getImage() == null) {
            btn_dialog_cancel.setVisibility(View.GONE);
            dialog_btn_add_imag.setVisibility(View.VISIBLE);
        } else {
            btn_dialog_cancel.setVisibility(View.VISIBLE);
            dialog_btn_add_imag.setVisibility(View.GONE);
        }

        if (dialogImageStrign == null) {
            dialogImageStrign = wocPojo.getImage();

        } else {
            dialog_btn_add_imag.setVisibility(View.GONE);
            btn_dialog_cancel.setVisibility(View.VISIBLE);

        }

        /* add image */
        dialog_btn_add_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImageDialog(v);
            }
        });
        /* btn image remove */
        btn_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogImageStrign = null;
                dialog_image.setImageResource(0);
                dialog_image.setVisibility(View.GONE);
                btn_dialog_cancel.setVisibility(View.GONE);
                dialog_btn_add_imag.setVisibility(View.VISIBLE);

            }
        });


        dialog_editText.setText(wocPojo.getMessage());

        if (wocPojo.getImage() == null) {
            dialog_image.setVisibility(View.GONE);
        } else {
            dialog_image.setVisibility(View.VISIBLE);
            dialog_btn_add_imag.setVisibility(View.GONE);
            GlideApp.with(this)
                    .load(wocPojo.getImage())
                    .into(dialog_image);
        }
        /* save message */
        dialog_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wocPojo.setMessage(dialog_editText.getText().toString());
                wocPojo.setImage(dialogImageStrign);


                /* Gson Using Store Data */
                Gson gson = new Gson();
                String json = gson.toJson(wocPojoList);
                editor.putString("admin", json);
                editor.apply();
                Log.i(TAG, "onClick: " + json);

                wocMultiAdapter.notifyItemChanged(position);
                wocMultiAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
                dialogImageStrign = null;
                Log.i(TAG, "onClick: message::=" + wocPojo.toString());
            }
        });

        dialog_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImageDialog(v);
            }
        });

    }

    @Override
    public void onCenterItem(View v, WocPojo wocPojo, int position) {
        wocPojo.setItemPosition(position);

        Gson gson = new Gson();
        String json = gson.toJson(wocPojoList);
        editor.putString("admin", json);
        editor.apply();
    }

    /* Adapter InterFace onDeleteEvents*/
    @Override
    public void onDeleteEvents(final WocPojo wocPojo, final int position) {

        Log.i(TAG, "onDeleteEvents: position::=" + position);
        AlertDialog.Builder dialog = new AlertDialog.Builder(WocDesignMainActivitiy.this);
        dialog.setMessage("Are you sure you want to delete this Message ?");
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wocPojoList.remove(position);
                wocPojoList.remove(wocPojo);
                wocMultiAdapter.notifyItemRemoved(position);
//                wocMultiAdapter.notifyItemRangeChanged(position, wocPojoList.size());
                wocMultiAdapter.notifyDataSetChanged();

                Gson gson = new Gson();
                String json = gson.toJson(wocPojoList);
                editor.putString("admin", json);
                editor.apply();
            }

        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                Toast.makeText(WocDesignMainActivitiy.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == ALL_PERMISSIONS_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            } else {
                Toast.makeText(WocDesignMainActivitiy.this, " Permission Denied by User", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /* Enter Message With send Image */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addImage(View view) {

        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE};

                    ActivityCompat.requestPermissions(WocDesignMainActivitiy.this, permissions, ALL_PERMISSIONS_CAMERA);

                }
                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(WocDesignMainActivitiy.this, permissions, ALL_PERMISSIONS_GALLERY);

                }
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.show();

    }

    /* Edit Dialog Set Image */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addImageDialog(View view) {

        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick Image ");
        dialogBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if ("Take from Camera".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE};

                    ActivityCompat.requestPermissions(WocDesignMainActivitiy.this, permissions, ALL_PERMISSIONS_CAMERA);

                }
                if ("Select from Gallery".equals(option[which])) {
                    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(WocDesignMainActivitiy.this, permissions, ALL_PERMISSIONS_GALLERY);

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

    @SuppressLint("IntentReset")
    private void callGallery() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.setType("image/*");
        startActivityForResult(pickPhoto, 1);
    }


    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case 0:

                    selectedImageUri = data.getData();
                    bitmap = (Bitmap) data.getExtras().get("data");

                    Bitmap new_bitmap = getResizedBitmap(bitmap, 300);

                    int width = new_bitmap.getWidth();
                    int height = new_bitmap.getHeight();

//                    Log.i(TAG, "onActivityResult: size_image::=" + width + "..*.." + height);
                    if (i == 1) {
                        dialog_image.setVisibility(View.VISIBLE);
                        btn_dialog_cancel.setVisibility(View.VISIBLE);
                        dialog_image.setImageBitmap(new_bitmap);
                        Uri tempUri = getImageUri(getApplicationContext(), new_bitmap);

                        image_file = new File(getRealPathFromURI(tempUri));

                        dialogImageStrign = image_file.toString();
                        if (dialogImageStrign != null) {
                            dialog_btn_add_imag.setVisibility(View.GONE);
                        }
                        Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);

                    }
                    if (i == 2) {
                        chat_image.setImageBitmap(new_bitmap);

                        Uri tempUri = getImageUri(getApplicationContext(), new_bitmap);

                        image_file = new File(getRealPathFromURI(tempUri));
                        if (image_file.length() > 0) {
                            chat_image.setVisibility(View.VISIBLE);
                            btn_send.setVisibility(View.VISIBLE);
                        } else {
                            chat_image.setVisibility(View.GONE);
                            btn_send.setVisibility(View.GONE);
                        }
                        fileStringimage = image_file.toString();

                        Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);
                    }

                    //********************************************************************************

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {

                        try {
                            selectedImageUri1 = data.getData();

                            bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri1);
                            Bitmap new_bitmap1 = getResizedBitmap(bitmap1, 300);

                            int width1 = new_bitmap1.getWidth();
                            int height1 = new_bitmap1.getHeight();

                            int width1o = bitmap1.getWidth();
                            int height1o = bitmap1.getHeight();

//                            Log.i(TAG, "onActivityResult: size_image_compress::=" + width1 + "..*.." + height1);
//                            Log.i(TAG, "onActivityResult: size_image_original::=" + width1o + "..*.." + height1o);
                            if (i == 1) {
                                dialog_image.setVisibility(View.VISIBLE);
                                btn_dialog_cancel.setVisibility(View.VISIBLE);
                                dialog_image.setImageBitmap(new_bitmap1);

                                Uri tempUri1 = getImageUri(getApplicationContext(), new_bitmap1);
                                image_file = new File(getRealPathFromURI(tempUri1));

                                dialogImageStrign = image_file.toString();
                                if (dialogImageStrign != null) {
                                    dialog_btn_add_imag.setVisibility(View.GONE);
                                }

                                Log.i(TAG, "onActivityResult: file::" + dialogImageStrign);
                                imageFileName = dialogImageStrign.substring(dialogImageStrign.lastIndexOf("/") + 1);

                            }
                            if (i == 2) {
                                chat_image.setImageBitmap(new_bitmap1);

                                Uri tempUri1 = getImageUri(getApplicationContext(), new_bitmap1);

                                image_file = new File(getRealPathFromURI(tempUri1));
                                if (image_file.length() > 0) {
                                    chat_image.setVisibility(View.VISIBLE);
                                    btn_send.setVisibility(View.VISIBLE);
                                } else {
                                    chat_image.setVisibility(View.GONE);
                                    btn_send.setVisibility(View.GONE);
                                }
                                fileStringimage = image_file.toString();

                                Log.i(TAG, "onActivityResult: file::" + image_file);
                                imageFileName = fileStringimage.substring(fileStringimage.lastIndexOf("/") + 1);

//                                image_file_new = new File(imageFileName);

                                Log.i(TAG, "onActivityResult: tempUri::" + imageFileName);
                                Log.i(TAG, "onActivityResult: tempUri::" + fileStringimage);
                            }
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
        inImage.compress(Bitmap.CompressFormat.JPEG, 0, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "", null);
        return Uri.parse(path);
    }

    @SuppressLint("LongLogTag")
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

//        Log.i(TAG, " size_image_original::=" + width + "..*.." + height);

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
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
