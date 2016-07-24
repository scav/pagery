package com.devbugger.pagery.transform.pagery;

/**
 * Contains all the markers supported in context specific
 * Pagery markdown pages.
 *
 * These markers are the only valid pagery template markers
 * Pagery will support.
 */
public class PageryMarkers {

    /**
     * Pagery base markers for {@link com.devbugger.pagery.site.BasePage}.
     * These are used when generating the basic template all other will be
     * extended from.
     */
    public static final String PAGERY_PAGES = "@pagery.pages";
    public static final String PAGERY_TITLE = "@pagery.title";
    public static final String PAGERY_CONTENT = "@pagery.content";
    public static final String PAGERY_SITE_INFO = "@pagery.info";
    public static final String PAGERY_BUILD_INFO = "@pagery.buildinfo";

    /**
     * Post wrappers.
     * These wrap the post data and provides a way to define
     * where in the template the data is extracted.
     */
    public static final String POST_ALL = "@post.all";
    public static final String POST_END = "@post.end";

    /**
     * {@link com.devbugger.pagery.site.Post} data for {@link TransformPagery} where to extract
     * data at matching markers.
     */
    public static final String POST_TITLE = "@post.title";
    public static final String POST_AUTHOR = "@post.author";
    public static final String POST_DATE = "@post.date";
    public static final String POST_CATEGORIES = "@post.categories";
    public static final String POST_LEAD_PARAGRAPH = "@post.lead";
    public static final String POST_LEAD_PARAGRAPH_START = "@post.lead.start";
    public static final String POST_LEAD_PARAGRAPH_END = "@post.lead.end";

    /**
     * {@link com.devbugger.pagery.site.Page} data for {@link TransformPagery} where to extract
     * data of site content.
     */
    public static final String PAGE_TITLE = "@page.title";
    public static final String PAGE_DATE = "@page.date";



}
