package com.sgcc.yzd.translate.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.activity.NavigationActivity;

public class BillDetailInfoFragment extends Fragment {
    private NavController navController;
    private NavigationActivity activity;
    TextView tvSeekbarNumber,tvOther;
    SeekBar sbLadderPower;
    Button btBack,btNext,btShow,btHide;
    Button btTwenty,btThirty,btSixty,btEighty;
    private int seekbarNumber;
    LinearLayout llSb;
    CheckBox cbTest;

    public BillDetailInfoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_bill_detail_info, container, false);
        activity = (NavigationActivity) getActivity();
        navController = activity.navController;
        tvSeekbarNumber = view.findViewById(R.id.tv_electricity_count);
        tvOther = view.findViewById(R.id.tv_other);
        sbLadderPower = view.findViewById(R.id.sb_ladder_power);
        btBack = view.findViewById(R.id.bt_back);
        btNext = view.findViewById(R.id.bt_next);
        btShow = view.findViewById(R.id.bt_show);
        btHide = view.findViewById(R.id.bt_hide);
        btTwenty = view.findViewById(R.id.bt_twenty);
        btThirty = view.findViewById(R.id.bt_thirty);
        btSixty = view.findViewById(R.id.bt_sixty);
        btEighty = view.findViewById(R.id.bt_eighty);
        llSb = view.findViewById(R.id.ll_sb);
        cbTest = view.findViewById(R.id.cb_test);

        initView();
        return view;
    }

    public void initView() {
        llSb.setVisibility(View.GONE);
        btBack.setOnClickListener((v)->{
            FragmentUtils.remove(this);
            navController.navigate(R.id.billFirstInputNumberFragment);
        });
        btNext.setOnClickListener((v)->{
            FragmentUtils.remove(this);
            navController.navigate(R.id.billPrintFragment);
        });
        btShow.setOnClickListener((v)-> {
            llSb.setVisibility(View.VISIBLE);
        });
        btHide.setOnClickListener(view -> {
            llSb.setVisibility(View.GONE);
        });
        btTwenty.setOnClickListener(view -> {
            sbLadderPower.setProgress(20);
            seekbarNumber = 20;
            tvOther.setText("3321432");
        });
        btThirty.setOnClickListener(view -> {
            sbLadderPower.setProgress(30);
            seekbarNumber = 30;
        });
        btSixty.setOnClickListener(view -> {
            sbLadderPower.setProgress(60);
            seekbarNumber = 60;
        });
        btEighty.setOnClickListener(view -> {
            sbLadderPower.setProgress(80);
            seekbarNumber = 80;
        });
        sbLadderPower.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        sbLadderPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ThreadUtils.runOnUiThreadDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                        tvSeekbarNumber.setText("" + seekbarNumber);
                        tvSeekbarNumber.setX(30 + val + seekBar.getThumbOffset() / 2 - 40);

                    }
                }, 300);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        cbTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tvOther.setText(b?"true":"false");
            }
        });
//        seekbarNumber = 60;
//        sbLadderPower.setProgress(60);
    }

}