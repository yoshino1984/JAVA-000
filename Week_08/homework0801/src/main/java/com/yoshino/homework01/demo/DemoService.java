package com.yoshino.homework01.demo;

import com.alibaba.fastjson.JSON;
import com.yoshino.homework01.model.Order;
import com.yoshino.homework01.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DemoService {

    @Autowired
    private OrderService orderService;

    private Order getDefaultOrder(Long seed) {
        return Order.builder()
            .id(seed)
            .orderNo(seed.toString())
            .build();
    }

    public void curdSingle() {
        long startSize = orderService.getCount();
        System.out.println("startSize=" + startSize);

        Order order = getDefaultOrder(System.currentTimeMillis() % 100000000);
        orderService.insertSelect(order);

        System.out.println(JSON.toJSONString(order));


    }



}
