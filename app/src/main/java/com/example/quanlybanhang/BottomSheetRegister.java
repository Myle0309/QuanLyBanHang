package com.example.quanlybanhang;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlybanhang.model.User;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetRegister extends BottomSheetDialogFragment {

    private View view;
    ICallbackRegister iCallbackRegister;
    private EditText edtInputUsername, edtInputPassword, edtInputPhone, edtInputName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext(), getTheme());
        dialog.setOnShowListener(dialog1 -> {
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog1;
            View parentLayout =
                    bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (parentLayout != null) {
                BottomSheetBehavior<View> behaviour = BottomSheetBehavior.from(parentLayout);
                setupFullHeight(parentLayout);
                behaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
                behaviour.setPeekHeight(2000);
            }
        });
        return dialog;
    }

    private void setupFullHeight(View parentLayout) {
        ViewGroup.LayoutParams layoutParams = parentLayout.getLayoutParams();
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        parentLayout.setLayoutParams(layoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_sheet_register, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        edtInputUsername = view.findViewById(R.id.edtInputUsername);
        edtInputPassword = view.findViewById(R.id.edtInputPassword);
        edtInputPhone = view.findViewById(R.id.edtInputPhone);
        edtInputName = view.findViewById(R.id.edtInputName);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((Button) view.findViewById(R.id.btnRegister)).setOnClickListener(v -> {
            if (validateForm() == 1) {
                User nguoiDung = new User(
                        edtInputUsername.getText().toString(),
                        edtInputPassword.getText().toString(),
                        edtInputName.getText().toString(),
                        edtInputPhone.getText().toString()
                );
                if (iCallbackRegister != null) {
                    iCallbackRegister.onSuccess(nguoiDung);
                    dismiss();
                }
            }
        });
        ((View) view.findViewById(R.id.btnBack)).setOnClickListener(v -> {
            if (iCallbackRegister != null) {
                iCallbackRegister.onFailure();
                dismiss();
            }
        });
    }

    private int validateForm() {
        int check = 1;
        if (edtInputUsername.getText().length() == 0
                || edtInputPassword.getText().length() == 0
                || edtInputPhone.getText().length() == 0
                || edtInputName.getText().length() == 0
        ) {
            Toast.makeText(requireContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    interface ICallbackRegister {
        void onSuccess(User nguoiDung);

        void onFailure();
    }
}
