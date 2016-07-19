package com.devbugger.pagery.export;

import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;

import java.util.List;

/**
 * Utility class for generating lists of all the resources
 * that will be exported.
 */
public class GeneratePages {

    private final DefaultTransformer transformer;

    private List<Post> posts;
    private List<Page> pages;
    private PostPage postPage;
    private BasePage basePage;

    public GeneratePages() {
        transformer = new DefaultTransformer();
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
    }

    public DefaultTransformer getTransformer() {
        return transformer;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Page> getPages() {
        return pages;
    }

    public PostPage getPostPage() {
        return postPage;
    }

    public BasePage getBasePage() {
        return basePage;
    }
}
