package com.sgcc.yzd.translate.retrofit;

import com.sgcc.yzd.translate.model.ResponseData;
import com.sgcc.yzd.translate.model.TranslationResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("oauth/2.0/token")
    @FormUrlEncoded
    Observable<ResponseData> getAccessToken(@Field("grant_type") String grantType , @Field("client_id") String apiKey, @Field("client_secret") String secretKey);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("rpc/2.0/mt/texttrans/v1?access_token=24.447d8bdbdf3a3ff150539ae5c7d1bae4.2592000.1691825357.282335-34467556")
    Observable<TranslationResponse> translate(@Body RequestBody body);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("rpc/2.0/mt/texttrans/v1")
    Observable<TranslationResponse> translateAccessToken(@Query("access_token") String path, @Body RequestBody body);

}
