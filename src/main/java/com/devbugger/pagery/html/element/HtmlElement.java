package com.devbugger.pagery.html.element;

import com.devbugger.pagery.html.attribute.Attribute;

import java.util.Arrays;

/**
 * Simple definition of a HTML element with open and close tag
 * semantics. Supports the addition of one ore more {@link Attribute}
 * definitions within the element.
 */
public abstract class HtmlElement {

    String openingTag, closingTag;
    Attribute[] attributes;

    HtmlElement(String openingTag, String closingTag) {
        this.openingTag = openingTag;
        this.closingTag = closingTag;
    }

    public HtmlElement attributes(Attribute...attributes) {
        this.attributes = attributes;
        return this;
    }

    public String get() {
        return openingTag+getAttributes()+closingTag;
    }

    public String getAttributes() {
        if(attributes == null)
            return "";
        if(attributes.length == 0)
            return "";

        StringBuilder value = new StringBuilder();
        int max = attributes.length -1;
        for (int i = 0; i < attributes.length; i++) {
            value.append(" ").append(attributes[i].get());
        }

        return value.toString();
    }

    @Override
    public String toString() {
        return "HtmlElement{" +
                "openingTag='" + openingTag + '\'' +
                ", closingTag='" + closingTag + '\'' +
                ", attributes=" + Arrays.toString(attributes) +
                '}';
    }
}
