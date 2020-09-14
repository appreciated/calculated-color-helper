package com.github.appreciated.calc.color.helper;

public class CssValuePair {
    private String key;
    private String value;

    public CssValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
