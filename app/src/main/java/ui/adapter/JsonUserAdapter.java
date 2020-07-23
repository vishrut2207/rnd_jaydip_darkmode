package ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class JsonUserAdapter extends RecyclerView.Adapter<JsonUserAdapter.MyViewHolder> {


    private ArrayList<String> nameuser;
    private ArrayList<String> strength;
    private ArrayList<String> dose;
    private ArrayList<String> route;
    private ArrayList<String> sig;
    private ArrayList<String> pillCount;
    private ArrayList<String> refills;

    public JsonUserAdapter(Context context, ArrayList<String> nameuser,
                           ArrayList<String> strength, ArrayList<String> dose, ArrayList<String> route, ArrayList<String> sig, ArrayList<String> pillCount,
                           ArrayList<String> refills) {
        this.nameuser = nameuser;
        this.strength = strength;
        this.dose = dose;
        this.route = route;
        this.sig = sig;
        this.pillCount = pillCount;
        this.refills = refills;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.json, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

       /* holder.username.setText(nameuser.get(position));
        holder.strength.setText(strength.get(position));
        holder.dose.setText(dose.get(position));
        holder.route.setText(route.get(position));
        holder.sig.setText(sig.get(position));
        holder.pillCount.setText(pillCount.get(position));
        holder.refills.setText(refills.get(position));*/

    }


    @Override
    public int getItemCount() {
        return nameuser.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, strength, dose, route, sig, pillCount, refills;

        MyViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.username);
            strength = (TextView) itemView.findViewById(R.id.strength);
            dose = (TextView) itemView.findViewById(R.id.dose);
            route = (TextView) itemView.findViewById(R.id.route);
            sig = (TextView) itemView.findViewById(R.id.sig);
            pillCount = (TextView) itemView.findViewById(R.id.pillCount);
            refills = (TextView) itemView.findViewById(R.id.refills);
        }
    }
}