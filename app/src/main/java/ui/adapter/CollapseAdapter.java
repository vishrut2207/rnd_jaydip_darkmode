package ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;

import java.util.Random;

public class CollapseAdapter extends ArrayAdapter<String> {
    private static final String TAG = CollapseAdapter.class.getSimpleName();
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private String[] text_icon1;
    private final String[] relation = {"Person", "Family", "Friend", "Unknown"};

    public CollapseAdapter(Activity context, String[] maintitle, String[] subtitle, String[] text_icon) {
        super(context, R.layout.dynamic_mylist, maintitle);
        this.context = context;
        this.text_icon1 = text_icon;
        this.maintitle = maintitle;
        this.subtitle = subtitle;

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyListAdapter.ViewHolderItem holder;
        View vi = convertView;

        if (convertView == null) {
            vi = LayoutInflater.from(context).inflate(R.layout.dynamic_mylist, null);

            holder = new MyListAdapter.ViewHolderItem();

            holder.titleText = (TextView) vi.findViewById(R.id.title);
            holder.text_icon = (TextView) vi.findViewById(R.id.text_icon);
//            holder.imageView = (ImageView) vi.findViewById(R.id.icon);
            holder.subtitleText = (TextView) vi.findViewById(R.id.subtitle);
            holder.linearLayout = (LinearLayout) vi.findViewById(R.id.linearlayout);
            vi.setTag(holder);
        } else {
            holder = (MyListAdapter.ViewHolderItem) vi.getTag();
        }

        holder.titleText.setText(maintitle[position]);
        holder.subtitleText.setText(subtitle[position]);

       /*  String[] color = context.getResources().getStringArray(R.array.listcolors);
        int[] colors = context.getResources().getIntArray(R.array.listcolors);
        String[] newcolor = {"#448AFF", "#FFC107", "#009688", "#ff8000", "#3C9107", "#FF480E", "#936c6c",
                "#7733ff", "#ED8909", "#448AFF", "#0000ff", "#448AFF", "#FFC107", "#009688", "#ff8000", "#3C9107", "#FF480E", "#936c6c", "#448AFF", "#0000ff", "#009688",
                "#448AFF", "#FFC107", "#009688",};
       int i = new Random().nextInt(254);
        GradientDrawable shap = new GradientDrawable();
        shap.setShape(GradientDrawable.RECTANGLE);
        shap.setColor(Color.parseColor("#" + newcolor[new Random().nextInt(254)]));
        Log.i(TAG, "getView: shap::-"+shap);*/
//        String s = String.valueOf(getItem(position).charAt(6));
//        holder.text_icon.setBackgroundColor(Color.parseColor(s));

        String firstletter = String.valueOf(getItem(position).charAt(0));
        holder.text_icon.setText(firstletter);
        //holder.text_icon.setBackgroundColor(Color.parseColor(text_icon1[position]));
        Random rnd = new Random();
        String a = "";
        int colorr = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        int i = 0;
        int n = 0;
        int b = 0;
        int size = text_icon1.length;


        if (position >= size) {
            for (b = size; b < position; b++) {
                n++;
                if (n == size) {
                    n = 0;
                }
            }
            Log.i(TAG, "getView: n::-" + n);
        } else {
            n = position;
        }

        for (i = 0; i < size; i++) {
            a = text_icon1[n];
        }

        holder.text_icon.setBackgroundColor(Color.parseColor(a));


       /* ColorGenerator generator = ColorGenerator.MATERIAL;
        final int color = generator.getColor(getItem(position));
        TextDrawable drawable = TextDrawable.builder().buildRoundRect(firstletter, color, 0);
        holder.imageView.setImageDrawable(drawable);
*/

        final int finalPosition = position;
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.titleText.setTag(finalPosition);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setItems(relation, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if ("Person".equals(relation[which])) {
                            holder.titleText.setTextColor(Color.BLUE);
                        } else if ("Family".equals(relation[which])) {
                            holder.titleText.setTextColor(Color.rgb(37, 111, 48));
                        } else if ("Friend".equals(relation[which])) {
                            holder.titleText.setTextColor(Color.RED);
                        } else if ("Unknown".equals(relation[which])) {
                            holder.titleText.setTextColor(Color.rgb(209, 41, 119));
                        }
                    }
                });
                holder.titleText.setTag(finalPosition);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }


        });

        return vi;
    }

    static class ViewHolderItem {
        TextView titleText, subtitleText, text_icon;
        ImageView imageView;
        LinearLayout linearLayout;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}