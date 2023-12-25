package com.sgcc.yzd.translate.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.FragmentUtils;
import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.activity.FourthActivity;
import com.sgcc.yzd.translate.activity.NavigationActivity;
import com.sgcc.yzd.translate.databinding.FragmentBillFirstInputNumberBinding;

public class BillFirstInputNumberFragment extends Fragment {

    private NavController navController;
    private NavigationActivity activity;
    FragmentBillFirstInputNumberBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("FirstInput----------onCreateView----------");
        // Inflate the layout for this fragment
//        View root = inflater.inflate(R.layout.fragment_bill_first_input_number, container, false);
        binding = FragmentBillFirstInputNumberBinding.inflate(inflater,container,false);

        activity = (NavigationActivity) getActivity();
        navController = activity.navController;

        binding.tvHouseProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentUtils.remove(BillFirstInputNumberFragment.this);
                startActivity(new Intent(activity, FourthActivity.class));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navController.navigate(R.id.billFirstSelectProvinceFragment);
                    }
                }, 5000);
            }
        });

        binding.tvScanElectricCard.setOnClickListener((v)-> {
            navController.navigate(R.id.billDetailInfoFragment);
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        System.out.println("FirstInput----------onResume----------");
        super.onResume();
    }

    @Override
    public void onStart() {
        System.out.println("FirstInput----------onStart----------");
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        System.out.println("FirstInput----------onDestroyView----------");
        binding = null;
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        System.out.println("FirstInput----------onDetach----------");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        System.out.println("FirstInput----------onDestroy----------");
        super.onDestroy();
    }
}