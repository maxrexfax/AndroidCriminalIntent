package com.example.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";
    private static final String ARG_DATE = "date";

    private DatePicker _DatePicker;
    private TimePicker _TimePicker;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity())
        .inflate(R.layout.dialog_date, null);

        _DatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        _DatePicker.init(year, month, day, null);
        _TimePicker = (TimePicker) v.findViewById(R.id.dialog_date_time_picker);
        _TimePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        _TimePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = _DatePicker.getYear();
                                int month = _DatePicker.getMonth();
                                int day = _DatePicker.getDayOfMonth();
                                int hours = _TimePicker.getHour();
                                int minutes = _TimePicker.getMinute();
                                Date date = new GregorianCalendar(year, month, day, hours, minutes).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
