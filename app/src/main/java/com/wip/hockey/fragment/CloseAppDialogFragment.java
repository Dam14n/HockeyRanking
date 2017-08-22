package com.wip.hockey.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.wip.hockey.R;

public class CloseAppDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.sign_out_dialog_fragment)
                .setPositiveButton( R.string.accept, (dialog, id) -> {
                    getActivity().finishAffinity();
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // User cancelled the dialog
                });
        return builder.create();
    }
}
