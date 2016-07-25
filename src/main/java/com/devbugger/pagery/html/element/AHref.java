package com.devbugger.pagery.html.element;

public class AHref extends HtmlElement {

    private static final String OPEN_TAG = "<a href=\"/";
    private static final String CLOSE_TAG = "</a>";

    private String location;
    private String title;

    public AHref(String title, String...locations) {
        super(OPEN_TAG, CLOSE_TAG);
        path(title, locations);
    }

    private void path(String title, String...locations) {
        this.title = title;
        for (String s : locations) {
            location = location != null ? location+"/"+s : s;
        }
    }

    @Override
    public String get() {
        return openingTag+location+".html\""+getAttributes()+">"+title+closingTag;
    }

    @Override
    public String toString() {
        return "AHref{" +
                "location='" + location + '\'' +
                ", title='" + title + '\'' +
                "} " + super.toString();
    }
}
