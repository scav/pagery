package com.devbugger.pagery.configuration;

import java.util.List;

public class Files {

    private String suffix;
    private String root;
    private String post;
    private String page;
    private String resource;
    private String target;
    private List<Resource> resources;

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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Files{" +
                "suffix='" + suffix + '\'' +
                ", root='" + root + '\'' +
                ", post='" + post + '\'' +
                ", page='" + page + '\'' +
                ", resource='" + resource + '\'' +
                ", target='" + target + '\'' +
                ", resources=" + resources +
                '}';
    }
}
