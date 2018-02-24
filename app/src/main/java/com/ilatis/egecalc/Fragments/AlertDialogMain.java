package com.ilatis.egecalc.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.ilatis.egecalc.R;

/**
 * Created by thymomenosgata on 22.02.18.
 */

public class AlertDialogMain extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.titleER);
        builder.setMessage(R.string.Aldial);
        return builder.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setCanceledOnTouchOutside(false);
    }
}
