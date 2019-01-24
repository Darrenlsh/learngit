package com.darren.sellweixin.repository;

import com.darren.sellweixin.dataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: luosihao
 * @date: 2019/1/18 09:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findTest(){
        ProductInfo productInfo = productInfoRepository.findById("123456").orElse(null);
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(3);
        productInfo.setProductDescription("美味不加价");
        productInfo.setProductIcon("http://darren.jpg");
        productInfo.setProductId("111");
        productInfo.setProductName("小杨生煎");
        productInfo.setProductPrice(new BigDecimal(12.6));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(20);

        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfos.size());
    }
}