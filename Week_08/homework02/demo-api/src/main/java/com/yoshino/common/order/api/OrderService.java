package com.yoshino.common.order.api;

import com.yoshino.common.order.entity.Order;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.HmilyTCC;

import java.math.BigDecimal;

/**
 * @author wangxin
 * 2020/12/10 上午12:00
 * @since
 **/
public interface OrderService {

    /**
     * 创建订单 扣减金额 扣减库存
     *
     * @param count 购买数量
     * @param amount 支付金额
     * @return java.lang.String
     */
    String orderPay(Integer count, BigDecimal amount);

    String makePayment(Order order);

    /**
     * 创建订单 扣减金额 扣减库存抛出异常
     *
     * @param count 购买数量
     * @param amount 支付金额
     * @return java.lang.String
     */
    String orderPayWithExp(Integer count, BigDecimal amount);

    void makePaymentWithExp(Order order);
}
