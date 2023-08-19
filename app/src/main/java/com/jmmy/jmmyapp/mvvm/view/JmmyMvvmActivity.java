package com.jmmy.jmmyapp.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.databinding.ActivityMvvmBinding;
import com.jmmy.jmmyapp.mvvm.viewmodel.JmmyMvvmViewModel;
import com.jmmy.jmmyapp.utils.LogUtils;

public class JmmyMvvmActivity extends AppCompatActivity {
    private static String TAG = "JmmyMvvmActivity:";
    private JmmyMvvmViewModel mvvmViewModel;
    private ActivityMvvmBinding mMvvmBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        mvvmViewModel = new ViewModelProvider(this).get(JmmyMvvmViewModel.class);
        mvvmViewModel.getAccountMutableLiveData().observe(this, str -> {
            LogUtils.i(TAG, "account onChange:" + str);
            mMvvmBinding.editTextAccount.setText("" + str);
        });
        mvvmViewModel.getAccountMutableLiveData().observe(this, str -> {
            LogUtils.i(TAG, "pwd onChange:" + str);
            mMvvmBinding.editTextPwd.setText("" + str);
        });
        mvvmViewModel.getAccountMutableLiveData().postValue("");
    }
}