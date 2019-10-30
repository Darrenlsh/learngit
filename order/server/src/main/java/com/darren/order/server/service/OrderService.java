package com.darren.order.server.service;


import com.darren.order.server.dto.OrderDTO;

/**
 * @author luosihao
 * @date 2019/10/8 14:30
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
