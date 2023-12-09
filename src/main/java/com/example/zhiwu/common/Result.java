package com.example.zhiwu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口包装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String message;
    private Object data;

    public static Result success() {
        return new Result(Constants.CODE_200, "", null);
    }

    public static Result success(Object data){
        return new Result(Constants.CODE_200, "", data);
    }

    public static Result fail(String code,String message){
        return new Result(code,message,null);
    }
    public static Result fail(String message){
        return new Result(Constants.SERVER_ERROR,message,null);
    }


    public static Result fail(){
        return new Result(Constants.SERVER_ERROR,"系统错误",null);
    }

    public static Result fail(RespEnum tokenError) {
        return new Result(tokenError.getCode(), tokenError.getMessage(),null);
    }
}
