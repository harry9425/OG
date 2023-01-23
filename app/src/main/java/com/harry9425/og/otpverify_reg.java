package com.harry9425.og;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpverify_reg extends Fragment {

    private String phoneno;
    itemviewmodel viewModel;
    String verificationCodeBySystem;
    ImageView imageView;
    ImageButton state;
    PinView pinView;
    ProgressBar progressBar;
    PhoneAuthCredential credential;

    public otpverify_reg() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_otpverify_reg, container, false);
         imageView= view.findViewById(R.id.otpverify_reg_image);
         pinView=view.findViewById(R.id.firstPinView);
         progressBar=view.findViewById(R.id.progressBar_otpverify);
         state=view.findViewById(R.id.statbtn_otpverify);
         state.setVisibility(View.INVISIBLE);
        viewModel=new ViewModelProvider(getActivity()).get(itemviewmodel.class);
        viewModel.getphonedata().observe(getActivity(),item->{
            phoneno=item;
        });
        viewModel.setOtpverify("wrong");
        Glide.with(getContext())
                .load(R.raw.enterotpentry)
                .transition(GenericTransitionOptions.with(android.R.anim.slide_in_left))
                .into(imageView);
        new CountDownTimer(1700, 100) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                Glide.with(view.getContext())
                        .load(R.raw.enterotploop)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView);
            }
        }.start();
        sendverificationcodetouser("+91"+phoneno);
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                state.setVisibility(View.INVISIBLE);
                Glide.with(view.getContext())
                        .load(R.raw.enterotploop)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView);
            if(pinView.getText().toString().trim().length()==6){
                progressBar.setVisibility(View.INVISIBLE);

                verifyCode(pinView.getText().toString().trim());
            }
            else{
                progressBar.setVisibility(View.VISIBLE);
                viewModel.setOtpverify("wrong");
            }
            }
        });
        viewModel.getOtpverify().observe(getActivity(),item->{
            if(item.equals("completed")){
                state.setVisibility(View.VISIBLE);
                state.setImageResource(R.drawable.ic_round_done_24);
                Glide.with(getContext())
                        .load(R.raw.verifiedotp)
                        .transition(GenericTransitionOptions.with(android.R.anim.slide_in_left))
                        .into(imageView);

                Toast.makeText(getContext(), "Verification completed...", Toast.LENGTH_SHORT).show();
                new CountDownTimer(900, 100) {
                    public void onTick(long millisUntilFinished) {}
                    public void onFinish() {
                        Glide.with(view.getContext())
                                .load(R.raw.verified)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(imageView);
                        Toast.makeText(getContext(), "Moving Forward", Toast.LENGTH_SHORT).show();
                        new CountDownTimer(500, 100) {
                            public void onTick(long millisUntilFinished) {}
                            public void onFinish() {
                               viewModel.setOtpverify("startdance");
                            }
                        }.start();
                    }

                }.start();
            }else if(item.equals("failed")){
                viewModel.setOtpverify("wrong");
                state.setVisibility(View.VISIBLE);
                state.setImageResource(R.drawable.ic_round_close_24);
                progressBar.setVisibility(View.VISIBLE);
                Glide.with(view.getContext())
                        .load(R.raw.sorry)
                        .transition(GenericTransitionOptions.with(android.R.anim.slide_in_left))
                        .into(imageView);
                Toast.makeText(getContext(), "WRONG OTP", Toast.LENGTH_SHORT).show();
            }
            else if(item.equals("checking")){
                state.setVisibility(View.INVISIBLE);
                Glide.with(getContext())
                        .load(R.raw.checking)
                        .transition(GenericTransitionOptions.with(android.R.anim.slide_in_left))
                        .into(imageView);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    private void sendverificationcodetouser(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,   // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken forceResendingToken)
        {
            super.onCodeSent(s,forceResendingToken);
            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if(code!= null) {
                pinView.setText(code);
              //  verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String codeByUser) {
       // Toast.makeText(getContext(),codeByUser+"\n"+verificationCodeBySystem,Toast.LENGTH_SHORT).show();
        try{
            credential= PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
            progressBar.setVisibility(View.INVISIBLE);
            state.setVisibility(View.INVISIBLE);
            viewModel.setOtpverify("correct");
            viewModel.setCredential(credential);
           // signInTheUserByCredentials(credential);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            viewModel.setOtpverify("wrong");
            state.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "WRONG OTP", Toast.LENGTH_SHORT).show();
        }
    }

}