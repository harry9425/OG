package com.harry9425.og;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class collegedetail_reg extends Fragment {

    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Toolbar mToolbar;
    EditText collegename,prn;
    ImageView med,eng,law,arch,phot,other;
    SeekBar year;
    TextView yeardis,domaindis;


    public collegedetail_reg() {}

    private enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_collegedetail_reg, container, false);
        collapsingToolbarLayout=view.findViewById(R.id.collapsing_collegedetail_reg);
        appBarLayout=view.findViewById(R.id.appBarLayout_collegedetail_reg);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            private State state;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != State.EXPANDED) {

                    }
                    state = State.EXPANDED;
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != State.COLLAPSED) {

                    }
                    state = State.COLLAPSED;
                } else {
                    if (state != State.IDLE) {
                        //Toast.makeText(getContext(),"idle",Toast.LENGTH_SHORT).show();
                    }
                    state = State.IDLE;
                }
            }
        });
        initaitetrans(view);

        return view;
    }

    void initaitetrans(View view){
        ImageView imageView = view.findViewById(R.id.collegedetail_imageview);
        Glide.with(getContext())
                .load(R.raw.resume_entry)
                .into(imageView);
        new CountDownTimer(800, 50) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                Glide.with(getContext())
                        .load(R.raw.resume_loop)
                        .into(imageView);
            }

        }.start();
        ImageView formview = view.findViewById(R.id.form_collegedetail_reg_view);
        Glide.with(getContext())
                .load(R.raw.forms)
                .into(formview);
        ImageView eng = view.findViewById(R.id.eng_dom);
        Glide.with(getContext())
                .load(R.raw.engineering)
                .into(eng);
        ImageView med = view.findViewById(R.id.medical_dom);
        Glide.with(getContext())
                .load(R.raw.medical)
                .into(med);
        ImageView law = view.findViewById(R.id.law_dom);
        Glide.with(getContext())
                .load(R.raw.law)
                .into(law);
        ImageView arc = view.findViewById(R.id.archi_dom);
        Glide.with(getContext())
                .load(R.raw.architect)
                .into(arc);
        ImageView pic = view.findViewById(R.id.photo_dom);
        Glide.with(getContext())
                .load(R.raw.photography)
                .into(pic);
        ImageView other = view.findViewById(R.id.otherdom);
        Glide.with(getContext())
                .load(R.raw.otherdomain)
                .into(other);
        ImageView other2 = view.findViewById(R.id.form_collegedetail_reg_view2);
        Glide.with(getContext())
                .load(R.raw.digitalpresentation)
                .into(other2);
    }

    public void enter(View view){
        Intent i=new Intent(getContext(),MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(i);
    }
}