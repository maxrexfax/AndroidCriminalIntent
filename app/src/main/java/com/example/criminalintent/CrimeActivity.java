package com.example.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String TAG = "CrimeApp";
    public static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";
    public static final String EXTRA_CRIME_TEXT = "crime.text";
    private final String TEXT_KEY = "Crime.App.Key";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        //Log.d(TAG, "CrimeActivity public static Intent newIntent crimeId=" + crimeId);
        return intent;
    }

    public static Intent newIntentFromCF(Context packageContext, String text) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_TEXT, text);
        //Log.d(TAG, "CrimeActivity public static Intent newIntentFromCF text:" + text);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    @Override
    public void onResume() {//to return edited data from CrimeActivity
        super.onResume();
        logDataInCrimeActivity("CrimeActivity onResume() called");
    }
    @Override
    public void onStart() {
        super.onStart();
        logDataInCrimeActivity("CrimeActivity onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        logDataInCrimeActivity("CrimeActivity onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        logDataInCrimeActivity("CrimeActivity onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logDataInCrimeActivity("CrimeActivity onDestroy() called");
    }

    private void logDataInCrimeActivity(String text) {
        //Log.d(TAG, text);
    }
}