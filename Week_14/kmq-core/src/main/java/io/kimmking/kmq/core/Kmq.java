package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class Kmq {
    private final Map<String, AtomicInteger> groupOffsetMap = new ConcurrentHashMap<>();

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new KmqMessage[capacity];
        this.pos = new AtomicInteger();
    }

    private String topic;

    private int capacity;

    private KmqMessage[] queue;

    /** 当前队列的位置 */
    private AtomicInteger pos;

    public boolean send(KmqMessage message) {
        int i = pos.getAndIncrement();
        if (i < capacity) {
            message.setMsgId(i);
            queue[i] = message;
            return true;
        }
        return false;
    }

    @SneakyThrows
    public KmqMessage poll(String group) {
        int offset = groupOffsetMap.computeIfAbsent(group, key -> new AtomicInteger()).get();
        if (offset >= capacity) {
            return null;
        }
        return queue[offset];
    }

    public void confirm(String group, int offset) {
        groupOffsetMap.computeIfAbsent(group, key -> new AtomicInteger())
            .compareAndSet(offset, offset + 1);
    }
}
