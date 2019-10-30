package com.darren.product.server.repository;

import com.darren.product.server.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author luosihao
 * @date 2019/9/30 20:10
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

