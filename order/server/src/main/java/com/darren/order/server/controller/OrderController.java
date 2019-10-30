package com.darren.order.server.controller;

import com.darren.order.server.VO.ResultVO;
import com.darren.order.server.converter.OrderForm2OrderDTOConverter;
import com.darren.order.server.dto.OrderDTO;
import com.darren.order.server.enums.ResultEnum;
import com.darren.order.server.exception.OrderException;
import com.darren.order.server.form.OrderForm;
import com.darren.order.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luosihao
 * @date 2019/10/11 10:37
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public <T> ResultVO<T> create(@RequestBody @Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【订单创建】参数不正确，orderForm={}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convertOrderForm(orderForm);
//        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
//            log.error("【创建订单】购物车信息为空");
//            throw new OrderException(ResultEnum.CART_EMPTY);
//        }
        OrderDTO result = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVO.success(map);
    }
}
