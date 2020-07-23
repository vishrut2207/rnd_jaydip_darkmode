package ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import ui.activity.DialogControl;
import ui.activity.SnackbarControl;
import ui.activity.ToastControl;

public class FragmentToastDialogSnackbar extends Fragment {
    Button toast, dialog, snackbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.toast_dialog_snackbar_control, container, false);
        toast = rootview.findViewById(R.id.btn_toast);
        dialog = rootview.findViewById(R.id.btn_dialog);
        snackbar = rootview.findViewById(R.id.btn_snackbar);

        return rootview;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ToastControl.class);
                startActivity(intent);
            }
        });
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DialogControl.class);
                startActivity(intent);
            }
        });
        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SnackbarControl.class);
                startActivity(intent);
            }
        });
    }

}
