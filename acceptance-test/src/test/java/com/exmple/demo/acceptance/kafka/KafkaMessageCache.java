package com.exmple.demo.acceptance.kafka;

import java.util.ArrayList;
import java.util.List;

public class KafkaMessageCache<T> {
    private List<T> cache = new ArrayList<>();

    public void clearCache() {
        cache.clear();
    }

    public void addMessage(T message) {
        cache.add(message);
    }

    public T getFirstMessage() {
        return cache.get(0);
    }

    public int getSize() {
        return cache.size();
    }
}
