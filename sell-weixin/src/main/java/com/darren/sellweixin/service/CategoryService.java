package com.darren.sellweixin.service;

import com.darren.sellweixin.dataObject.ProductCategory;

import java.util.List;

/**
 * @author: luosihao
 * @date: 2019/1/18 00:11
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();

    /**
     * 买家端需要根据类目来查对应商品
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
