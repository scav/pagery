package com.devbugger.pagery.transform;

import com.devbugger.pagery.configuration.PageryConfigSupport;
import com.devbugger.pagery.site.*;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;

import java.util.List;

/**
 * Transformer defining all methods exposed to outside callers
 * when creating the complete in memory Java representation
 * of all the content used to create a complete page form templates.
 */
public interface Transformer extends PageryConfigSupport {
    void setTransformMarkdown(TransformMarkdown transformMarkdown);
    void setTransformFontMatter(TransformFontMatter transformFontMatter);

    /**
     * Generate the index page that will become index.html at the
     * root of the project.
     * @param content string representation of index page
     * @param posts available for listing at the index page
     * @return a complete basic page
     */
    IndexPage transformIndexPage(String content, List<Post> posts);

    /**
     * Generate the basic layout that all pages will inherit when
     * they are created.
     * @param content string representation of base page
     * @param pages to be included in the menu
     * @return a complete basic page
     */
    BasePage transformBasePage(String content, List<Page> pages);

    /**
     * Transforms a page by accepting its file location.
     * This method takes care of everything and places the page
     * in a stack of pages that will be used to do the final transformation.
     * @param content string representation of page
     * @return a complete page
     */
    Page transformPage(String content);

    /**
     * Transforms a page containing all posts.
     * @param content string representation of post page
     * @param posts all posts to add to the post page
     * @return a complete post page ready for writing
     */
    PostPage transformPostPage(String content, List<Post> posts);

    /**
     * Transforms one post ready to be written.
     * @param content string representation of post
     * @return a complete post ready for writing
     */
    Post transformPost(String content);


}
