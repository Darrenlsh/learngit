package com.darren.sellweixin.dataObject;

import com.darren.sellweixin.enums.OrderStatusEnum;
import com.darren.sellweixin.enums.PayStatusEnum;
//import lombok.Data;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主体（订单列表）
 *
 * @author: luosihao
 * @date: 2019/1/22 16:11
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信号
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态：1：新订单，2完结，-1已取消。默认新订单1
     */
    private Integer orderStatus = OrderStatusEnum.New.getCode();

    /**
     * 支付状态：1待支付，2已支付.默认待支付1
     */
    private Integer payStatus = PayStatusEnum.Wait.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
