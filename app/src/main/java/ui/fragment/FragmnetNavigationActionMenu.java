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

import ui.activity.DropDownMenuN;
import ui.activity.FloatinActionButton;
import ui.activity.NavigationMainActivity;
import ui.activity.SlidingMenuWithWebView;

public class FragmnetNavigationActionMenu extends Fragment {
    private Button b1, b2, b3, b4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.navigatio_action_menu_flotion_button, container, false);

        b1 = rootview.findViewById(R.id.navigation_drawer);
        b2 = rootview.findViewById(R.id.sliding_menu_with_web_view);
        b3 = rootview.findViewById(R.id.drop_down_menu_n);
        b4 = rootview.findViewById(R.id.floating_action_button);
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), NavigationMainActivity.class);
                startActivity(intent1);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent rintent = new Intent(getActivity(), SlidingMenuWithWebView.class);
                startActivity(rintent);
            }

        });


        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent tableintent = new Intent(getActivity(), DropDownMenuN.class);
                startActivity(tableintent);
            }

        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent tableintent = new Intent(getActivity(), FloatinActionButton.class);
                startActivity(tableintent);
            }

        });
    }
}
