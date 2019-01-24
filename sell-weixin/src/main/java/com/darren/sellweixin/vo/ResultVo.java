package com.darren.sellweixin.vo;

/**
 * @author: luosihao
 * @date: 2019/1/18 15:42
 */

import lombok.Data;

@Data
public class ResultVo<T> {

    /** 错误码。*/
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
