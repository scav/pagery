package com.devbugger.pagery.configuration;

public class Resource {

    private String location;
    private int loadOrder;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLoadOrder() {
        return loadOrder;
    }

    public void setLoadOrder(int loadOrder) {
        this.loadOrder = loadOrder;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "location='" + location + '\'' +
                ", loadOrder=" + loadOrder +
                '}';
    }
}
