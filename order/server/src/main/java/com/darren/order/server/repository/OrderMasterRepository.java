package com.darren.order.server.repository;

import com.darren.order.server.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luosihao
 * @date 2019/10/8 14:25
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
