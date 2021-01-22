package io.kimmking.kmq.core;

import io.kimmking.kmq.demo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
@Data
public class KmqMessage<T> {

    private HashMap<String,Object> headers;

    private T body;

    private int msgId;

    public KmqMessage(T body) {
        this.body = body;
    }
}
