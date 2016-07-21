package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Page;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.PAGE_DATE;
import static com.devbugger.pagery.transform.pagery.PageryMarkers.PAGE_TITLE;

public class TransformPageryPage implements TransformPagery<Page> {

    @Override
    public Page transform(Page page) {
        String input = page.getContent();
        if(input.contains(PAGE_TITLE))
            input = input.replace(PAGE_TITLE, page.getFontMatterMeta().getTitle());
        if(input.contains(PAGE_DATE))
            input = input.replace(PAGE_DATE, page.getFontMatterMeta().getDateTime().toString());

        page.setContent(input);

        return page;
    }

}
