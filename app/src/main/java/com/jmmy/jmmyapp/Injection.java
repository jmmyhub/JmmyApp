package com.jmmy.jmmyapp;

import com.jmmy.jmmyapp.data.DemoRepository;
import com.jmmy.jmmyapp.data.source.HttpDataSource;
import com.jmmy.jmmyapp.data.source.LocalDataSource;
import com.jmmy.jmmyapp.data.source.http.HttpDataSourceImpl;
import com.jmmy.jmmyapp.data.source.http.service.DemoApiService;
import com.jmmy.jmmyapp.data.source.local.LocalDataSourceImpl;
import com.jmmy.jmmyapp.utils.RetrofitClient;

public class Injection {
    public static DemoRepository provideDemoRepository() {
        //网络API服务
        DemoApiService apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        return DemoRepository.getInstance(httpDataSource, localDataSource);
    }
}
