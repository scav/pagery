package com.devbugger.pagery.configuration;

public class Project {

    private String title;
    private String path;
    private String info;
    private int postlimit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPostlimit() {
        return postlimit;
    }

    public void setPostlimit(int postlimit) {
        this.postlimit = postlimit;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", info='" + info + '\'' +
                ", postlimit=" + postlimit +
                '}';
    }
}
