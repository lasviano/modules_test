package com.xuwb.modules.dto;

public class GeneralResponse<T> {

    private static final String SUCCESS_CODE = "success";

    private String code = SUCCESS_CODE;
    private String message;
    private T data;

    public GeneralResponse(){}

    public GeneralResponse(T data){
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
