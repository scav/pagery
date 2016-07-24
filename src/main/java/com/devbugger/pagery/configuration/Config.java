package com.devbugger.pagery.configuration;

public class Config {

    // Data
    private String suffix;
    private String root;
    private String postDir;
    private String pageDir;

    // Project configuration
    private String projectName;
    private String siteName;
    private String siteInfo;

    // Undertow server
    private String name;
    private String host;
    private String port;

    public Config() {
    }

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

    public String getPostDir() {
        return postDir;
    }

    public void setPostDir(String postDir) {
        this.postDir = postDir;
    }

    public String getPageDir() {
        return pageDir;
    }

    public void setPageDir(String pageDir) {
        this.pageDir = pageDir;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteInfo() {
        return siteInfo;
    }

    public void setSiteInfo(String siteInfo) {
        this.siteInfo = siteInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Config{" +
                "suffix='" + suffix + '\'' +
                ", root='" + root + '\'' +
                ", postDir='" + postDir + '\'' +
                ", pageDir='" + pageDir + '\'' +
                ", projectName='" + projectName + '\'' +
                ", siteName='" + siteName + '\'' +
                ", siteInfo='" + siteInfo + '\'' +
                ", name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
