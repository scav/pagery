package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Page;

import java.util.List;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.PAGERY_PAGES;
import static com.devbugger.pagery.transform.pagery.PageryMarkers.PAGERY_SITE_INFO;

public class TransformPageryBase implements TransformPagery<List<Page>> {

    @Override
    public String transform(String input, List<Page> pages) {
        if(input.contains(PAGERY_PAGES))
            input = input.replace(PAGERY_PAGES, menu(pages));

        if(input.contains(PAGERY_SITE_INFO)) {
            input = input.replace(PAGERY_SITE_INFO, "Pagery 2016");
        }

        return input;
    }

    /**
     * Generate the menu from a list of existing pages.
     * @param pages all transformed pages
     * @return the html formatted menu
     */
    public String menu(List<Page> pages) {
        StringBuilder builder = new StringBuilder();

        pages.forEach(p -> {
                builder.append("<a href=\"/pages/"+p.getFontMatterMeta().getTitle()+".html\">"+p.getFontMatterMeta().getTitle()+"</a>")
                    .append(" \n");
        });

        return builder.toString();
    }
}
