package com.darren.order.server.exception;


import com.darren.order.server.enums.ResultEnum;

/**
 * @author luosihao
 * @date 2019/10/11 10:53
 */
public class OrderException extends RuntimeException {
    private Integer code;
    private String msg;
    public OrderException(Integer code,String message){
        super(message);
        this.code = code;
        this.msg = message;
    }
    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

}
