package com.devbugger.pagery.html.attribute.css;

import com.devbugger.pagery.html.attribute.Attribute;

public class CSSId extends Attribute {

    private static final String PREFIX = "id";

    public CSSId(String...values) {
        super(PREFIX, values);
    }

    @Override
    public String toString() {
        return "CSSId{} " + super.toString();
    }
}
