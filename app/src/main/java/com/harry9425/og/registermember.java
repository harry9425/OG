package com.harry9425.og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;

import java.util.Random;

public class registermember extends AppCompatActivity {

    FragmentContainerView fragmentContainerView;
    int i=1;
    itemviewmodel viewModel;
    CardView cardView;
    String phone="";
    String name;
    PhoneAuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registermember);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        cardView=(CardView) findViewById(R.id.continue_btn);
        cardView.setEnabled(false);
        cardView.setAlpha(0.8f);
        fragmentContainerView=(FragmentContainerView) findViewById(R.id.fragmentContainerView);
        viewModel=new ViewModelProvider(this).get(itemviewmodel.class);
        viewModel.getphonedata().observe(this,item->{
            if(item.toString().isEmpty()){
                cardView.setEnabled(false);
                cardView.setAlpha(0.8f);
                phone=item;
            }
            else {
                cardView.setEnabled(true);
                cardView.setAlpha(1f);
            }
        });
        viewModel.getOtpverify().observe(this,item->{
            cardView.setEnabled(false);
            cardView.setAlpha(0.8f);
            if (item.equals("correct")){
                cardView.setEnabled(true);
                cardView.setAlpha(1f);
            }
            else if(item.equals("startdance")){
                Fragment exampleFragment = new name_reg();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right).replace(fragmentContainerView.getId(),exampleFragment).commit();
                i=3;
            }

        });
        viewModel.getCredential().observe(this,item->{
            credential=item;
            cardView.setEnabled(true);
            cardView.setAlpha(1f);
        });
        viewModel.getUsername().observe(this,item->{
            name=item;
            i=3;
            cardView.setEnabled(true);
            cardView.setAlpha(1f);
        });
    }

    public void next(View view){
        if(i==1) {
            Fragment exampleFragment = new otpverify_reg();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,
                                android.R.anim.slide_out_right)
                        .replace(fragmentContainerView.getId(), exampleFragment).commit();
                i = 2;
        }
        else if(i==2) {
            viewModel.setOtpverify("checking");
            Random random=new Random();
            Toast.makeText(this,"Verifying...",Toast.LENGTH_SHORT).show();
            new CountDownTimer(random.nextInt(1500)+500, 100) {
                public void onTick(long millisUntilFinished) {}
                public void onFinish() {
                    signInTheUserByCredentials(credential);
                }
            }.start();
        }
        else if(i==3){
             Intent i=new Intent(this,collegedetail_act.class);
             startActivity(i);
        }
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(registermember.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                  viewModel.setOtpverify("completed");
                        } else {
                            viewModel.setOtpverify("failed");
                        }
                    }
                });
    }

}

