package com.devbugger.pagery.configuration.defaults;

/**
 * This class contains a very basic and default application
 * configuration that will be used to create a config.yaml
 * in cases where this does not exist in the project directory.
 */
public class DefaultConfig {

    public final static String CONFIGYAML = "###\n" +
            "# This is the default Pagery project configuration.\n" +
            "#\n" +
            "# This file can be changed and updated as necessary.\n" +
            "# All projects must have this file and most of the root properties\n" +
            "# are necessary for a project to build an actual web page.\n" +
            "##\n" +
            "#\n" +
            "\n" +
            "# Files defines file based resources and properties\n" +
            "# regarding different files.\n" +
            "files:\n" +
            "  suffix: md\n" +
            "  root: example/\n" +
            "  post: post\n" +
            "  page: page\n" +
            "  target: pagery-out-2/\n" +
            "  resource: resources/\n" +
            "  resources: # Set up the CSS resources that needs a load order. Any resource listed will be given a random order.\n" +
            "    - location: pure-min.css\n" +
            "      loadOrder: 1\n" +
            "    - location: grids-responsive-min.css\n" +
            "      loadOrder: 2\n" +
            "    - location: blog.css\n" +
            "      loadOrder: 3\n" +
            "\n" +
            "# Adding templates to override or add defaults to tags\n" +
            "# to make it fit different style sheets.\n" +
            "# These will only transform Markdown content. Html based\n" +
            "# content is intended to be edited manually.\n" +
            "#\n" +
            "# Examples:\n" +
            "#\n" +
            "#<tag>@post.title</tag> => <tag class=\"myClass moreClass\">My Title</tag>\n" +
            "# templates:\n" +
            "#    post.title:\n" +
            "#      attribute: class/\n" +
            "#      values: [myClass, moreClass]\n" +
            "#\n" +
            "#<tag>@post.author</tag> => <tag id=\"myId moreId\">An Author</tag>\n" +
            "# templates:\n" +
            "#    post.author:\n" +
            "#      attribute: id\n" +
            "#      values: [myId, moreId]\n" +
            "#\n" +
            "templates:\n" +
            "  post.title:\n" +
            "    attribute: class\n" +
            "    values: [post-title]\n" +
            "\n" +
            "# Pagery Project propertied\n" +
            "# Properties define the name of the project, the root path, some info text\n" +
            "# and limits the amount of blog posts the index page will show.\n" +
            "project:\n" +
            "  title: Pagery Developer Blog\n" +
            "  path: http://localhost:8080\n" +
            "  info: Developer blog for static blog generator Pagery.\n" +
            "  postlimit: 1 # Amount of posts to show on the frontpage\n" +
            "\n" +
            "# Development server properties.\n" +
            "# These properties can be used to override the default properties\n" +
            "# of the server instance (localhost:8080 is default).\n" +
            "server:\n" +
            "  name: localhost\n" +
            "  host: localhost\n" +
            "  port: 8080\n";
}
