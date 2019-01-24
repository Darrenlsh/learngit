package com.darren.sellweixin.repository;

import com.darren.sellweixin.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: luosihao
 * @date: 2019/1/17 16:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
        //List<ProductCategory> productCategory = productCategoryRepository.findAll();
        /**
         * 因为SpringBoot的版本是2.0.0之后的，所以spring-data-jpa的版本也是2.0.0之后的。问题是2.0.0版本后,
         * findOne方法的返回值也改了，由原先的T变成了 Optional<T>。
         * 所以无法直接这样用：ProductCategory productCategory1 = productCategoryRepository.findById(1);
         *
         * 解决方法有三种:https://blog.csdn.net/u012211603/article/details/79828277
         *
         * 推荐使用 追加orElse
         */
        ProductCategory productCategory1 = productCategoryRepository.findById(1).orElse(null);
        System.out.println(productCategory1.toString());
    }

    /**
     * @Transactional
     * 这个注解是事务回滚，在test测试里，无论执行成功与否，数据都不会插入数据库（会回滚）。
     * 在service里，执行发生异常的情况下，会发生回滚。成功的情况下，不发生回滚。
     */
    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("女生",4);
        ProductCategory productCategory1 = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(productCategory1);
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);
        // spring-boot-data-jpa中，更新也是用save，与保存的唯一不同是，要设置id;
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeTest(){
        List<Integer> list = Arrays.asList(2,3,4,5,6);
        List<ProductCategory> productCategories = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,productCategories.size());
    }
}