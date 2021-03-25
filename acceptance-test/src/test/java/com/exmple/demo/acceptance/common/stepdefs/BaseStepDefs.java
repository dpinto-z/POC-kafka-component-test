package com.exmple.demo.acceptance.common.stepdefs;

public class BaseStepDefs {
    private static ContextAttributes context(){
        return ContextAttributes.getInstance();
    }

    public static void setAttribute(String attributeName, Object value) {
        context().setAttribute(attributeName, value);
    }

    public static Object getAttribute(String attributeName) {
        return context().getAttribute(attributeName);
    }

    public static void clearContext() {
        context().clear();
    }
}
