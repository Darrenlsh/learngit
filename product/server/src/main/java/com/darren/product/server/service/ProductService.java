package com.darren.product.server.service;


import com.darren.product.common.DecreaseStockInput;
import com.darren.product.common.ProductInfoOutput;
import com.darren.product.server.dataobject.ProductInfo;

import java.util.List;

/**
 * @author luosihao
 * @date 2019/9/30 11:18
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     *
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     *
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
