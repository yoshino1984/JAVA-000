package com.yoshino.homework1102.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单
 **/
@Data
@Builder
public class Order {

    /** 订单编号 */
    private Long id;

    /** 订单名称 */
    private String name;

    /** 下订单时间 */
    private Long orderTime;

    /** 订单金额 */
    private BigDecimal money;

    /** 订单状态 */
    private String status;

}
