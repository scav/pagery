package com.devbugger.pagery.html.attribute;

import java.util.Arrays;

/**
 * HTML element attributes.
 * It support creation of HTML attributes as class="nav nav-bar hover" or id="p1 p2 p3"
 */
public class Attribute {

    private String name;
    private String[] values;

    public Attribute(String name, String...values) {
        this.name = name;
        this.values = values;
    }

    public String get() {
        if(values == null)
            return "";
        if(values.length == 0)
            return "";

        StringBuilder value = new StringBuilder();
        int max = values.length -1;
        for (int i = 0; i < values.length; i++) {
            value.append(values[i]);
            if(i == max)
                return name+"=\""+value.toString()+"\"";
            value.append(" ");
        }

        return null;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
