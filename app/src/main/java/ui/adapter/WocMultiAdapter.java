package ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pojo.WocPojo;

import static com.facebook.stetho.inspector.network.ResponseHandlingInputStream.TAG;

public class WocMultiAdapter extends RecyclerView.Adapter {

    private List<WocPojo> wocPojoList;
    private Context mContext;
    private WocMultiInterfaceEvents interfaceEvents;


    public class ViewHolderAdmin extends RecyclerView.ViewHolder {
        TextView chat_message, time;
        ImageButton delete, edit, btn_right, btn_left;
        ImageView chat_image;
        LinearLayout linearLayout, admin_linear;
        TextView date;

        ViewHolderAdmin(View itemView) {
            super(itemView);
            chat_message = itemView.findViewById(R.id.chat_txt_message);
            time = itemView.findViewById(R.id.chat_txt_time);
            delete = itemView.findViewById(R.id.chat_delete_btn);
            time = itemView.findViewById(R.id.chat_txt_time);
            edit = itemView.findViewById(R.id.chat_edit_btn);
            btn_right = itemView.findViewById(R.id.chat_admin_image_btn_right);
            btn_left = itemView.findViewById(R.id.chat_admin_image_btn_left);
            chat_image = itemView.findViewById(R.id.chat_image);
            linearLayout = itemView.findViewById(R.id.linear_chat);
            admin_linear = itemView.findViewById(R.id.woc_admin_linear);
            date = itemView.findViewById(R.id.woc_txt_time_layout);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    interfaceEvents.onDeleteEvents(wocPojoList.get(pos), pos);

                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    interfaceEvents.onEditEvents(v, wocPojoList.get(pos), pos);

                }
            });

            btn_right.setOnClickListener(new View.OnClickListener() {
                @SuppressLint({"LongLogTag", "RtlHardcoded"})
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    int itemPosition = Gravity.RIGHT;
                    interfaceEvents.onCenterItem(v, wocPojoList.get(pos), itemPosition);
                    LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) admin_linear.getLayoutParams();
                    ll.gravity = Gravity.RIGHT;
                    admin_linear.setLayoutParams(ll);
                    btn_right.setVisibility(View.GONE);
                    btn_left.setVisibility(View.VISIBLE);

                }
            });
            btn_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    int itemPosition = Gravity.CENTER;
                    interfaceEvents.onCenterItem(v, wocPojoList.get(pos), itemPosition);
                    LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) admin_linear.getLayoutParams();
                    ll.gravity = Gravity.CENTER;
                    admin_linear.setLayoutParams(ll);
                    btn_right.setVisibility(View.VISIBLE);
                    btn_left.setVisibility(View.GONE);
                }
            });
        }
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {

        TextView chat_message, time;
        ImageButton delete, edit, btn_right, btn_left;
        ImageView chat_image;
        LinearLayout linearLayout, user_linear;
        TextView date;

        ViewHolderUser(View itemView) {
            super(itemView);
            chat_message = itemView.findViewById(R.id.chat__user_txt_message);
            time = itemView.findViewById(R.id.chat__user_txt_time);
            delete = itemView.findViewById(R.id.chat__user_delete_btn);
            time = itemView.findViewById(R.id.chat__user_txt_time);
            edit = itemView.findViewById(R.id.chat__user_edit_btn);
            btn_right = itemView.findViewById(R.id.chat_user_image_btn_right);
            btn_left = itemView.findViewById(R.id.chat_user_image_btn_left);
            chat_image = itemView.findViewById(R.id.chat_user_image);
            linearLayout = itemView.findViewById(R.id.linear__user_chat);
            user_linear = itemView.findViewById(R.id.woc_user_linear);
            date = itemView.findViewById(R.id.woc_txt_time_layout);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    interfaceEvents.onDeleteEvents(wocPojoList.get(pos), pos);

                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    interfaceEvents.onEditEvents(v, wocPojoList.get(pos), pos);

                }
            });

            btn_right.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    int itemPosition = Gravity.CENTER;
                    interfaceEvents.onCenterItem(v, wocPojoList.get(pos), itemPosition);

                    LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) user_linear.getLayoutParams();
                    ll.gravity = Gravity.CENTER;
                    user_linear.setLayoutParams(ll);
                    btn_right.setVisibility(View.GONE);
                    btn_left.setVisibility(View.VISIBLE);
                }
            });
            btn_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    int itemPosition = Gravity.START;
                    interfaceEvents.onCenterItem(v, wocPojoList.get(pos), itemPosition);

                    LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) user_linear.getLayoutParams();
                    ll.gravity = Gravity.START;
                    user_linear.setLayoutParams(ll);
                    btn_right.setVisibility(View.VISIBLE);
                    btn_left.setVisibility(View.GONE);
                }
            });
        }
    }

    public WocMultiAdapter(List<WocPojo> data, Context context, WocMultiInterfaceEvents interfaceEvents) {
        this.interfaceEvents = interfaceEvents;
        this.wocPojoList = data;
        this.mContext = context;

    }

    @SuppressLint("LongLogTag")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {

            case WocPojo.ADMIN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.woc_admin_message_layout, parent, false);
                return new ViewHolderAdmin(view);
            case WocPojo.USER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.woc_user_message_layout, parent, false);
                return new ViewHolderUser(view);

        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (wocPojoList.get(position).getViewType()) {
            case 0:
                return WocPojo.ADMIN;
            case 1:
                return WocPojo.USER;
            default:
                return -1;
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        WocPojo pojo = wocPojoList.get(listPosition);


        if (pojo != null) {
            switch (pojo.getViewType()) {
                case WocPojo.ADMIN:
//                    covertTimeToText(pojo.getDateStamp(), ((ViewHolderAdmin) holder).date);
//                    ((ViewHolderAdmin) holder).date.setText(pojo.getDateStamp());
//                    ((ViewHolderAdmin) holder).date.setVisibility(View.VISIBLE);
                    /* message Gravity */
                    if (pojo.getItemPosition() == 0) {
                        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) ((ViewHolderAdmin) holder).admin_linear.getLayoutParams();
                        ll.gravity = Gravity.END;
                        ((ViewHolderAdmin) holder).admin_linear.setLayoutParams(ll);
                    } else {
                        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) ((ViewHolderAdmin) holder).admin_linear.getLayoutParams();
                        ll.gravity = pojo.getItemPosition();
                        ((ViewHolderAdmin) holder).admin_linear.setLayoutParams(ll);
                    }
                    if (Gravity.CENTER == pojo.getItemPosition()) {
                        ((ViewHolderAdmin) holder).btn_right.setVisibility(View.VISIBLE);
                        ((ViewHolderAdmin) holder).btn_left.setVisibility(View.GONE);

                    } else {
                        ((ViewHolderAdmin) holder).btn_left.setVisibility(View.VISIBLE);
                        ((ViewHolderAdmin) holder).btn_right.setVisibility(View.GONE);
                    }

                    /* Chat Message Text */

                    if (pojo.getMessage() == null || pojo.getMessage().isEmpty()) {
                        ((ViewHolderAdmin) holder).linearLayout.setVisibility(View.GONE);
                    } else {
                        ((ViewHolderAdmin) holder).linearLayout.setVisibility(View.VISIBLE);
                        ((ViewHolderAdmin) holder).chat_message.setText(pojo.getMessage());
                    }

                    /* Image */
                    if (pojo.getImage() == null) {
                        ((ViewHolderAdmin) holder).chat_image.setVisibility(View.GONE);
                    } else {
                        ((ViewHolderAdmin) holder).chat_image.setVisibility(View.VISIBLE);
                        Glide.with(mContext)
                                .load(pojo.getImage())
                                .into(((ViewHolderAdmin) holder).chat_image);
                    }
                    /* Message Time */
                    ((ViewHolderAdmin) holder).time.setText(pojo.getTime());

                    /*  Date Header Method Calling   */
                    if (listPosition != 0) {
                        dateStamp(((ViewHolderAdmin) holder).date,
                                pojo.getDateStamp(),
                                this.wocPojoList.get(listPosition - 1).getDateStamp(), false);
                    } else {
                        dateStamp(((ViewHolderAdmin) holder).date, pojo.getDateStamp()
                                , null
                                , true);
                    }
                    Log.i(TAG, "onBindViewHolder: AdminItem::=" + pojo.getMessage() + "...." + pojo.getImage() + "...." + pojo.getItemPosition() + "..."
                            + pojo.getDateStamp() + "\n");
                    break;
                case WocPojo.USER:
//                    covertTimeToText(pojo.getDateStamp(), ((ViewHolderUser) holder).date);
//                    ((ViewHolderUser) holder).date.setText(pojo.getDateStamp());
//                    ((ViewHolderUser) holder).date.setVisibility(View.VISIBLE);
                    /* Message Gravity */
                    if (pojo.getItemPosition() == 0) {
                        LinearLayout.LayoutParams ll1 = (LinearLayout.LayoutParams) ((ViewHolderUser) holder).user_linear.getLayoutParams();
                        ll1.gravity = Gravity.START;
                        ((ViewHolderUser) holder).user_linear.setLayoutParams(ll1);
                    } else {
                        LinearLayout.LayoutParams ll1 = (LinearLayout.LayoutParams) ((ViewHolderUser) holder).user_linear.getLayoutParams();
                        ll1.gravity = pojo.getItemPosition();
                        ((ViewHolderUser) holder).user_linear.setLayoutParams(ll1);
                    }

                    if (Gravity.CENTER == pojo.getItemPosition()) {
                        ((ViewHolderUser) holder).btn_right.setVisibility(View.GONE);
                        ((ViewHolderUser) holder).btn_left.setVisibility(View.VISIBLE);

                    } else {
                        ((ViewHolderUser) holder).btn_right.setVisibility(View.VISIBLE);
                        ((ViewHolderUser) holder).btn_left.setVisibility(View.GONE);
                    }
                    /* messageText */
                    if (pojo.getMessage() == null || pojo.getMessage().isEmpty()) {
                        ((ViewHolderUser) holder).linearLayout.setVisibility(View.GONE);
                    } else {
                        ((ViewHolderUser) holder).linearLayout.setVisibility(View.VISIBLE);
                        ((ViewHolderUser) holder).chat_message.setText(pojo.getMessage());
                    }

                    /* Image */
                    if (pojo.getImage() == null) {
                        ((ViewHolderUser) holder).chat_image.setVisibility(View.GONE);
                    } else {
                        ((ViewHolderUser) holder).chat_image.setVisibility(View.VISIBLE);
                        Glide.with(mContext)
                                .load(pojo.getImage())
                                .into(((ViewHolderUser) holder).chat_image);
                    }

                    /* message Time */
                    ((ViewHolderUser) holder).time.setText(pojo.getTime());

                    /* Date Header Method Calling   */
                    if (listPosition != 0) {
                        dateStamp(((ViewHolderUser) holder).date, pojo.getDateStamp(), this.wocPojoList.get(listPosition - 1).getDateStamp(), false);
                    } else {
                        dateStamp(((ViewHolderUser) holder).date, pojo.getDateStamp(), null, true);
                    }
                    Log.i(TAG, "onBindViewHolder: UserItem::=" + pojo.getMessage() + "...." + pojo.getImage() + "...." + pojo.getItemPosition() + "..."
                            + pojo.getDateStamp() + "\n");
                    break;
            }
        }
    }

    @SuppressLint({"LongLogTag", "SetTextI18n"})
    private void dateStamp(TextView tv, String newItemDate, String lastItemDate, boolean isFirstItem) {

        Log.i(TAG, "processDate: ddd::" + newItemDate + ".............." + lastItemDate + ".........." + isFirstItem);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        if (isFirstItem) {
            Log.i(TAG, "processDate: isFirstItem:::" + isFirstItem);
            Date date = null;

            try {

                date = f.parse(newItemDate);
                Log.i(TAG, "processDate: istoday::" + date.getTime() + "...." + DateUtils.DAY_IN_MILLIS);
                if (DateUtils.isToday(date.getTime())) {
                    tv.setText("Today");
                } else if (DateUtils.isToday(date.getTime() + DateUtils.DAY_IN_MILLIS)) {

                    tv.setText("Yesterday");
                } else tv.setText(newItemDate);
                tv.setVisibility(View.VISIBLE);
            } catch (ParseException e) {
                e.printStackTrace();
                tv.setVisibility(View.GONE);
            }
        } else {

            if (!newItemDate.equalsIgnoreCase(lastItemDate)) {


                try {

                    Date data = f.parse(newItemDate);
                    Log.i(TAG, "processDate: istoday::" + data.getTime() + "...." + DateUtils.DAY_IN_MILLIS);
                    if (DateUtils.isToday(data.getTime())) {

                        tv.setText("Today");

                    } else if (DateUtils.isToday(data.getTime() + DateUtils.DAY_IN_MILLIS)) {

                        tv.setText("Yesterday");

                    } else tv.setText(newItemDate);
                    tv.setVisibility(View.VISIBLE);
                } catch (ParseException e) {
                    e.printStackTrace();
                    tv.setVisibility(View.GONE);
                }
            } else {
                tv.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return wocPojoList.size();
    }

    public interface WocMultiInterfaceEvents {
        void onDeleteEvents(WocPojo wocPojo, int position);

        void onEditEvents(View v, WocPojo wocPojo, int position);

        void onCenterItem(View v, WocPojo wocPojo, int position);
    }

}