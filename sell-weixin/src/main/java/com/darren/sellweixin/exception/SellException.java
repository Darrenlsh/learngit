package com.darren.sellweixin.exception;

import com.darren.sellweixin.enums.ResultEnum;

/**
 * @author: luosihao
 * @date: 2019/1/23 14:21
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        // RuntimeException 的父类有针对message赋值的代码。
        super(message);
        this.code = code;
    }
}
