package com.sgcc.yzd.translate.callback;
/**
 * 数据请求回调接口
 */
public interface DataCallback<T> {
    /**
     * 数据请求成功回调
     *
     * @param result 返回数据
     */
    void onSuccess(T result);

    /**
     * 数据请求失败回调
     *
     * @param error 错误信息
     */
    void onError(String error);
}