package com.example.criminalintent;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private Crime _Crime;
    private EditText _TitleField;
    private Button _DateButton;
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
            }

            @Override
            public void afterTextChanged(Editable c) {
                //
            }
        });
        String formattedDate = (android.text.format.DateFormat.format("EEEE, MMM dd, yyyy", _Crime.getDate())).toString();
        _DateButton = (Button)v.findViewById(R.id.crime_date);
        _DateButton.setText(formattedDate);//Tuesday, Jul 22, 2015
        _DateButton.setEnabled(false);

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
}