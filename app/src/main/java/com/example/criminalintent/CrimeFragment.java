package com.example.criminalintent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private final String TAG = "CrimeApp";
    private static final String TEXT_KEY = "Crime.App.Key";
    private Crime _Crime;
    private EditText _TitleField;
    private Button _DateButton;
    private Button _TimeButton;
    private CheckBox _SolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        _Crime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        _TitleField = (EditText)v.findViewById(R.id.crime_title);
        _TitleField.setText(_Crime.getTitle());
        _TitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence c, int start, int count, int after) {
                _Crime.setTitle(c.toString());
                saveDataToIntent(_TitleField.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable c) {
                //
            }
        });
        String formattedDate = (android.text.format.DateFormat.format("EEEE, MMM dd, yyyy", _Crime.getDate())).toString();
        _DateButton = (Button)v.findViewById(R.id.crime_date);
        _DateButton.setText(formattedDate);//Tuesday, Jul 22, 2015
        _DateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDateAndTime();
            }
        });

        String formattedTime = (android.text.format.DateFormat.format("HH:mm:ss", _Crime.getDate())).toString();
        _TimeButton = (Button)v.findViewById(R.id.crime_time);
        _TimeButton.setText("Time:" + formattedTime);
        _TimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateAndTime();
            }
        });

        _SolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        _SolvedCheckBox.setChecked(_Crime.isSolved());
        
        _SolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Назначение флага раскрытия преступления
                _Crime.setSolved(isChecked);
            }
        });

        return v;
    }

    private void setDateAndTime() {
        FragmentManager manager = getFragmentManager();
        DatePickerFragment dialog = DatePickerFragment.newInstance(_Crime.getDate());
        dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);//сам себя назначил целевым фрагментом
        dialog.show(manager, DIALOG_DATE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            _Crime.setDate(date);
            _DateButton.setText(_Crime.getDate().toString());
            _TimeButton.setText((android.text.format.DateFormat.format("HH:mm:ss", _Crime.getDate())).toString());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        logDataInCrimeFragment("CrimeFragment onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        logDataInCrimeFragment("CrimeFragment onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        logDataInCrimeFragment("CrimeFragment onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        returnResult();
        saveDataToIntent(_TitleField.getText().toString());
        logDataInCrimeFragment("CrimeFragment onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "CrimeFragment onDestroy() called");
    }

    public void returnResult() {
        Intent intent = _CrimeActivity.newIntentFromCF(getActivity(), "Test text from CrimeFragment");
    }

    public static String returnResult1(Intent result) {
        return result.getStringExtra(TEXT_KEY);
    }

    public void saveDataToIntent(String textToSend) {
        Intent data = new Intent();
        data.putExtra(TEXT_KEY, textToSend);
        getActivity().setResult(RESULT_OK, data);
    }

    private void logDataInCrimeFragment(String text) {
        //Log.d(TAG, text);
    }
}