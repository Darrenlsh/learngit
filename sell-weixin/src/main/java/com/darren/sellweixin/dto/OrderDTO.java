package com.darren.sellweixin.dto;


import com.darren.sellweixin.dataObject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: luosihao
 * @date: 2019/1/23 11:22
 */
@Data
public class OrderDTO {
    /**
     * 订单id
     */
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
    private Integer orderStatus;

    /**
     * 支付状态：1待支付，2已支付.默认待支付1
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 订单详情列表
     */
    List<OrderDetail> orderDetailList;

}
