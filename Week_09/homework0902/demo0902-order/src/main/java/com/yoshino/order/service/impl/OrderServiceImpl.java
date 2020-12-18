package com.yoshino.order.service.impl;

import com.yoshino.common.account.api.AccountService;
import com.yoshino.common.account.dto.AccountDTO;
import com.yoshino.common.order.api.OrderService;
import com.yoshino.common.order.entity.Order;
import com.yoshino.common.order.enums.OrderStatusEnum;
import com.yoshino.common.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.utils.IdWorkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;

    private AccountService accountService;


    @Autowired
    private OrderService orderService;

    @Autowired(required = false)
    public OrderServiceImpl(OrderMapper orderMapper,
                            AccountService accountService) {
        this.orderMapper = orderMapper;
        this.accountService = accountService;
    }

    @Override
    public String orderPay(Integer count, BigDecimal amount) {
        Order order = createOrder(count, amount);
        return orderService.makePayment(order);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String makePayment(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING.getCode());
        // 扣除user的美元余额
        accountService.payment(buildDollarAccountDTO(order));
        // 扣除buyer的人民币余额
        accountService.payment(buildRmbAccountDTO(order));

        return "success";
    }

//    @Override
//    public String orderPayWithExp(Integer count, BigDecimal amount) {
//        Order order = createOrder(count, amount);
//        orderService.makePaymentWithExp(order);
//        return "success";
//    }
//
//    @Override
//    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
//    public void makePaymentWithExp(Order order) {
//        updateOrderStatus(order, OrderStatusEnum.PAYING.getCode());
//        // 扣除用户余额
//        accountService.payment(buildAccountDTO(order));
//    }

    private AccountDTO buildDollarAccountDTO(Order order) {
        return AccountDTO.builder().amount(order.getTotalAmount()).userId(order.getUserId())
            .type(AccountDTO.TYPE_DOLLAR).build();
    }

    private AccountDTO buildRmbAccountDTO(Order order) {
        return AccountDTO.builder().amount(order.getTotalAmount()).userId(order.getBuyerId())
            .type(AccountDTO.TYPE_RMB).build();
    }

    public void confirmOrderStatus(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAY_SUCCESS.getCode());
        log.info("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAY_FAIL.getCode());
        log.info("=========进行订单cancel操作完成================");
    }

    private Order createOrder(Integer count, BigDecimal amount) {
        Order order = buildOrder(count, amount);
        orderMapper.save(order);
        return order;
    }

    private Order buildOrder(Integer count, BigDecimal amount) {
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setNumber(String.valueOf(IdWorkerUtils.getInstance().createUUID()));
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        // 暂时设定为美元
        order.setTotalAmount(amount);
        order.setCount(count);
        // demo中的表里只有商品id为1的数据
        order.setProductId("1");
        // demo中的表里面存的用户id为10000 和 10001
        order.setUserId("10000");
        order.setBuyerId("10001");
        return order;
    }

    private void updateOrderStatus(Order order, int status) {
        order.setStatus(status);
        orderMapper.update(order);
    }
}
