package ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import ui.activity.Absolute;
import ui.activity.Frame;
import ui.activity.HorizontalScrollView;
import ui.activity.ListViewActivity;
import ui.activity.RelativeLayoutActivity;
import ui.activity.ScrollView;
import ui.activity.SecondActivitiy;
import ui.activity.SimpleListView;
import ui.activity.TableLayout;
import ui.activity.ViewPosition;

public class FragmentButton extends Fragment {

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mylayout = inflater.inflate(R.layout.button, container, false);
        b1 = mylayout.findViewById(R.id.btn_linear);
        b2 = mylayout.findViewById(R.id.btn_relative);
        b3 = mylayout.findViewById(R.id.btn_table);
        b4 = mylayout.findViewById(R.id.btn_frame);
        b5 = mylayout.findViewById(R.id.btn_absolute);
        b6 = mylayout.findViewById(R.id.btn_scroll);
        b7 = mylayout.findViewById(R.id.btn_h_scroll);
        b8 = mylayout.findViewById(R.id.btn_simple_list);
        b9 = mylayout.findViewById(R.id.btn_list_view);
        b10 = mylayout.findViewById(R.id.btn_view_position);
        return mylayout;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), SecondActivitiy.class);
                startActivity(intent1);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent rintent = new Intent(getActivity(), RelativeLayoutActivity.class);
                startActivity(rintent);
            }

        });


        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent tableintent = new Intent(getActivity(), TableLayout.class);
                startActivity(tableintent);
            }

        });


        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent frameintent = new Intent(getActivity(), Frame.class);
                startActivity(frameintent);
            }

        });


        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent absoluteintent = new Intent(getActivity(), Absolute.class);
                startActivity(absoluteintent);
            }

        });


        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent scrollintent = new Intent(getActivity(), ScrollView.class);
                startActivity(scrollintent);
            }

        });


        b7.setOnClickListener( new View.OnClickListener() {
            public void onClick(View view) {
                Intent hscrollintent = new Intent(getActivity(), HorizontalScrollView.class);
                startActivity(hscrollintent);
            }

        });


        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent listintent = new Intent(getActivity(), SimpleListView.class);
                startActivity(listintent);
            }

        });


        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent listintent = new Intent(getActivity(), ListViewActivity.class);
                startActivity(listintent);
            }

        });


        b10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent viewposition = new Intent(getActivity(), ViewPosition.class);
                startActivity(viewposition);
            }

        });

    }
}