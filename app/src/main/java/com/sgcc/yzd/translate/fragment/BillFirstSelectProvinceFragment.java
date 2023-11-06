package com.sgcc.yzd.translate.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.FragmentUtils;
import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.activity.NavigationActivity;
import com.sgcc.yzd.translate.databinding.FragmentBillFirstInputNumberBinding;
import com.sgcc.yzd.translate.databinding.FragmentBillFirstSelectProvinceBinding;

public class BillFirstSelectProvinceFragment extends Fragment {

    private NavController navController;
    private NavigationActivity activity;
    FragmentBillFirstSelectProvinceBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("SelectProvince----------onCreate----------");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("SelectProvince----------onCreateView----------");
        // Inflate the layout for this fragment
        binding = FragmentBillFirstSelectProvinceBinding.inflate(inflater, container, false);

        activity = (NavigationActivity) getActivity();
        navController = activity.navController;

        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentUtils.remove(BillFirstSelectProvinceFragment.this);
                navController.navigate(R.id.billFirstInputNumberFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        System.out.println("SelectProvince----------onDestroyView----------");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("SelectProvince----------onDestroy----------");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println("SelectProvince----------onDetach----------");
        super.onDetach();
    }
}