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

import ui.activity.ButtonAndroid;
import ui.activity.Checkbox;
import ui.activity.DateView;
import ui.activity.Edittext;
import ui.activity.ImageButton;
import ui.activity.Radiob;
import ui.activity.RatingB;
import ui.activity.SpinnerL;
import ui.activity.SwitchB;
import ui.activity.TimeView;
import ui.activity.ToggleB;

public class FragmentInputControl extends Fragment {

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mylayout = inflater.inflate(R.layout.input_control, container, false);
        b1 = mylayout.findViewById(R.id.android_button);
        b2 = mylayout.findViewById(R.id.image_button);
        b3 = mylayout.findViewById(R.id.edit_text);
        b4 = mylayout.findViewById(R.id.checkbox_button);
        b5 = mylayout.findViewById(R.id.radio_button);
        b6 = mylayout.findViewById(R.id.switch_button);
        b7 = mylayout.findViewById(R.id.toggle);
        b8 = mylayout.findViewById(R.id.rating_bar);
        b9 = mylayout.findViewById(R.id.spinner);
        b10 = mylayout.findViewById(R.id.date);
        b11 = mylayout.findViewById(R.id.time);
        return mylayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ButtonAndroid.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImageButton.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edittext.class);
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Checkbox.class);
                startActivity(intent);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Radiob.class);
                startActivity(intent);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SwitchB.class);
                startActivity(intent);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ToggleB.class);
                startActivity(intent);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RatingB.class);
                startActivity(intent);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SpinnerL.class);
                startActivity(intent);
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DateView.class);
                startActivity(intent);
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimeView.class);
                startActivity(intent);
            }
        });


    }
}
