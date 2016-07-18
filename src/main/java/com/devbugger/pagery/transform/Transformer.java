package com.devbugger.pagery.transform;

import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;

import java.util.List;

/**
 * Transformer defining all methods exposed to outside callers
 * when creating the complete in memory Java representation
 * of all the content used to create a complete page form templates.
 */
public interface Transformer {
    void setTransformMarkdown(TransformMarkdown transformMarkdown);
    void setTransformFontMatter(TransformFontMatter transformFontMatter);

    /**
     * Transforms a page by accepting its file location.
     * This method takes care of everything and places the page
     * in a stack of pages that will be used to do the final transformation.
     * @param path to file location
     * @return a complete page
     */
    Page transformPage(String path);

    /**
     * Transforms a page containing all posts.
     * @param path to the post page template
     * @param posts all posts to add to the post page
     * @return a complete post page ready for writing
     */
    PostPage transformPostPage(String path, List<Post> posts);

    /**
     * Transforms one post ready to be written.
     * @param path to the post template
     * @return a complete post ready for writing
     */
    Post transformPost(String path);


}
