package ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class FragmentSlideitem2 extends Fragment {
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.slideit_ilayout_tem2, container, false);
       /* imageView = (ImageView) rootview.findViewById(R.id.imgcard);
        imageView.setAlpha(0.5f);*/
        return rootview;
    }

}

