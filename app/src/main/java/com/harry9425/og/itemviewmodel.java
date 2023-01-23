package com.harry9425.og;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.PhoneAuthCredential;

public class itemviewmodel extends ViewModel {

    private final MutableLiveData<String> phone=new MutableLiveData<String>();
    private final MutableLiveData<String> otpverify=new MutableLiveData<String>();
    private final MutableLiveData<PhoneAuthCredential> credential=new MutableLiveData<PhoneAuthCredential>();
    private final MutableLiveData<String> username=new MutableLiveData<String>();
    private final MutableLiveData<String> data=new MutableLiveData<String>();

    public MutableLiveData<String> getphonedata() {
        return phone;
    }

    public void setphonedata(String d){
        phone.setValue(d);
    }

    public MutableLiveData<String> getOtpverify() {
        return otpverify;
    }

    public void setOtpverify(String d){
        otpverify.setValue(d);
    }

    public MutableLiveData<String> getData() {
        return data;
    }
    public void setData(String d){
        data.setValue(d);
    }


    public MutableLiveData<PhoneAuthCredential> getCredential() {
        return credential;
    }

    public void setCredential(PhoneAuthCredential d){
        credential.setValue(d);
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }
    public void setUsername(String d){
        username.setValue(d);
    }
}
