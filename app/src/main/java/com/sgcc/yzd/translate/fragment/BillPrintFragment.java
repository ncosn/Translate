package com.sgcc.yzd.translate.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgcc.yzd.translate.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillPrintFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillPrintFragment extends Fragment {

    public BillPrintFragment() {
        // Required empty public constructor
    }

    public static BillPrintFragment newInstance() {
        BillPrintFragment fragment = new BillPrintFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_print, container, false);
    }
}