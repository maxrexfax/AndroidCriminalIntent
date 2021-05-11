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

public class CrimeFragment extends Fragment {
    private Crime _Crime;
    private EditText _TitleField;
    private Button _DateButton;
    private CheckBox _SolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _Crime = new Crime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        _TitleField = (EditText)v.findViewById(R.id.crime_title);
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
