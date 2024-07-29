package com.example.todolist;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";
    private EditText newTaskText;
    private Button newTaskSaveButton;

    public static AddNewTask newInstance() {
        return new AddNewTask();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTaskText = requireView().findViewById(R);
        newTaskSaveButton = requireView().findViewById(R);

        // Initialize the save button
        newTaskSaveButton.setEnabled(false);
        newTaskSaveButton.setTextColor(Color.GRAY);

        newTaskText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if (s.toString().isEmpty()) {
                        newTaskSaveButton.setEnabled(false);
                        newTaskSaveButton.setTextColor(Color.GRAY);
                    } else {
                        newTaskSaveButton.setEnabled(true);
                        newTaskSaveButton.setTextColor(ContextCompat.getColor(requireContext(), R));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        newTaskSaveButton.setOnClickListener(v -> {
            String text = newTaskText.getText().toString();
            if (getActivity() instanceof DialogCloseListener) {
                ((DialogCloseListener) getActivity()).handleDialogClose(null, text);
            }
            dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener) {
            ((DialogCloseListener) activity).handleDialogClose(dialog, null);
        }
    }
}
