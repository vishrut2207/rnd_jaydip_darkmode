package ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.myapplication.R;
import com.optimumbrew.obglide.core.imageloader.GlideApp;

import java.util.List;

import pojo.EmployeeDetailsItem;
import pojo.FriendsItem;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {
    private static final String TAG = "EmployeeAdapter";
    private List<EmployeeDetailsItem> itemList;
    private final Activity context;


    public EmployeeAdapter(Activity context, List<EmployeeDetailsItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.employee_details_gson, parent, false);

        return new EmployeeAdapter.MyViewHolder(listItem);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final EmployeeDetailsItem employeeDetailsItem = itemList.get(position);
        holder.name.setText("Name:\n" + employeeDetailsItem.getName());
        holder.gender.setText("Gender:\n" + employeeDetailsItem.getGender());
        holder.eyecolor.setText("Eye Color: " + employeeDetailsItem.getEyeColor());
        holder.email.setText("Email: " + employeeDetailsItem.getEmail());
        holder.phoneno.setText("Phone No: " + employeeDetailsItem.getPhone());
        holder.company.setText("Company: " + employeeDetailsItem.getCompany());
        holder.address.setText("Address: " + employeeDetailsItem.getAddress());
//................................................................................
        List<String> tag = employeeDetailsItem.getTagss();
        String str_tag = "";
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < tag.size() - 1) {
            builder.append(tag.get(i));
            builder.append(str_tag).append(", ");
            i++;

        }
        builder.append(tag.get(i));
        String res = builder.toString();
        holder.tags.setText("Tag: [" + res + "]");
//....................................................................................
        List<FriendsItem> frd = employeeDetailsItem.getFriends();
        Log.i(TAG, "onBindViewHolder: frd:-" + frd);
        holder.nooffriend.setText("No of Friends: " + frd.size());
//......................................................................
        holder.favouritfruit.setText("Favourit Fruit: " + employeeDetailsItem.getFavoriteFruit());
        holder.balance.setText("Balance: " + employeeDetailsItem.getBalance());

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.start();


        GlideApp.with(context)
                .load(employeeDetailsItem.getUrl())
                .placeholder(circularProgressDrawable)
                .into(holder.imageView);

        final int index = employeeDetailsItem.getIndex();
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Name : " + employeeDetailsItem.getName() + "\nIndex : " + index, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, gender, eyecolor, email, phoneno, company, address, tags,
                nooffriend, favouritfruit, balance;
        ImageView imageView;
        View linear;

        MyViewHolder(@NonNull View vi) {
            super(vi);
            imageView = (ImageView) vi.findViewById(R.id.image);
            name = (TextView) vi.findViewById(R.id.name);
            gender = (TextView) vi.findViewById(R.id.gender);
            eyecolor = (TextView) vi.findViewById(R.id.eyecolor);
            email = (TextView) vi.findViewById(R.id.email);
            phoneno = (TextView) vi.findViewById(R.id.phoneNo);
            company = (TextView) vi.findViewById(R.id.company);
            address = (TextView) vi.findViewById(R.id.address);
            tags = (TextView) vi.findViewById(R.id.tags);
            nooffriend = (TextView) vi.findViewById(R.id.nooffriends);
            favouritfruit = (TextView) vi.findViewById(R.id.favouritefruit);
            balance = (TextView) vi.findViewById(R.id.balance);
            linear = (LinearLayout) itemView.findViewById(R.id.linearlayout);
        }
    }
}
