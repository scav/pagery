package com.devbugger.pagery.transform.pagery;


import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

/**
 * Test creating a complete site with all elements,
 * menus, post lists, posts, pages and base page ready.
 */
@RunWith(JUnit4.class)
public class PageryExportTest {

    private DefaultTransformer transformer;

    @Before
    public void setup() {
        transformer = new DefaultTransformer();
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
    }

    @Test
    public void export() throws Exception {
        List<Post> posts = new ArrayList<>();
        List<Page> pages = new ArrayList<>();


        posts.add(transformer.transformPost("example/posts/post-hello.md"));
        posts.add(transformer.transformPost("example/posts/post-status.md"));

        PostPage postPage = transformer.transformPostPage("example/postpage.md", posts);
        pages.add(postPage);

        pages.add(transformer.transformPage("example/pages/about.md"));
        pages.add(transformer.transformPage("example/pages/contact.md"));

        transformer.transformBasePage("example/basepage.md", pages);

    }

}
