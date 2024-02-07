package com.sgcc.yzd.translate.retrofit;

import com.sgcc.yzd.translate.common.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    /**
     * 连接、读、写超时时间
     */
    private static final long CONNECT_TIMEOUT = 10L;
    private static final long READ_TIMEOUT = 15L;
    private static final long WRITE_TIMEOUT = 15;

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }

        }
        return apiService;
    }

    private Api() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://aip.baidubce.com/")
                .baseUrl(Constant.IOT_CENTRAL_CONTROL_PLATFORM_URL)
                // 添加转换器工厂
                .addConverterFactory(GsonConverterFactory.create())
                // 请求指定适配器RxJava，例如原本拿到的是Call<String>，使用rxjava的calladapter转换成Observable<String>。
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getHttpClient())
                .build();
        /* 动态代理生成网络访问对象 */
        apiService = retrofit.create(ApiService.class);
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                // 动态添加token
                // .addInterceptor(new TokenHeaderInterceptor)
                // 返回空字符串时显示
                // .addInterceptor(new NullResponseInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

}
