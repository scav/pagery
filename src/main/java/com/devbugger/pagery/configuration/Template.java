package com.devbugger.pagery.configuration;

import java.util.Arrays;

public class Template {

    private String attribute;
    private String[] values;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Template{" +
                ", attribute='" + attribute + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
