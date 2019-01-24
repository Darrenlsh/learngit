package com.darren.sellweixin.service.impl;

import com.darren.sellweixin.dataObject.OrderDetail;
import com.darren.sellweixin.dto.OrderDTO;
import com.darren.sellweixin.enums.OrderStatusEnum;
import com.darren.sellweixin.enums.PayStatusEnum;
import com.darren.sellweixin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author: luosihao
 * @date: 2019/1/24 11:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "s7fuu3rhu83877e8eww";
    private final String ORDER_ID = "1548308616152351355456";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("陈蝶衣");
        orderDTO.setBuyerPhone("17718987888");
        orderDTO.setBuyerAddress("松江大学城学生公寓18号楼");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("111232");
        orderDetail.setProductQuantity(2);

        // 再添加一种商品
        OrderDetail orderMoreDetail = new OrderDetail();
        orderMoreDetail.setProductId("123456");
        orderMoreDetail.setProductQuantity(3);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderMoreDetail);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findList() {
        Pageable pageable = PageRequest.of(0,10);
        Page<OrderDTO> resultPage = orderService.findList(BUYER_OPENID,pageable);
        log.info("【查询某用户的所有订单】resultList={}",resultPage.getContent());
        Assert.assertNotEquals(0,resultPage.getTotalElements());
    }

    @Test
    public void cancel() {
        // 传来的只有orderId和openId两项数据参数
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(ORDER_ID);
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        OrderDTO result = orderService.cancel(orderDTO);
        log.info("【取消订单】orderId={}",orderDTO.getOrderId());
        //Assert.assertNotNull(result);
        Assert.assertEquals(OrderStatusEnum.Cancle.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.Finished.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.Finished.getCode(),result.getPayStatus());
    }

    @Test
    public void findList1() {
    }
}