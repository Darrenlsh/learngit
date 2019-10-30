package com.darren.product.server.repository;


import com.darren.product.server.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author luosihao
 * @date 2019/9/30 11:08
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
