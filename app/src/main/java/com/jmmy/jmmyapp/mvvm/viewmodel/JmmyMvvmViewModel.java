package com.jmmy.jmmyapp.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JmmyMvvmViewModel extends ViewModel {

    private MutableLiveData<String> account = new MutableLiveData<>();

    private MutableLiveData<String> pwd = new MutableLiveData<>();

    public MutableLiveData<String> getAccountMutableLiveData(){
        return account;
    }

    public MutableLiveData<String> getPwdMutableLiveData(){
        return pwd;
    }
}
