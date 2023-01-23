package com.harry9425.og;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class entryscreen extends AppCompatActivity {

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entryscreen);
        getSupportActionBar().hide();
        cardView=(CardView) findViewById(R.id.cardview_entry);
        Animation btnanim=AnimationUtils.loadAnimation(this,R.anim.growingrowout);
       // cardView.setAnimation(btnanim);
        ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressbar_entry);
        progressBar.setProgress(0);
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
        ImageView imageFlipper1 = (ImageView) findViewById(R.id.flipper1);
        ImageView imageFlipper2 = (ImageView) findViewById(R.id.flipper2);
        ImageView imageFlipper3 = (ImageView) findViewById(R.id.flipper3);
        ImageView imageFlipper4 = (ImageView) findViewById(R.id.flipper4);
        ImageView imageFlipper5 = (ImageView) findViewById(R.id.flipper5);
        Glide.with(this)
                .load(R.raw.telework)
                .into(imageFlipper1);
        new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                Glide.with(getApplicationContext()).load(R.raw.teleworkloop).into(imageFlipper1);
                Glide.with(getApplicationContext()).load(R.raw.programmingentry).into(imageFlipper2);
                Glide.with(getApplicationContext()).load(R.raw.groupvideoentry).into(imageFlipper3);
                Glide.with(getApplicationContext()).load(R.raw.onlinelearningentry).into(imageFlipper4);
                Glide.with(getApplicationContext()).load(R.raw.codingentry).into(imageFlipper5);
                progressBar.setMax(3000);
                progressBar.setProgress(0);
                Timer timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if(progressBar.getProgress()<progressBar.getMax())
                            progressBar.setProgress(progressBar.getProgress()+10);
                        else {
                            progressBar.setProgress(0);
                        }
                    }
                }, 0, 10);
                flipper.setAutoStart(true);
                flipper.setFlipInterval(3000);
                Animation in = AnimationUtils.loadAnimation(entryscreen.this, android.R.anim.fade_in);
                Animation out = AnimationUtils.loadAnimation(entryscreen.this, android.R.anim.fade_out);
                flipper.setInAnimation(in);
                flipper.setOutAnimation(out);
                flipper.startFlipping();
            }
        }.start();
    }


    public void next(View view){
        Intent i=new Intent(this,registermember.class);
        startActivity(i);
    }
}