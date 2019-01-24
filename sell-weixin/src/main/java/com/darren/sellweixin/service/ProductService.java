package com.darren.sellweixin.service;

import com.darren.sellweixin.dataObject.ProductInfo;
import com.darren.sellweixin.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: luosihao
 * @date: 2019/1/18 10:41
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 商家查询所有商品需要分页
     *
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    // 加库存
    void increaseStock(List<CartDTO> cartDTOList);
}
