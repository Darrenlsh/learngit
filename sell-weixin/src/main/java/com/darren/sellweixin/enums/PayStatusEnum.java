package com.darren.sellweixin.enums;

import lombok.Getter;

/**
 * @author: luosihao
 * @date: 2019/1/22 16:34
 */
@Getter
public enum PayStatusEnum {
    Wait(1,"未支付"),
    Finished(2,"已支付")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
