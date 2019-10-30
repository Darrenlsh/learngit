package com.darren.order.server.repository;

import com.darren.order.server.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author luosihao
 * @date 2019/10/8 14:26
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetailRepository> findByOrderId(String orderId);
}
