package com.darren.product.server.exception;


import com.darren.product.server.enums.ResultEnum;

/**
 * @author luosihao
 * @date 2019/10/14 9:48
 */
public class ProductException extends RuntimeException {
    private Integer code;
    private String message;
    public ProductException(Integer code,String message){
        super(message);
        this.code = code;
        this.message = message;
    }
    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
