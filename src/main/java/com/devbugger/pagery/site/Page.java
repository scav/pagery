package com.devbugger.pagery.site;


/**
 * Defines a page in the system.
 * A Page can be any kind of content making up a site.
 */
public class Page {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Page{" +
                "content='" + content + '\'' +
                '}';
    }
}
