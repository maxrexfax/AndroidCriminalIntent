package com.example.criminalintent;

import android.util.Log;

import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {
    public static final String KEY_FOR_TEXT = "text.send";
    public static final String KEY_FOR_CHECKED = "check.send";
    private final String TAG = "CrimeApp";

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        logDataInCrimeListActivity("CrimeListActivity onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        logDataInCrimeListActivity("CrimeListActivity onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        logDataInCrimeListActivity("CrimeListActivity onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        logDataInCrimeListActivity("CrimeListActivity onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logDataInCrimeListActivity("CrimeListActivity onDestroy() called");
    }

    private void logDataInCrimeListActivity(String text) {
        //Log.d(TAG, text);
    }
}