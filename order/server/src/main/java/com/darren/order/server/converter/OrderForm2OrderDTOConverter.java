package com.darren.order.server.converter;

import com.darren.order.server.dto.OrderDTO;
import com.darren.order.server.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luosihao
 * @date 2019/10/11 11:19
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convertOrderForm(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        try{
//            orderDetailList = JSONObject.parseArray(orderForm.getItems(),OrderDetail.class);
//        }catch (Exception e){
//            log.error("【json转换】错误，string={}",orderForm.getItems());
//            throw new OrderException(ResultEnum.PARAM_ERROR);
//        }
        orderDTO.setOrderDetailList(orderForm.getOrderDetailList());
        return orderDTO;
    }
}
