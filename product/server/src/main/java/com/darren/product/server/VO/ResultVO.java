package com.darren.product.server.VO;

import lombok.Data;

/**
 * @author luosihao
 * @date 2019/9/30 20:43
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 具体内容
     */
    private T data;

    public ResultVO(T data) {
        this.code = 0;
        this.message = "OK";
        this.data = data;
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVO() {

    }

    public static <T> ResultVO build(Integer code, String message, T data) {
        return new ResultVO(code, message, data);
    }

    public static ResultVO success() {
        return new ResultVO(null);
    }

    public static <T> ResultVO success(T data) {
        return new ResultVO(data);
    }

    public static <T> ResultVO failure(Integer code, String message, T data) {
        return build(code, message, data);
    }

}
