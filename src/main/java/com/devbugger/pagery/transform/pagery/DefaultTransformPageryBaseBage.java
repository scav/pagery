package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;

import java.time.LocalDateTime;
import java.util.List;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;

public class DefaultTransformPageryBaseBage implements TransformPageryBasePage<BasePage, List<Page>> {

    @Override
    public BasePage transform(Config config, BasePage basePage, List<Page> pages) {
        String input = basePage.getContent();

        if(input.contains(PAGERY_PAGES))
            input = input.replace(PAGERY_PAGES, menu(pages));
        if(input.contains(PAGERY_TITLE))
            input = input.replace(PAGERY_TITLE, "<a href=\"/\">"+config.getProject().getTitle()+"</a>\n");
        if(input.contains(PAGERY_SITE_INFO))
            input = input.replace(PAGERY_SITE_INFO, config.getProject().getInfo());
        if(input.contains(PAGERY_BUILD_INFO))
            input = input.replace(PAGERY_BUILD_INFO, "Created @ "+LocalDateTime.now().toString() + "<br />");

        basePage.setContent(input);

        return basePage;
    }

    /**
     * Generate the menu from a list of existing pages.
     * @param pages all transformed pages
     * @return the html formatted menu
     */
    @Override
    public String menu(List<Page> pages) {
        StringBuilder builder = new StringBuilder();

        // Sort the menu alphabetically
        pages.sort((p1, p2) -> p1.getFontMatterMeta().getTitle().compareTo(p2.getFontMatterMeta().getTitle()));

        pages.forEach(p -> {
            String type = p.getFontMatterMeta().getType();
            String name = p.getFontMatterMeta().getTitle();
                builder.append("\n<a href=\"/")
                    .append(type)
                    .append("/")
                    .append(name)
                    .append(".html\">")
                    .append(name)
                    .append("</a>");
        });

        return builder.toString();
    }

    @Override
    public Page attach(BasePage basePage, Page page) {
        if(basePage.getContent().contains(PAGERY_CONTENT)) {
            page.setContent(basePage.getContent().replace(PAGERY_CONTENT, page.getContent()));

            return page;
        }
        return null;
    }

}
