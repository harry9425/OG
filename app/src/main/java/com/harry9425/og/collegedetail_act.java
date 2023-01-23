package com.harry9425.og;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class collegedetail_act extends AppCompatActivity {

    public static ConstraintLayout constraintLayout;

    ImageButton updown,updownin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegedetail);
        getSupportActionBar().hide();
        Fragment exampleFragment = new collegedetail_reg();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).replace(R.id.fragmentContainerView_collegedetail_act,exampleFragment).commit();
        constraintLayout=(ConstraintLayout) findViewById(R.id.cons_collegedetail_act);
        constraintLayout.setVisibility(View.VISIBLE);
        Animation in= AnimationUtils.loadAnimation(this,R.anim.slideup);
        Animation out= AnimationUtils.loadAnimation(this,R.anim.slidedown);
        updown=(ImageButton) findViewById(R.id.updown_collegedetail_act);
        updown.setVisibility(View.GONE);
        updownin=(ImageButton) findViewById(R.id.updown_collegedetail_act2);
        updownin.setVisibility(View.VISIBLE);
        updownin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.startAnimation(out);
                constraintLayout.setVisibility(View.GONE);
                updown.setVisibility(View.VISIBLE);
            }
        });
        updown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    updown.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.VISIBLE);
                    constraintLayout.startAnimation(in);
            }
        });
    }

}