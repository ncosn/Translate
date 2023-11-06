package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.sgcc.yzd.translate.R;

public class FourthActivity extends AppCompatActivity {

    private SubsamplingScaleImageView ivFaceAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        ivFaceAgreement = findViewById(R.id.iv_face_agreement);

        ivFaceAgreement.setImage(ImageSource.asset("images/face_authentication_agreement.png"));

    }
}