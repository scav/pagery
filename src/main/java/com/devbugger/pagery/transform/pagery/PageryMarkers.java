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
     * Defines the prefix used to define a marker.
     */
    private static final String PREFIX = "@";

    /**
     * Pagery base markers for {@link com.devbugger.pagery.site.BasePage}.
     * These are used when generating the basic template all other will be
     * extended from.
     */
    public static final String PAGERY_PAGES = PREFIX+"pagery.pages";
    public static final String PAGERY_TITLE = PREFIX+"pagery.title";
    public static final String PAGERY_CONTENT = PREFIX+"pagery.content";
    public static final String PAGERY_SITE_INFO = PREFIX+"pagery.info";
    public static final String PAGERY_BUILD_INFO = PREFIX+"pagery.buildinfo";

    /**
     * Post wrappers.
     * These wrap the post data and provides a way to define
     * where in the template the data is extracted.
     */
    public static final String POST_ALL = PREFIX+"post.all";
    public static final String POST_END = PREFIX+"post.end";

    /**
     * {@link com.devbugger.pagery.site.Post} data for {@link TransformPagery} where to extract
     * data at matching markers.
     */
    public static final String POST_TITLE = PREFIX+"post.title";
    public static final String POST_AUTHOR = PREFIX+"post.author";
    public static final String POST_DATE = PREFIX+"post.date";
    public static final String POST_CATEGORIES = PREFIX+"post.categories";
    public static final String POST_LEAD_PARAGRAPH = PREFIX+"post.lead";
    public static final String POST_LEAD_PARAGRAPH_START = PREFIX+"post.lead.start";
    public static final String POST_LEAD_PARAGRAPH_END = PREFIX+"post.lead.end";

    /**
     * {@link com.devbugger.pagery.site.Page} data for {@link TransformPagery} where to extract
     * data of site content.
     */
    public static final String PAGE_TITLE = PREFIX+"page.title";
    public static final String PAGE_DATE = PREFIX+"page.date";



}
