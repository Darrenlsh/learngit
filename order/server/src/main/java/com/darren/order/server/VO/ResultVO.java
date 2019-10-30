package com.darren.order.server.VO;

import lombok.Data;

/**
 * @author luosihao
 * @date 2019/10/11 10:39
 */
@Data
public class ResultVO<T> {

    private Integer code;
    private String msg;
    private T data;

    ResultVO(T data){
        this.code = 0;
        this.msg = "OK";
        this.data = data;
    }

    ResultVO(String msg,T data){
        this.code = -1;
        this.msg = msg;
        this.data = data;
    }

    ResultVO(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVO success(){
        return new ResultVO(null);
    }

    public static <T> ResultVO success(T data){
        return new ResultVO(data);
    }

    public static <T> ResultVO faliure(String msg){
        return new ResultVO(msg,null);
    }

    public static <T> ResultVO faliure(String msg, T data){
        return new ResultVO(msg,data);
    }

    public static <T> ResultVO bulid(Integer code, String msg, T data){
        return new ResultVO(code,msg,data);
    }

}
