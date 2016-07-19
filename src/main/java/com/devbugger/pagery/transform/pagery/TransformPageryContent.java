package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;

import java.util.List;

public interface TransformPageryContent extends TransformPagery<List<Page>> {

    /**
     * Complete the transformation of class extending {@link Page} by
     * adding its content to the {@link PageryMarkers}.PAGERY_CONTENT tag.
     * @param basePage marker location
     * @param page transformation candidate
     * @return the complete page ready to be written as html
     */
    String complete(BasePage basePage, Page page);
}
