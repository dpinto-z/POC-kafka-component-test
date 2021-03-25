package com.exmple.demo.acceptance.common.stepdefs;

import java.util.HashMap;
import java.util.Map;

public class ContextAttributes {
    private static ContextAttributes INSTANCE;
    private Map<String, Object> attributes;

    private ContextAttributes() {
        attributes = new HashMap<>();
    }

    public static ContextAttributes getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContextAttributes();
        }
        return INSTANCE;
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void clear() {
        attributes.clear();
    }
}
