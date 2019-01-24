package com.darren.sellweixin.enums;

import lombok.Getter;

/**
 * @author: luosihao
 * @date: 2019/1/18 00:38
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;
    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
