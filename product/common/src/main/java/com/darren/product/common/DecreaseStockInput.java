package com.darren.product.common;

import lombok.Data;

/**
 * 减库存入参
 * @author luosihao
 * @date 2019/10/12 18:36
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
