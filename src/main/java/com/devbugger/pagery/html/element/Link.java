package com.devbugger.pagery.html.element;

public class Link extends HtmlElement {

    private static final String OPEN_TAG = "<link";
    private static final String CLOSE_TAG = "/>";

    public Link() {
        super(OPEN_TAG, CLOSE_TAG);
    }
}
