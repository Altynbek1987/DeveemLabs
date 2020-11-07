package com.example.deveemlabs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.deveemlabs.model.Comments;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextComment;
    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog,null);
        builder.setView(view)
                .setTitle("Comments")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editTextName.getText().toString();
                        String email = editTextEmail.getText().toString();
                        String comment = editTextComment.getText().toString();
                        Comments comments = new Comments(0,0,name, email, comment);
                        listener.applyTexts(comments);
                    }
                });
        editTextName = view.findViewById(R.id.ed_name);
        editTextEmail = view.findViewById(R.id.ed_email);
        editTextComment = view.findViewById(R.id.ed_comment);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(Comments comments);
    }
}
