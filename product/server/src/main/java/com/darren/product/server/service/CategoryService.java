package com.darren.product.server.service;


import com.darren.product.server.dataobject.ProductCategory;

import java.util.List;

/**
 * @author luosihao
 * @date 2019/9/30 20:36
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
