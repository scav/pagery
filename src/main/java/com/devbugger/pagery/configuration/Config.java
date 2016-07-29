package com.devbugger.pagery.configuration;

import java.util.Map;

/**
 * Collects all the config classes providing access to them
 * throughout the application.
 *
 * It is populated by an instance of {@link PageryConfig}.
 */
public class Config {

    private Files files;
    private Project project;
    private Server server;
    private Map<String, Template> templates;

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Map<String, Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, Template> templates) {
        this.templates = templates;
    }

    @Override
    public String toString() {
        return "Config{" +
                "files=" + files +
                ", project=" + project +
                ", server=" + server +
                ", templates=" + templates +
                '}';
    }
}
