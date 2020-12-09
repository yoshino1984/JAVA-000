package com.yoshino.homework01.service;

import com.yoshino.homework01.dao.OrderMapper;
import com.yoshino.homework01.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单服务
 *
 * @author wangxin
 * 2020/12/8 下午11:38
 * @since
 **/
@Service
@Slf4j
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;

    public Long getCount() {
        return orderMapper.getCount();
    }

    public Order selectByPrimaryKey(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public void insertSelect(Order order) {
        orderMapper.insertSelective(order);
    }

    public void update(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    public void delete(Order order) {
        orderMapper.deleteByPrimaryKey(order.getId());
    }

}
