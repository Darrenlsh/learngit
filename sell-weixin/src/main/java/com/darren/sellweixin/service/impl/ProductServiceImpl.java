package com.darren.sellweixin.service.impl;

import com.darren.sellweixin.dataObject.ProductInfo;
import com.darren.sellweixin.dto.CartDTO;
import com.darren.sellweixin.enums.ProductStatusEnum;
import com.darren.sellweixin.enums.ResultEnum;
import com.darren.sellweixin.exception.SellException;
import com.darren.sellweixin.repository.ProductInfoRepository;
import com.darren.sellweixin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: luosihao
 * @date: 2019/1/18 10:47
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }

    /**
     * 查询所有上架的商品
     *
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }


    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    /**
     * 由于减库存是针对集合操作的，是一个事务，要么全部成功，要么全部失败。加上事务注解。
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for(CartDTO entity:cartDTOList){
            /**
             * spring data jpa 中的findOne()和getOne()有区别。正确的情况下一样
             * 当查询的参数的id不存在时，findOne()返回null值，getOne()会直接抛出异常。
             *
             * 原因：
             * findOne：return 如果没有找到，则使用给定id或{@literal null}的实体。
             * getOne:return 具有给定标识符的实体的引用（对象的引用）
             *
             * 一般优先使用findOne。然而现在的springboot 版本中已经没有findOne了，用findById代替。
             *
             * 参考：https://blog.csdn.net/u012462033/article/details/79466679
             */
            ProductInfo productInfo = productInfoRepository.findById(entity.getProductId()).orElse(null);
            if(null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stockNum = productInfo.getProductStock() - entity.getProductQuantity();
            if(stockNum < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stockNum);

            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO entity:cartDTOList){
            ProductInfo productInfo = productInfoRepository.findById(entity.getProductId()).orElse(null);
            if(null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stockNum = productInfo.getProductStock() + entity.getProductQuantity();
            productInfo.setProductStock(stockNum);

            productInfoRepository.save(productInfo);
        }
    }
}
