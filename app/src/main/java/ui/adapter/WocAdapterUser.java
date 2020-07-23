package ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

import pojo.WocPojo;

public class WocAdapterUser extends RecyclerView.Adapter<WocAdapterUser.MyViewHolder> {

    private static final String TAG = "WocAdapter";
    private Context mcontext;
    private List<WocPojo> mWocPojos;
    WocPojo pojo;
    private WocAdapterUser.WocInterfaceEventsUser wocInterfaceEvents;


    public WocAdapterUser(Context contex, List<WocPojo> dataPojoList, WocAdapterUser.WocInterfaceEventsUser wocInterfaceEvents) {
        this.mcontext = contex;
        this.mWocPojos = dataPojoList;
        this.wocInterfaceEvents = wocInterfaceEvents;

    }

    @NonNull
    @Override
    public WocAdapterUser.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.woc_user_message_layout, parent, false);

        return new WocAdapterUser.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WocPojo pojo = mWocPojos.get(position);


        if (pojo.getMessage() == null || pojo.getMessage().isEmpty()) {
            holder.linearLayout.setVisibility(View.GONE);
        } else {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.chat_message.setText(pojo.getMessage());
        }


        if (pojo.getImage() == null) {
            holder.chat_image.setVisibility(View.GONE);
        } else {
            holder.chat_image.setVisibility(View.VISIBLE);
            Glide.with(mcontext)
                    .load(pojo.getImage())
                    .into(holder.chat_image);
        }
        holder.time.setText(pojo.getTime());

        Log.i(TAG, "onBindViewHolder: Amessage::=" + pojo.getMessage() + "\n" + pojo.getImage());

    }


    @Override
    public int getItemCount() {
        return mWocPojos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView chat_message, time;
        ImageButton delete, edit, profile;
        ImageView chat_image;
        LinearLayout linearLayout;

        @SuppressLint("CutPasteId")
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_message = itemView.findViewById(R.id.chat__user_txt_message);
            time = itemView.findViewById(R.id.chat__user_txt_time);
            delete = itemView.findViewById(R.id.chat__user_delete_btn);
            time = itemView.findViewById(R.id.chat__user_txt_time);
            edit = itemView.findViewById(R.id.chat__user_edit_btn);
//            profile = itemView.findViewById(R.id.chat__user_image_btn);
            chat_image = itemView.findViewById(R.id.chat_user_image);
            linearLayout = itemView.findViewById(R.id.linear__user_chat);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    wocInterfaceEvents.UseronDeleteEvents(mWocPojos.get(pos), pos);

                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    wocInterfaceEvents.UseronEditEvents(v, mWocPojos.get(pos), pos);

                }
            });
        }
    }

    public interface WocInterfaceEventsUser {
        void UseronDeleteEvents(WocPojo wocPojo, int position);

        void UseronEditEvents(View v, WocPojo wocPojo, int position);
    }
}
