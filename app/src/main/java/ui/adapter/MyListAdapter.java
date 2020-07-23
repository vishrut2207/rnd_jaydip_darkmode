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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;


public class MyListAdapter extends ArrayAdapter<String> {

    private static final String TAG = "MyListAdapter";
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private String[] text_icon1;
    private final String[] relation = {"Person", "Family", "Friend", "Unknown"};


    public MyListAdapter(Activity context, String[] maintitle, String[] subtitle, String[] text_icon) {
        super(context, R.layout.dynamic_mylist, maintitle);
        this.context = context;

        this.text_icon1 = text_icon;
        this.maintitle = maintitle;
        this.subtitle = subtitle;


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint({"ResourceType", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolderItem holder;
        View vi = convertView;

        if (convertView == null) {
            vi = LayoutInflater.from(context).inflate(R.layout.dynamic_mylist, null);

            holder = new ViewHolderItem();

            holder.titleText = vi.findViewById(R.id.title);
            holder.text_icon = vi.findViewById(R.id.text_icon);
            holder.subtitleText = vi.findViewById(R.id.subtitle);
            holder.linearLayout = vi.findViewById(R.id.linearlayout);

            vi.setTag(holder);
        } else {
            holder = (ViewHolderItem) vi.getTag();
            Log.i(TAG, "getView: tag:"+vi.getTag());
        }

        holder.titleText.setText(maintitle[position]);
        holder.subtitleText.setText(subtitle[position]);


        String firstletter = String.valueOf(getItem(position).charAt(0));
        holder.text_icon.setText(firstletter);

        String a = "";

        int n = 0;
        int size = text_icon1.length;

        if (position >= size) {
            for (int b = size; b < position; b++) {
                n++;
                if (n == size) {
                    n = 0;
                }
            }
            Log.i(TAG, "getView: n::-" + n);
        } else {
            n = position;
        }

        for (int i = 0; i < size; i++) {
            a = text_icon1[n];
        }

        holder.text_icon.setBackgroundColor(Color.parseColor(a));


//        final int finalPosition = position;
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.titleText.setTag(finalPosition);
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
//                holder.titleText.setTag(finalPosition);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }


        });

        return vi;
    }

    static class ViewHolderItem {
        TextView titleText, subtitleText, text_icon;
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