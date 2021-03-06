package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.Resource;
import com.devbugger.pagery.html.attribute.*;
import com.devbugger.pagery.html.element.Link;
import com.devbugger.pagery.site.*;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

import static com.devbugger.pagery.html.attribute.AttributeType.CHARSET_UTF8;
import static com.devbugger.pagery.html.attribute.AttributeType.STYLESHEET;
import static com.devbugger.pagery.html.attribute.AttributeType.TEXT_CSS;
import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;

public class DefaultTransformPageryBaseBage implements TransformPageryBasePage<BasePage, Page> {

    private Config config;

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public BasePage transform(Config config, BasePage basePage, List<Page> pages) {
        List<MenuItem> menuItems = new ArrayList<>();

        // Generate menu items.
        for (Page page : pages) {
            String name = page.getFontMatterMeta().getTitle();
            String type = page.getFontMatterMeta().getType();
            if(page instanceof IndexPage) {
                continue;
            }

            else
                menuItems.add(new MenuItem(name, "/"+type+"/"+name+".html"));
        }

        basePage.setMenuItems(menuItems);

        String input = basePage.getContent();

        if(input.contains(PAGERY_HEADER_CSS))
            input = input.replace(PAGERY_HEADER_CSS, css());
        if(input.contains(PAGERY_TITLE))
            input = input.replace(PAGERY_TITLE, "<a href=\"/\">"+config.getProject().getTitle()+"</a>\n");
        if(input.contains(PAGERY_SITE_INFO))
            input = input.replace(PAGERY_SITE_INFO, config.getProject().getInfo());
        if(input.contains(PAGERY_BUILD_INFO))
            input = input.replace(PAGERY_BUILD_INFO, "Created @ "+LocalDateTime.now().toString() + "<br />");

        basePage.setContent(input);

        return basePage;
    }

    @Override
    public Page attach(BasePage basePage, Page page, List<Page> pages) {
        if(basePage.getContent().contains(PAGERY_CONTENT)) {
            page.setContent(basePage.getContent().replace(PAGERY_CONTENT, page.getContent()));
        }
        if(page.getContent().contains(PAGERY_HEADER_TITLE)) {
            page.setContent(page.getContent().replace(PAGERY_HEADER_TITLE, page.getFontMatterMeta().getTitle()));
        }
        if(basePage.getContent().contains(PAGERY_MENUS_START) || basePage.getContent().contains(PAGERY_MENUS_END)) {
            page.setContent(menu(basePage, page));
        }

        return page;
    }

    String menu(BasePage basePage, Page page) {
        String input = page.getContent();

        int indexPre = input.indexOf(PAGERY_MENUS_START)+PAGERY_MENUS_START.length();
        int indexPost = input.indexOf(PAGERY_MENUS_END)+PAGERY_MENUS_END.length();

        // Create copies of the content pre and post markers.
        String pre = input.substring(0, input.indexOf(PAGERY_MENUS_START));
        String post = input.substring(indexPost);

        StringBuilder builder = new StringBuilder(pre);

        for (MenuItem menuItem : basePage.getMenuItems()) {
            String output = input.substring(indexPre, input.indexOf(PAGERY_MENUS_END));

            if(output.contains(PAGERY_MENU_NAME))
                output = output.replace(PAGERY_MENU_NAME, menuItem.getName());
            if (output.contains(PAGERY_MENU_HREF)) {
                if(page.getFontMatterMeta().getTitle().equals(menuItem.getName())) {
                    output = output.replace(PAGERY_MENU_ACTIVE, "menu-active");
                }
                else {
                    output = output.replace(PAGERY_MENU_ACTIVE, "");
                }
                output = output.replace(PAGERY_MENU_HREF, menuItem.getHref());
            }


            builder.append(output);
        }

       builder.append(post);

        return builder.toString();
    }


    /**
     * Generate the menu from a list of existing pages.
     * @param pages all transformed pages
     * @return the html formatted menu
     */
    @Deprecated
    String menuOld(List<Page> pages, Page p) {
        StringBuilder builder = new StringBuilder();

        for (Page page : pages) {
            String type = page.getFontMatterMeta().getType();
            String name = page.getFontMatterMeta().getTitle();

            if(page instanceof Post) {
                continue;
            }
            if(page.equals(p)) {
                builder.append("\n<a class=\"active\" href=\"/");
            }
            else {
                builder.append("\n<a href=\"/");
            }
            builder.append(type)
                    .append("/")
                    .append(name)
                    .append(".html\">")
                    .append(name)
                    .append("</a>");
        }

        return builder.toString();

    }

    String css() {
        String path = config.getFiles().getRoot()+config.getFiles().getResource();
        StringBuilder output = new StringBuilder();

        // Set up the resource with the correct load order
        Resource[] loadOrdered = new Resource[config.getFiles().getResources().size()];
        for (Resource resource : config.getFiles().getResources()) {
            int pos = resource.getLoadOrder();
            loadOrdered[pos-1] = resource;
        }

        // Add resources with their given load order
        for (Resource resource : loadOrdered) {
            output.append(
                new Link()
                    .attributes(
                    new Rel(STYLESHEET),
                    new Type(TEXT_CSS),
                    new Charset(CHARSET_UTF8),
                    new Href("/" + config.getFiles().getResource()+resource.getLocation())).get()
            ).append("\n\t");
        }

        // Set up a filter based on CSS files and files not in load order
        final PathMatcher matcher = Paths.get(path).getFileSystem().getPathMatcher("glob:" + "*.css");
        DirectoryStream.Filter<Path> filter = entry -> {
            for (Resource resource : loadOrdered) {
                if (entry.getFileName().toString().equals(resource.getLocation()) || !matcher.matches(entry.getFileName())) {
                    return false;
                }
            }
            return true;
        };

        // Add the rest of the files not under load order
        try (DirectoryStream<Path> postStream = Files.newDirectoryStream(
                Paths.get(path), filter)) {

            postStream.forEach(p ->
                output.append(
                    new Link()
                        .attributes(
                        new Rel(STYLESHEET),
                        new Type(TEXT_CSS),
                        new Charset(CHARSET_UTF8),
                        new Href("/" + config.getFiles().getResource()+p.getFileName().toString())).get()
                ).append("\n\t")
            );


            return output.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }


}
