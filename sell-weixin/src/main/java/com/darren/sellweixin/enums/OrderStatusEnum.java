package com.darren.sellweixin.enums;

import lombok.Getter;

/**
 * @author: luosihao
 * @date: 2019/1/22 16:26
 */
@Getter
public enum OrderStatusEnum {
    New(1,"新订单"),
    Finished(2,"完结"),
    Cancle(-1,"取消")
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
