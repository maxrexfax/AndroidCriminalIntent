package com.example.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private static final int REQUEST_CRIME = 1;
    private final String TAG = "CrimeApp";

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;


        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView
                    .findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView
                    .findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView
                    .findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
            logDataInCrimeListFragment("CrimeListFragment onClick() called");
            //Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
            //Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
            //startActivityForResult(intent, REQUEST_CRIME);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        logDataInCrimeListFragment("CrimeListFragment onActivityResult worked, requestCode=" + requestCode);

        if (requestCode == REQUEST_CRIME) {
            String textFromCrimeFragment = CrimeFragment.returnResult1(data);
            logDataInCrimeListFragment("CrimeListFragment onActivityResult() tmp:" + textFromCrimeFragment);
            //Log.d(TAG, "CrimeListFragment onActivityResult TITLE" + title + " isChecked:" + isSolved);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            //holder.mTitleTextView.setText(crime.getTitle());//only one textView
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    @Override
    public void onResume() {//to return edited data from CrimeActivity
        super.onResume();
        logDataInCrimeListFragment("CrimeListFragment onResume() called");
        updateUI();
    }
    @Override
    public void onStart() {
        super.onStart();
        logDataInCrimeListFragment("CrimeListFragment onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        logDataInCrimeListFragment("CrimeListFragment onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        logDataInCrimeListFragment("CrimeListFragment onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logDataInCrimeListFragment("CrimeListFragment onDestroy() called");
    }

    private void logDataInCrimeListFragment(String text) {
        //Log.d(TAG, text);
    }
}
