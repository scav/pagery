package com.devbugger.pagery.site;

public class MenuItem {

    private String name;
    private String href;

    public MenuItem(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
