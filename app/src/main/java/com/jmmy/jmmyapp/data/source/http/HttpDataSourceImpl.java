package com.jmmy.jmmyapp.data.source.http;

import com.jmmy.jmmyapp.data.source.HttpDataSource;
import com.jmmy.jmmyapp.data.source.http.service.DemoApiService;
import com.jmmy.jmmyapp.entity.DemoEntity;
import com.jmmy.mvvmhabit.http.BaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class HttpDataSourceImpl implements HttpDataSource {
    private DemoApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(DemoApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(DemoApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Object> login() {
        return Observable.just(new Object()).delay(3, TimeUnit.SECONDS); //延迟3秒
    }

    @Override
    public Observable<DemoEntity> loadMore() {
        return Observable.create((ObservableOnSubscribe<DemoEntity>) observableEmitter -> {
            DemoEntity entity = new DemoEntity();
            List<DemoEntity.ItemsEntity> itemsEntities = new ArrayList<>();
            //模拟一部分假数据
            for (int i = 0; i < 10; i++) {
                DemoEntity.ItemsEntity item = new DemoEntity.ItemsEntity();
                item.setId(-1);
                item.setName("模拟条目");
                itemsEntities.add(item);
            }
            entity.setItems(itemsEntities);
            observableEmitter.onNext(entity);
        }).delay(3, TimeUnit.SECONDS); //延迟3秒
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoGet() {
        return apiService.demoGet();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoPost(String catalog) {
        return apiService.demoPost(catalog);
    }
}
