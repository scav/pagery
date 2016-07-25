package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;

import java.time.LocalDateTime;
import java.util.List;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;

public class DefaultTransformPageryBaseBage implements TransformPageryBasePage<BasePage, Page> {

    @Override
    public BasePage transform(Config config, BasePage basePage, List<Page> pages) {
        String input = basePage.getContent();

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
    private String menu(List<Page> pages, Page p) {
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

}
