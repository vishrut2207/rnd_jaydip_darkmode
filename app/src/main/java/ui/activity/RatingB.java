package ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.hsalf.smilerating.SmileRating;

import pojo.ThemeColorHelper;

public class RatingB extends AppCompatActivity {

    RatingBar ratingbar1, ratingbar2, ratingbar3, ratingbar4;
    SmileRating smilerating;
    TextView text1, text2, text3, text4;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Get Previous Theme */
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themeColor", ThemeColorHelper.ORANGE);

        if (themePref != null) {
            ThemeColorHelper.applyThemeColor(this, themePref);
        }

        setContentView(R.layout.rating_bar);
        setTitle("Rating Bar");
        ratingbar1 = (RatingBar) findViewById(R.id.rating1);
        ratingbar2 = (RatingBar) findViewById(R.id.rating2);
        ratingbar3 = (RatingBar) findViewById(R.id.rating3);
        ratingbar4 = (RatingBar) findViewById(R.id.rating4);
        smilerating = (SmileRating) findViewById(R.id.rating5);


        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);

        text1.setText("Rate:");
        text2.setText("Rate:");
        text3.setText("Rate:");
        text4.setText("Rate:");
        ratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar r2, float rating, boolean fromUser) {
                float f=ratingbar1.getRating();
                Toast.makeText(getApplicationContext(), "Rate: " + f, Toast.LENGTH_SHORT).show();

            }
        });
        ratingbar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar r2, float rating, boolean fromUser) {
                float f=ratingbar2.getRating();
                text1.setText("Rate:" + f);


            }
        });
        ratingbar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar r2, float rating, boolean fromUser) {
                float f=ratingbar3.getRating();
                text2.setText("Rate:" + f);
                // Toast.makeText(getApplicationContext(), "Rate: " + r, Toast.LENGTH_LONG).show();

            }
        });
        ratingbar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar r2, float rating, boolean fromUser) {
                float f=ratingbar4.getRating();
                text3.setText("Rate:" + f);
                // Toast.makeText(getApplicationContext(), "Rate: " + r, Toast.LENGTH_LONG).show();

            }
        });

        smilerating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean b) {
                String s = smilerating.getSmileName(smiley);
                text4.setText("Rate:" + s);
              /*  if (smiley == 1)
                    Toast.makeText(getApplicationContext(), "Terrible!", Toast.LENGTH_SHORT).show();
                else if (smiley == 2)
                    Toast.makeText(getApplicationContext(), "Bad", Toast.LENGTH_SHORT).show();
                else if (smiley == 3)
                    Toast.makeText(getApplicationContext(), "Okay", Toast.LENGTH_SHORT).show();
                else if (smiley == 4)
                    Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Great", Toast.LENGTH_SHORT).show();
                    */

            }
        });

    }


}

