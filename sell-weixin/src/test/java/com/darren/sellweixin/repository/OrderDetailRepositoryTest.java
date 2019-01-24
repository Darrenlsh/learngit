package com.darren.sellweixin.repository;

import com.darren.sellweixin.dataObject.OrderDetail;
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
 * @date: 2019/1/22 20:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("12325353");
        orderDetail.setOrderId("24235353");
        orderDetail.setProductIcon("http://darrenlsh.jsp");
        orderDetail.setProductId("1232222");
        orderDetail.setProductName("羊蝎子");
        orderDetail.setProductPrice(new BigDecimal(56.55));
        orderDetail.setProductQuantity(3);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("24235353");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}