package com.sgcc.yzd.translate.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.activity.NavigationActivity;


public class BillPrintFragment extends Fragment {
    NavigationActivity activity;
    NavController navController;
    TextView tvInputHouseNumber;

    public BillPrintFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_print, container, false);
        activity = (NavigationActivity) getActivity();
        navController = activity.navController;
        tvInputHouseNumber = view.findViewById(R.id.tv_input_house_number);
        tvInputHouseNumber.setOnClickListener((v) -> {
            navController.navigate(R.id.billDetailInfoFragment);
        });
        return view;
    }
}