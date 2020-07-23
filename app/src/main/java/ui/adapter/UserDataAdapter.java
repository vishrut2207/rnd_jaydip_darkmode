package ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import pojo.UserDataPojo;


public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.MyViewHolder> {
    private Context mcontext;
    private List<UserDataPojo> dataPojoList;

    private UserInterfaceEvents userInterfaceEvents;
    private static final String TAG = "UserDataAdapter";


    public UserDataAdapter(Context context, List<UserDataPojo> userDataPojos, UserInterfaceEvents userInterfaceEvents) {
        this.userInterfaceEvents = userInterfaceEvents;
        this.mcontext = context;
        this.dataPojoList = userDataPojos;
    }


    @NonNull
    @Override
    public UserDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sqlite_add_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final UserDataPojo pojo = dataPojoList.get(position);

        Log.i(TAG, "onBindViewHolder: id:: " + position);
        holder.name.setText(pojo.getName());
        Log.i(TAG, "onBindViewHolder: name:: " + pojo.getName());

        holder.email.setText(pojo.getEmail());
        Log.i(TAG, "onBindViewHolder: email:: " + pojo.getEmail());

        holder.age.setText(pojo.getAge());
        Log.i(TAG, "onBindViewHolder: age:: " + pojo.getAge());

        holder.phone_no.setText(pojo.getPhone_no());

        holder.gender.setText(pojo.getGender());
        Log.i(TAG, "onBindViewHolder: gender:: " + pojo.getGender());

        Log.i(TAG, "onBindViewHolder: image:: " + pojo.getImage());
        Glide.with(mcontext)
                .load(pojo.getImage())
                .into(holder.take_image);
        holder.take_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.i(TAG, "onClick: ppp:::" + position);


            }
        });

        userInterfaceEvents.messageBackground((RelativeLayout) holder.relativeLayout, position);



//...................................................................................................................

    }


    public void setSearchOpration(List<UserDataPojo> newlist) {
        dataPojoList = new ArrayList<>();

        dataPojoList.clear();
        dataPojoList.addAll(newlist);

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataPojoList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, email, gender, age, phone_no;
        ImageView take_image, deleteUser;
        View relativeLayout;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_name);
            email = itemView.findViewById(R.id.rv_email);
            age = itemView.findViewById(R.id.rv_age);
            phone_no = itemView.findViewById(R.id.rv_phone_no);
            gender = itemView.findViewById(R.id.rv_gender);
            take_image = itemView.findViewById(R.id.rv_imagee);
            relativeLayout = itemView.findViewById(R.id.rv_relative);
            deleteUser = itemView.findViewById(R.id.rv_deleteuser);

            relativeLayout.setOnClickListener(this);
            take_image.setOnClickListener(this);

            deleteUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    userInterfaceEvents.onDeleteEvents(dataPojoList.get(position), position);
                }
            });
        }

        public void onClick(View view) {
            int position = getAdapterPosition();
            userInterfaceEvents.onUpdateEvents(dataPojoList.get(position));

        }

    }

    public interface UserInterfaceEvents {

        void onUpdateEvents(UserDataPojo pojo);

        void onDeleteEvents(UserDataPojo pojo, int pos);

        void messageBackground(RelativeLayout relativeLayout, int pos);
    }
}
