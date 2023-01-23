package com.harry9425.og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    String uid=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
       /* auth=FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user==null){
                    Intent i=new Intent(MainActivity.this,loginpage.class);
                    startActivity(i);
                    finish();
                }
                else {
                    uid=user.getUid();
                    Intent i=new Intent(MainActivity.this,entryscreen.class);
                   startActivity(i);
                }
            }
        });
        */
    }
}