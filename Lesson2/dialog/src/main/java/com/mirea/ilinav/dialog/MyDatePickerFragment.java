package com.mirea.ilinav.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class MyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = String.format("%02d.%02d.%d", day, month + 1, year);
        FragmentActivity activity = getActivity();

        if (activity != null) {
            Snackbar snackbar = Snackbar.make(
                    activity.findViewById(android.R.id.content),
                    "Выбрана дата: " + date,
                    Snackbar.LENGTH_LONG
            );
            snackbar.show();
        }
    }
}