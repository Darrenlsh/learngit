package com.darren.sellweixin.dataObject;

import com.darren.sellweixin.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 *
 * @author: luosihao
 * @date: 2019/1/18 00:33
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态，0正常1下架.  */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    private Integer categoryType;

//    private Date createTime;
//
//    private Date updateTime;

//    public ProductStatusEnum getProductStatusEnum(){
//        return
//    }

}
