package com.darren.order.common;

import lombok.Data;

/**
 * 减库存入参
 * @author luosihao
 * @date 2019/10/23 11:08
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
