package com.devbugger.pagery.transform.pagery;

public class PageryMarkers {

    /**
     * Post wrappers.
     * These wrap the post data and provides a way to define
     * where in the template the data is extracted.
     */
    public static final String POST_ALL = "@post.all";
    public static final String POST_END = "@post.end";

    /**
     * Post data.
     * Tell the {@link TransformPagery} where to extract
     * data at matching markers.
     */
    public static final String POST_TITLE = "@post.title";
    public static final String POST_AUTHOR = "@post.author";
    public static final String POST_DATE = "@post.date";
    public static final String POST_CATEGORIES = "@post.categories";
    public static final String POST_PARTIAL = "@post.partial";

    /**
     * Page data.
     * Tell the {@link TransformPagery} where to extract
     * data of site content.
     */
    public static final String PAGERY_PAGES = "@pagery.pages";
    public static final String PAGERY_SITE_INFO = "@pagery.info";


}
