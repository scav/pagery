package com.devbugger.pagery.transform;

import com.devbugger.pagery.configuration.PageryYAMLConfig;
import com.devbugger.pagery.export.ExportHtml;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class DefaultTransformerTest {

    private DefaultTransformer transformer;

    @Before
    public void setup() {
        transformer = new DefaultTransformer();
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
        transformer.setConfig(new PageryYAMLConfig().read("example/config.yaml"));
    }

    @Test
    public void transformBasePage() throws Exception {
        List<Page> pages = new ArrayList<>();

        //Create some pages to help create the menu.
        pages.add(transformer.transformPage("example/page/about.md"));
        pages.add(transformer.transformPage("example/page/contact.md"));

        BasePage p = transformer.transformBasePage("example/template.html", pages);

        ExportHtml exportHtml = new ExportHtml();
        exportHtml.write(p.getContent(), "index.html");

    }

    @Test
    public void transformPage() throws Exception {
        Page page = transformer.transformPage("example/page/about.md");
    }

    @Test
    public void transformPostPage() throws Exception {

    }

    @Test
    public void transformPost() throws Exception {
        Post post = transformer.transformPost("example/post/post-hello.md");

        ExportHtml exportHtml = new ExportHtml();
        exportHtml.write(post.getContent(), post.getFontMatterMeta().getTitle());

        assertNotNull("Post should have font matter data", post.getFontMatterMeta());
        assertNotNull("Post should have content", post.getContent());
    }

}
