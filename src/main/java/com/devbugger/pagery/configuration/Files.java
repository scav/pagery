package com.devbugger.pagery.configuration;

public class Files {

    private String suffix;
    private String root;
    private String post;
    private String page;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Files{" +
                "suffix='" + suffix + '\'' +
                ", root='" + root + '\'' +
                ", post='" + post + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
