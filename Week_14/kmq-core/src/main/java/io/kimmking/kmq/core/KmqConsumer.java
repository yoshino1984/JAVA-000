package io.kimmking.kmq.core;

import io.kimmking.kmq.demo.Order;

import java.util.concurrent.atomic.AtomicInteger;

public class KmqConsumer<T> {

    private final String group;

    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker) {
        this.group = "default";
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> get() {
        return kmq.poll(group);
    }

    public void confirm(int offset) {
        kmq.confirm(group, offset);
    }
}
