package com.jmmy.jmmyapp.data.source;

import com.jmmy.jmmyapp.entity.DemoEntity;
import com.jmmy.mvvmhabit.http.BaseResponse;

import io.reactivex.Observable;

public interface HttpDataSource {
    //模拟登录
    Observable<Object> login();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);


}