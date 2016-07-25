package com.devbugger.pagery.html.attribute.css;

import com.devbugger.pagery.html.attribute.Attribute;

public class CSSClass extends Attribute {

    private static final String PREFIX = "class";

    public CSSClass(String...values) {
        super(PREFIX, values);
    }

    @Override
    public String toString() {
        return "CSSClass{} " + super.toString();
    }
}
