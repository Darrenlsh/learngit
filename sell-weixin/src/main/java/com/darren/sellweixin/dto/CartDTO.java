package com.darren.sellweixin.dto;

import lombok.Data;

/**
 * 购物车
 *
 * @author: luosihao
 * @date: 2019/1/24 09:55
 */
@Data
public class CartDTO {
    // 购买数量
    private Integer productQuantity;
    private String productId;

    public CartDTO(Integer productQuantity, String productId) {
        this.productQuantity = productQuantity;
        this.productId = productId;
    }
}
