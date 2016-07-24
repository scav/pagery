package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.site.BasePage;
import com.devbugger .pagery.site.Page;

public interface TransformPageryBasePage<T,S> {

    /**
     * Transform the content within a template based on the occurrence
     * of markers defined in {@link PageryMarkers}
     * @param t the page type
     * @param s the input data
     * @param config active configuration file
     * @return the transformed html as S
     */
    T transform(Config config, T t, S s);

     /**
     *  This is a specialized, non-genric method for attaching
     * {@link com.devbugger.pagery.site.BasePage} content to the given {@link Page}.
     * @param basePage content to attach
     * @param page receiver of content
     * @return page with basepage content attached
     */
    Page attach(BasePage basePage, Page page);
}
