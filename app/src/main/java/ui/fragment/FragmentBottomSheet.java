package ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentBottomSheet extends BottomSheetDialogFragment {
    TextView note, bluetooth, mail, share, copy, delete;
    BottomSheetBehavior mBehavior;

   /* public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;


         rootview = inflater.inflate(R.layout.bottom_sheet, container, false);
      *//*  BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(rootview);
        dialog.show();*//*

        note = rootview.findViewById(R.id.note);
        bluetooth = rootview.findViewById(R.id.bluetooth);
        mail = rootview.findViewById(R.id.mail);
        share = rootview.findViewById(R.id.share);
        copy = rootview.findViewById(R.id.copy);
        delete = rootview.findViewById(R.id.delete);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Note", Toast.LENGTH_SHORT).show();
            }
        });
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Bluetooth", Toast.LENGTH_SHORT).show();
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Mail", Toast.LENGTH_SHORT).show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Copy", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
            }
        });

        return rootview;
    }*/

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @SuppressLint("RestrictedApi")
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        dialog.setCanceledOnTouchOutside(false);
        View rootview = View.inflate(getActivity(), R.layout.bottom_sheet, null);
        dialog.setContentView(rootview);

        mBehavior = BottomSheetBehavior.from(((View) rootview.getParent()));
        if (mBehavior != null) {
            mBehavior.setPeekHeight(800);
            rootview.requestLayout();
        }


        note = rootview.findViewById(R.id.note);
        bluetooth = rootview.findViewById(R.id.bluetooth);
        mail = rootview.findViewById(R.id.mail);
        share = rootview.findViewById(R.id.share);
        copy = rootview.findViewById(R.id.copy);
        delete = rootview.findViewById(R.id.delete);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Note", Toast.LENGTH_SHORT).show();
            }
        });
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Bluetooth", Toast.LENGTH_SHORT).show();
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Mail", Toast.LENGTH_SHORT).show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Copy", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
