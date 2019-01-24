package com.darren.sellweixin.service.impl;

import com.darren.sellweixin.converter.OrderMaster2OrderDTOConverter;
import com.darren.sellweixin.dataObject.OrderDetail;
import com.darren.sellweixin.dataObject.OrderMaster;
import com.darren.sellweixin.dataObject.ProductInfo;
import com.darren.sellweixin.dto.CartDTO;
import com.darren.sellweixin.dto.OrderDTO;
import com.darren.sellweixin.enums.OrderStatusEnum;
import com.darren.sellweixin.enums.PayStatusEnum;
import com.darren.sellweixin.enums.ResultEnum;
import com.darren.sellweixin.exception.SellException;
import com.darren.sellweixin.repository.OrderDetailRepository;
import com.darren.sellweixin.repository.OrderMasterRepository;
import com.darren.sellweixin.service.OrderService;
import com.darren.sellweixin.service.ProductService;
import com.darren.sellweixin.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单操作
 *
 * @author: luosihao
 * @date: 2019/1/23 11:38
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        // 生成订单id号
        String orderId = KeyUtil.getUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 1.查询商品（数量，价格）
        for(OrderDetail entity:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(entity.getProductId());
            if(null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(entity.getProductQuantity()))
                    .add(orderAmount);

            // 设置唯一的主键
            entity.setDetailId(KeyUtil.getUniqueKey());
            entity.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,entity);
            // 写入数据库orderDetail
            orderDetailRepository.save(entity);
        }
        // 2.写入订单数据库orderMaster
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.New.getCode());
        orderMaster.setPayStatus(PayStatusEnum.Wait.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
        // 3.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductQuantity(),e.getProductId()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        // 实体类是从数据库中取得的时候，最好不要new对象，之后进行null值判断是否找到，这样更合理。
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if(null == orderMaster){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(null == orderDetailList || orderDetailList.size() <= 0){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询所有订单时只需展示订单主体，订单详情不需要
     *
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        // 根据前端的参数orderId查找orderMaster,得到orderDTO的完整信息。
        orderDTO = findOne(orderDTO.getOrderId());

        // 1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())){
            log.error("【订单状态不正确】orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 2.修改订单状态为取消状态
        orderDTO.setOrderStatus(OrderStatusEnum.Cancle.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateOrder = orderMasterRepository.save(orderMaster);
        if(null == updateOrder){
            log.error("【订单更新失败】orderId={}",orderMaster.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        // 3.返回库存数量
       List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
               .map(e -> new CartDTO(e.getProductQuantity(),e.getProductId())).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        // 4.如果已支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.Finished)){
            //TODO
        }

        return orderDTO;
    }

    /**
     * 商家操作 完结订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())){
            log.error("【订单状态不正确】orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.Finished.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateOrder = orderMasterRepository.save(orderMaster);
        if(null == updateOrder){
            log.error("【完结订单】更新失败orderId={}",orderMaster.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    /**
     * 用户操作 支付订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())){
            log.error("【订单状态不正确】orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.Wait.getCode())){
            log.error("【支付订单】支付状态不正确 orderId={},payStatus={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROE);
        }
        // 修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.Finished.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(null == updateResult){
            log.error("【支付订单】更新失败 orderId={},payStatus={}",orderMaster.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }


}
