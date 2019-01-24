
package com.darren.sellweixin.controller;

import com.darren.sellweixin.dataObject.ProductCategory;
import com.darren.sellweixin.dataObject.ProductInfo;
import com.darren.sellweixin.service.CategoryService;
import com.darren.sellweixin.service.ProductService;
import com.darren.sellweixin.utils.ResultVOUtil;
import com.darren.sellweixin.vo.ProductInfoVo;
import com.darren.sellweixin.vo.ProductVo;
import com.darren.sellweixin.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: luosihao
 * @date: 2019/1/18 15:23
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        // 1. 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2. 查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for(ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        // 精简方法（java8,lambda）
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        // 通过上架商品查找有哪些类目在架上有
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 3.数据拼装

        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory entity:productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(entity.getCategoryName());
            productVo.setCategoryType(entity.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                // 该类目下的商品加进去
                if(entity.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    // spring提供的工具，可以将第一个参数类内的元素赋予第一个参数类的对应元素。
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVOUtil.success(productVoList);
    }
}
