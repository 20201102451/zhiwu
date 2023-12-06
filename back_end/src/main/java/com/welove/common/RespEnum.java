package com.welove.common;


public enum RespEnum {

    //通用
    TOKEN_ERROR("500215", "用户token不存在"),

    ;

    private final String  code;
    private final String message;

    public String  getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    RespEnum(String  code, String message) {
        this.code = code;
        this.message = message;
    }
}
