package com.example.deveemlabs.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.deveemlabs.R;
import com.example.deveemlabs.model.Comments;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextComment;
    private TextInputLayout inputName,inputEmail,inputComment;
    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        editTextName = view.findViewById(R.id.ed_name);
        editTextEmail = view.findViewById(R.id.ed_email);
        editTextComment = view.findViewById(R.id.ed_comment);
        inputName = view.findViewById(R.id.inputLayout_name);
        inputEmail = view.findViewById(R.id.inputLayout_email);
        inputComment = view.findViewById(R.id.inputLayout_comment);
        builder.setView(view)
                .setTitle("Comments")
                .setNegativeButton("cancel", null)
                .setPositiveButton("ok", null);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> {

            Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view1 -> {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String comment = editTextComment.getText().toString().trim();
                if (name.isEmpty()) {
                    editTextName.setError("Enter username");
                    inputName.setEnabled(true);
                    return;
                }
                if (email.isEmpty()) {
                    inputEmail.setEnabled(true);
                    editTextEmail.setError("Enter mail");
                    return;
                }
                if (comment.isEmpty()) {
                    inputComment.setEnabled(true);
                    editTextComment.setError("The field cannot be empty");
                    return;
                }
                dialog.dismiss();
                Comments comments = new Comments(0, 0, name, email, comment);
                listener.applyTexts(comments);
            });
        });
        dialog.show();
        return dialog;
    }
    @Override
    public void onAttach(@NonNull Context context) {
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
