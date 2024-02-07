package com.sgcc.yzd.translate.model;

import java.io.Serializable;

public class HttpResponse<T> implements Serializable {
    String code;
    String message;
    T data;
}
