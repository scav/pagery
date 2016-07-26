package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.PageryConfigSupport;
import com.devbugger.pagery.html.attribute.Charset;
import com.devbugger.pagery.html.attribute.Href;
import com.devbugger.pagery.html.attribute.Rel;
import com.devbugger.pagery.html.attribute.Type;
import com.devbugger.pagery.html.element.Link;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        if(page.getContent().contains(PAGERY_PAGES)) {
            page.setContent(page.getContent().replace(PAGERY_PAGES, menu(pages, page)));
        }

        return page;
    }

    /**
     * Generate the menu from a list of existing pages.
     * @param pages all transformed pages
     * @return the html formatted menu
     */
    String menu(List<Page> pages, Page p) {
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
        StringBuilder output = new StringBuilder();

        try (DirectoryStream<Path> postStream = Files.newDirectoryStream(Paths.get(
                config.getFiles().getRoot()+config.getFiles().getResource()))) {

            postStream.forEach(p ->
                    output.append(
                    new Link()
                        .attributes(
                                new Rel(STYLESHEET),
                                new Type(TEXT_CSS),
                                new Charset(CHARSET_UTF8),
                                new Href(p.getFileName().toString())).get()
                    )

        );

            //
            return output.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();

    }


}
