
package com.darren.sellweixin.repository;

import com.darren.sellweixin.dataObject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author: luosihao
 * @date: 2019/1/22 17:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    private final String OPENID = "100234422";

    @Test
    public void save(){

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("10000001");
        orderMaster.setBuyerAddress("上海市闵行区环球创意中心T3");
        orderMaster.setBuyerName("罗嗣浩");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("17733333333");
        orderMaster.setOrderAmount(new BigDecimal(32.32));

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenId() {
        // pageRequest的值从0开始,但是方法已过时
        //PageRequest pageRequest = new PageRequest(0,10);
        // 替换方法
        Pageable pageable =PageRequest.of(0,5);
        Page<OrderMaster>  orderMasterList = orderMasterRepository.findByBuyerOpenid(OPENID,pageable);
        Assert.assertNotEquals(0,orderMasterList.getTotalElements());
    }
}