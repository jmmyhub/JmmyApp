package com.jmmy.jmmyapp.ui.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.jmmy.jmmyapp.BR;
import com.jmmy.jmmyapp.JmmyApplication;
import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.database.DataEntity;
import com.jmmy.jmmyapp.database.JmmyDatabase;
import com.jmmy.jmmyapp.databinding.ActivityDemoBinding;
import com.jmmy.jmmyapp.utils.LogUtils;
import com.jmmy.mvvmhabit.base.BaseActivity;
import com.jmmy.mvvmhabit.http.DownLoadManager;
import com.jmmy.mvvmhabit.http.download.ProgressCallBack;
import com.jmmy.mvvmhabit.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class DemoActivity extends BaseActivity<ActivityDemoBinding, DemoViewModel> {
    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        testDatabase();
    }

    private void testDatabase() {
        LogUtils.i("LoginViewModel","loginOnClickCommand save user data.");
        new Thread(() -> {
            LogUtils.i("LoginViewModel","loginOnClickCommand");
            DataEntity entity = new DataEntity("test2", "12346");
            JmmyDatabase.getInstance(JmmyApplication.getInstance().getApplicationContext()).dataDao().insert(entity);
            List<DataEntity> list = JmmyDatabase.getInstance(JmmyApplication.getInstance().getApplicationContext()).dataDao().getDateEntity();
            for (DataEntity entity2: list) {
                LogUtils.i("LoginViewModel","list1 :" + entity2);
            }
            DataEntity entity1 = JmmyDatabase.getInstance(JmmyApplication.getInstance().getApplicationContext()).dataDao().getDateEntityByName("test2");
            JmmyDatabase.getInstance(JmmyApplication.getInstance().getApplicationContext()).dataDao().delete(entity1);

            List<DataEntity> list1 = JmmyDatabase.getInstance(JmmyApplication.getInstance().getApplicationContext()).dataDao().getDateEntity();
            for (DataEntity entity3: list1) {
                LogUtils.i("LoginViewModel","list2 :" + entity3);
            }

        }).start();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_demo;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        //注册监听相机权限的请求
        viewModel.requestCameraPermissions.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                requestCameraPermissions();
            }
        });
        //注册文件下载的监听
        viewModel.loadUrlEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String url) {
                downFile(url);
            }
        });
    }

    /**
     * 请求相机权限
     */
    private void requestCameraPermissions() {
        //请求打开相机权限
        RxPermissions rxPermissions = new RxPermissions(DemoActivity.this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            ToastUtils.showShort("相机权限已经打开，直接跳入相机");
                        } else {
                            ToastUtils.showShort("权限被拒绝");
                        }
                    }
                });
    }

    private void downFile(String url) {
        String destFileDir = getApplication().getCacheDir().getPath();
        String destFileName = System.currentTimeMillis() + ".apk";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        DownLoadManager.getInstance().load(url, new ProgressCallBack<ResponseBody>(destFileDir, destFileName) {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                progressDialog.dismiss();
            }

            @Override
            public void onSuccess(ResponseBody responseBody) {
                ToastUtils.showShort("文件下载完成！");
            }

            @Override
            public void progress(final long progress, final long total) {
                progressDialog.setMax((int) total);
                progressDialog.setProgress((int) progress);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ToastUtils.showShort("文件下载失败！");
                progressDialog.dismiss();
            }
        });
    }
}
