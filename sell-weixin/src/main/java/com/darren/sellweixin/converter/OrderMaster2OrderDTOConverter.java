package com.darren.sellweixin.converter;

import com.darren.sellweixin.dataObject.OrderMaster;
import com.darren.sellweixin.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: luosihao
 * @date: 2019/1/24 15:50
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList = orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());

        return orderDTOList;
    }

}
