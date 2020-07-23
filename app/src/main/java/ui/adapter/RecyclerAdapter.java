package ui.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.myapplication.R;
import com.optimumbrew.obglide.core.imageloader.GlideApp;

import java.util.List;

import pojo.Person;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private List<Person> personList;
    private int previousPosition = -1;
    Activity activity;


    public RecyclerAdapter(List<Person> personList, Activity activity) {
        this.personList = personList;
        this.activity = activity;
    }


    static class MyViewHolder extends ViewHolder {
        View linear;
        ImageView imageView;
        TextView name, phone_no;

        MyViewHolder(@NonNull View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.rv_image);
            name = (TextView) view.findViewById(R.id.rv_name);
            phone_no = (TextView) view.findViewById(R.id.rv_phoneno);
            linear = (LinearLayout) itemView.findViewById(R.id.linearlayout);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycleview_mylist, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Person personn = personList.get(position);
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(activity);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.start();


        GlideApp.with(activity)
                .load(personn.getImage())
                .placeholder(circularProgressDrawable)
                .into(holder.imageView);
        Log.i(TAG, "onBindViewHolder: getimage:" + personn.getImage());
        holder.name.setText(personn.getName());
        holder.phone_no.setText(personn.getPhone());

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousPosition = position;
                notifyDataSetChanged();

            }
        });
        if (previousPosition == position) {
            holder.name.setTextColor(Color.parseColor("#3C9107"));
        }

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }


}


