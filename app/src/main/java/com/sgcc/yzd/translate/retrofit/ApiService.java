package com.sgcc.yzd.translate.retrofit;

import com.sgcc.yzd.translate.db.entity.Instruction;
import com.sgcc.yzd.translate.model.ResponseData;
import com.sgcc.yzd.translate.model.TranslationResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
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

    /**
     * 控制窗帘(需要根据操作模式type的值去确定是否传非必填参数angle和distance)
     * @param deviceType 操作模式 (1:打开窗帘, 2:关闭窗帘, 3:窗帘停止, 4:窗帘运动, 5:旋转角度, 6:窗帘运动且旋转)
     * @param deviceNo 内机编号
     * @param angle 旋转角度(0,180]
     * @param deviceId 设备id
     * @param distance 运动距离[0,100]
     * @return
     */
    @Headers({"Content-Type:application/json"})
    @POST("/mqtt/ctrl/device/curtain")
    Observable<ResponseBody> controlCurtain(@Field("type") Number deviceType, @Field("no") Number deviceNo, @Field("angle") Number angle, @Field("deviceId") Number deviceId, @Field("distance") Number distance);

//    @Headers({"Content-Type:application/json"})
//    @POST("/mqtt/ctrl/device/curtain")
//    Observable<ResponseBody> control
}
