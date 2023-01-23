package com.harry9425.og;

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
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;

public class name_reg extends Fragment {

    public name_reg() {}

    EditText name;
    itemviewmodel viewModel;
    ImageButton error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_name_reg, container, false);
        ImageView imageView = view.findViewById(R.id.name_reg_image);
        name=view.findViewById(R.id.name_namereg);
        error=view.findViewById(R.id.namerror_namereg);
        error.setVisibility(View.VISIBLE);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"name can't be empty",Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(getContext())
                .load(R.raw.picentry)
                .transition(GenericTransitionOptions.with(android.R.anim.slide_in_left))
                .into(imageView);
        new CountDownTimer(800, 50) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                Glide.with(getContext())
                        .load(R.raw.picloop)
                        .into(imageView);
            }
        }.start();
        itemviewmodel itemviewmodel=new ViewModelProvider(getActivity()).get(itemviewmodel.class);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(name.getText().toString().trim().isEmpty()){
                    error.setVisibility(View.VISIBLE);
                }else {
                    error.setVisibility(View.INVISIBLE);
                    itemviewmodel.setUsername(name.getText().toString().trim());
                }
            }
        });
        return view;
    }
}