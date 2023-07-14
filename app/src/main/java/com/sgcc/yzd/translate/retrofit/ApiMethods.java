package com.sgcc.yzd.translate.retrofit;

import com.sgcc.yzd.translate.model.ResponseData;
import com.sgcc.yzd.translate.model.TranslationResponse;
import com.sgcc.yzd.translate.rxjava.SchedulerTransformer;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ApiMethods {

    public static ApiMethods newInstance() {
        ApiMethods apiMethods = new ApiMethods();
        return apiMethods;
    }

    /**
     * @param observable 被观察者，网络请求方法
     * @param observer 观察者，调用 ApiMethods 时传入
     */
    private static void apiSubscribe(Observable observable, Observer observer) {
        observable.compose(new SchedulerTransformer())
                .subscribe(observer);
    }

//    /**
//     * @param observable 被观察者，网络请求方法
//     * @param observer 观察者，调用 ApiMethods 时传入
//     */
//    private static void apiSubscribe(Observable observable, Observer observer) {
//        Observable.just(observable)
//                .compose(new SchedulerTransformer())
//                .subscribe(observer);
//    }

    // 进行订阅
    public void postAccessToken(Observer observer, Map<String,String> map) {
        apiSubscribe(Api.getApiService().getAccessToken(map.get("grantType"),map.get("apiKey"),map.get("secretKey")), observer);
    }
    public void postTranslate(Observer observer, RequestBody body) {
        apiSubscribe(Api.getApiService().translate(body), observer);
    }

    /**
     * 二次请求，第一次获取鉴权参数，第二次翻译文本
     *
     * @param observer
     * @param map
     */
    public void postTranslateAccessToken(Observer observer,Map<String,String> map) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"from\":\""+map.get("from")+"\",\"to\":\""+map.get("to")+"\",\"q\":\""+map.get("q")+"\"}");
        Api.getApiService().getAccessToken(map.get("grantType"),map.get("apiKey"),map.get("secretKey"))
                .subscribeOn(Schedulers.io())
                //切换到子线程中请求网络数据
                .observeOn(Schedulers.io())
                .flatMap(new Function<ResponseData,Observable<TranslationResponse>>() {
                    @Override
                    public Observable<TranslationResponse> apply(ResponseData responseData) throws Exception {
                        return Api.getApiService().translateAccessToken(responseData.getAccess_token(), body);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
