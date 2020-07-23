package ui.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.optimumbrew.obglide.core.imageloader.GlideApp;
import com.optimumbrew.obglide.core.imageloader.GlideImageLoader;
import com.optimumbrew.obglide.core.imageloader.ImageLoader;

import pojo.ThemeColorHelper;

public class ImageLoadUsingGlide extends AppCompatActivity {
    String url = "https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg";
    String url2 = "https://upload.wikimedia.org/wikipedia/commons/6/61/Rainbow_Rose_%283366550029%29.jpg";
    ImageView img1, img2;
    ProgressBar progressBar;
    CircularProgressDrawable circularProgressDrawable;

    @SuppressLint("CheckResult")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        ThemeColorHelper.applyThemeColor(ImageLoadUsingGlide.this, themePref);
        setTitle("Image Load Using Glide");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.image_load_using_glide);
        img1 = (ImageView) findViewById(R.id.image1);
        img2 = (ImageView) findViewById(R.id.image);
        circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(7f);
        circularProgressDrawable.setCenterRadius(40f);
        circularProgressDrawable.start();
        //******************** Google Library use
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        GlideApp.with(this)
                .load(url)
                .into(img1);
//********************************** OptimumBrew library
        ImageLoader glideImageLoader = new GlideImageLoader(this);
        glideImageLoader.bindImage(img2, url2, new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }, false);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
