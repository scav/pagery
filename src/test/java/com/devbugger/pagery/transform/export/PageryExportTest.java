package com.devbugger.pagery.transform.export;


import com.devbugger.pagery.export.ExportHtml;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.TransformPageryBase;
import com.devbugger.pagery.transform.pagery.TransformPageryContent;
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

        posts.add(transformer.transformPost("example/post/post-hello.md"));
        posts.add(transformer.transformPost("example/post/post-status.md"));
        posts.add(transformer.transformPost("example/post/post-pagery.md"));
        PostPage postPage = transformer.transformPostPage("example/postpage.md", posts);
        pages.add(postPage);

        pages.add(transformer.transformPage("example/page/about.md"));
        pages.add(transformer.transformPage("example/page/contact.md"));
        pages.add(transformer.transformPage("example/page/markdown.md"));

        BasePage basePage = transformer.transformBasePage("example/basepage.md", pages);

        // Combine all pages
        List<Page> allPages = new ArrayList<>();
        allPages.addAll(pages);
        allPages.addAll(posts);

        // Add the surrounding page content with the base page
        TransformPageryContent base = new TransformPageryBase();
        allPages.forEach(p -> p.setContent(base.complete(basePage, p)));

        // Write all pages to disk
        ExportHtml exportHtml = new ExportHtml();
        allPages.forEach(exportHtml::write);

    }

}
