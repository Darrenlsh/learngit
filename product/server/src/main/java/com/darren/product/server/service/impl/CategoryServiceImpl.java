package com.darren.product.server.service.impl;


import com.darren.product.server.dataobject.ProductCategory;
import com.darren.product.server.repository.ProductCategoryRepository;
import com.darren.product.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luosihao
 * @date 2019/9/30 20:36
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
