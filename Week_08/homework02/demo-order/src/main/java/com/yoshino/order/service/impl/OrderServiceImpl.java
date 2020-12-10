package com.yoshino.order.service.impl;

import com.yoshino.common.account.api.AccountService;
import com.yoshino.common.account.dto.AccountDTO;
import com.yoshino.common.inventory.api.InventoryService;
import com.yoshino.common.inventory.dto.InventoryDTO;
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

    private InventoryService inventoryService;

    @Autowired
    private OrderService orderService;

    @Autowired(required = false)
    public OrderServiceImpl(OrderMapper orderMapper,
                            AccountService accountService,
                            InventoryService inventoryService) {
        this.orderMapper = orderMapper;
        this.accountService = accountService;
        this.inventoryService = inventoryService;
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
        //扣除用户余额
        accountService.payment(buildAccountDTO(order));
        //进入扣减库存操作
        inventoryService.decrease(buildInventoryDTO(order));

        return "success";
    }

    @Override
    public String orderPayWithExp(Integer count, BigDecimal amount) {
        Order order = createOrder(count, amount);
        orderService.makePaymentWithExp(order);
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void makePaymentWithExp(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING.getCode());
        // 扣除用户余额
        accountService.payment(buildAccountDTO(order));
        // 进入扣减库存操作
        inventoryService.mockWithTryException(buildInventoryDTO(order));
    }

    private InventoryDTO buildInventoryDTO(Order order) {
        return InventoryDTO.builder().count(order.getCount()).productId(order.getProductId()).build();
    }

    private AccountDTO buildAccountDTO(Order order) {
        return AccountDTO.builder().amount(order.getTotalAmount()).userId(order.getUserId()).build();
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
        order.setTotalAmount(amount);
        order.setCount(count);
        //demo中的表里只有商品id为1的数据
        order.setProductId("1");
        //demo中的表里面存的用户id为10000
        order.setUserId("10000");
        return order;
    }

    private void updateOrderStatus(Order order, int status) {
        order.setStatus(status);
        orderMapper.update(order);
    }
}
