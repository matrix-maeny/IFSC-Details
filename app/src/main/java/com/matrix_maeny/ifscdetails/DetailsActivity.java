package com.matrix_maeny.ifscdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.matrix_maeny.ifscdetails.databinding.ActivityDetailsBinding;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BankModel model = BankModel.model;

        if (model != null) {

            Objects.requireNonNull(getSupportActionBar()).setTitle(model.getIfsc().toUpperCase());
            binding.bankNameTv.setText(model.getBank());
            binding.bankCodeTv.setText(model.getBankCode());
            binding.branchTv.setText(model.getBranch());
            binding.micrTv.setText(model.getMicr().equals("") ? "Not provided" : model.getMicr());
            binding.addressTv.setText(model.getAddress());
            binding.centreTv.setText(model.getCentre());
            binding.districtTv.setText(model.getDistrict());
            binding.cityTv.setText(model.getCity());
            binding.stateTv.setText(model.getState());

            binding.rtgsTv.setText(model.isRtgs() ? "Yes" : "no");

            binding.swiftTv.setText(model.getSwift().equals("") ? "No Swift" : model.getSwift());


            binding.upiTv.setText(model.isUpi() ? "Yes" : "no");

            binding.neftTv.setText(model.isNeft() ? "Yes" : "no");

            binding.impsTv.setText(model.isImps() ? "Yes" : "No");


        }
    }
}