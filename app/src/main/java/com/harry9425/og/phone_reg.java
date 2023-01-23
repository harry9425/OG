package com.harry9425.og;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;


public class phone_reg extends Fragment {

    EditText phonenum;
    ImageButton error;

    public phone_reg() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_phone_reg, container, false);
        ImageView imageView = view.findViewById(R.id.phone_reg_image);
        phonenum=view.findViewById(R.id.phonenum_phonereg);
        error=view.findViewById(R.id.mobilerror_phonereg);
        error.setVisibility(View.INVISIBLE);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(error.getVisibility()==View.VISIBLE){
                    Toast.makeText(getActivity(),"Phone number must be of 10 digits",Toast.LENGTH_SHORT).show();
                }
            }
        });
        itemviewmodel itemviewmodel=new ViewModelProvider(getActivity()).get(itemviewmodel.class);
        phonenum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(phonenum.getText().toString().isEmpty()){
                    error.setVisibility(View.INVISIBLE);
                    itemviewmodel.setphonedata("");
                }
                else {
                    if (phonenum.getText().toString().trim().length() == 10) {
                        error.setVisibility(View.INVISIBLE);
                        itemviewmodel.setphonedata(phonenum.getText().toString().trim());
                    } else {
                        itemviewmodel.setphonedata("");
                        error.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        Glide.with(view.getContext())
                .load(R.raw.phoneregentry)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
        new CountDownTimer(1000, 50) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                Glide.with(view.getContext())
                        .load(R.raw.phoneregloop)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView);
            }

        }.start();
        return view;
    }

}