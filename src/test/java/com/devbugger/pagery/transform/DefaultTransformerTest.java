package com.devbugger.pagery.transform;

import com.devbugger.pagery.FileUtils;
import com.devbugger.pagery.configuration.PageryYAMLConfig;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.DefaultTransformPageryBaseBage;
import com.devbugger.pagery.transform.pagery.TransformPageryBasePage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.PAGERY_CONTENT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DefaultTransformerTest {

    private DefaultTransformer transformer;

    @Before
    public void setup() {
        transformer = new DefaultTransformer();
        transformer.setConfig(new PageryYAMLConfig().read(new FileUtils().generatePath("test-config.yaml")));
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
    }

    @Test
    public void transformBasePage() throws Exception {
        String page1 = "---\n" +
                "title: Test First\n" +
                "type: page\n" +
                "author: Dag\n" +
                "date: 2016-07-23 10:15:30\n" +
                "---\n" +
                "\n" +
                "# Test Page First\n" +
                "\n" +
                "This is the first test page.";

        String page2 = "---\n" +
                "title: Test Second\n" +
                "type: page\n" +
                "author: Dag\n" +
                "date: 2016-07-24 10:15:30\n" +
                "---\n" +
                "\n" +
                "# Test Page Second\n" +
                "\n" +
                "This is the second test page.";

        List<Page> pages = new ArrayList<>();

        //Create some pages to help create the menu.
        pages.add(transformer.transformPage(page1));
        pages.add(transformer.transformPage(page2));

        String content = new FileUtils().generate("template.html");

        BasePage basePage = transformer.transformBasePage(content, pages);
        TransformPageryBasePage<BasePage, Page> base = new DefaultTransformPageryBaseBage();
        pages.forEach(p -> p = base.attach(basePage, p, pages));

        pages.forEach(p ->
                assertFalse("All pages should be created without content markers",
                        p.getContent().contains(PAGERY_CONTENT)));
    }

    @Test
    public void transformPage() throws Exception {
        String content = "---\n" +
                "title: Test\n" +
                "type: page\n" +
                "author: Dag\n" +
                "date: 2016-07-24 10:15:30\n" +
                "---\n" +
                "\n" +
                "# Test Page\n" +
                "\n" +
                "This is a test page.";

        String expected = "<h1>Test Page</h1>\n" +
                "<p>This is a test page.</p>\n";

        Page page = transformer.transformPage(content);


        assertThat("Page content should match expected", page.getContent(), is(expected));
    }

    @Test
    public void transformPostPage() throws Exception {
        String post1 =
                "---\n" +
                "title: Post One\n" +
                "author: Dag\n" +
                "date: 2016-07-24 10:15:30\n" +
                "type: post\n" +
                "categories: post,first\n" +
                "---\n" +
                "## @post.title\n" +
                "by @post.author @ @post.date\n" +
                "in @post.categories\n" +
                "\n" +
                "@post.lead.start\n" +
                "First!\n" +
                "\n" +
                "This is the lead part of the post\n" +
                "@post.lead.end\n" +
                "\n" +
                "This is no longer part of the head.\n";

        String post2 =
                "---\n" +
                "title: Post Two\n" +
                "author: Dag\n" +
                "date: 2016-07-25 10:15:30\n" +
                "type: post\n" +
                "categories: post,second\n" +
                "---\n" +
                "## @post.title\n" +
                "by @post.author @ @post.date\n" +
                "in @post.categories\n" +
                "\n" +
                "@post.lead.start\n" +
                "Second!\n" +
                "\n" +
                "This is the lead part of the post\n" +
                "@post.lead.end\n" +
                "\n" +
                "This is no longer part of the head.";

        String expected =
                "<a href=\"/post/Post One.html\">Post One</a>\n" +
                "    Dag\n" +
                "    \n" +
                "First!</p>\n" +
                "<p>This is the lead part of the post\n" +
                "\n" +
                "\n" +
                "    <a href=\"/post/Post Two.html\">Post Two</a>\n" +
                "    Dag\n" +
                "    \n" +
                "Second!</p>\n" +
                "<p>This is the lead part of the post";

        List<Post> posts = new ArrayList<>();
        posts.add(transformer.transformPost(post1));
        posts.add(transformer.transformPost(post2));

        String content = new FileUtils().generate("post.html");
        PostPage postPage = transformer.transformPostPage(content, posts);

        assertTrue("Post page should contain posts", postPage.getContent().contains(expected));
    }

    @Test
    public void transformPost() throws Exception {
        String content =
                "---\n" +
                "title: Pagery\n" +
                "author: Dag\n" +
                "date: 2016-07-24 10:15:30\n" +
                "type: post\n" +
                "categories: post,first\n" +
                "---\n" +
                "\n" +
                "## @post.title\n" +
                "by @post.author @ @post.date\n" +
                "in @post.categories\n" +
                "\n" +
                "\n" +
                "@post.lead.start\n" +
                "Small post lead text.\n" +
                "@post.lead.end\n" +
                "\n" +
                "Rest of the content starts here.\n" +
                "Post test is over.";

        String expected =
                "<h2 class=\"post-title aheader\"><a href=\"/post/Pagery.html\">Pagery</a></h2>\n" +
                "<p>by Dag @ 2016-07-24T10:15:30\n" +
                "in post first</p>\n" +
                "<p>\n" +
                "Small post lead text.\n" +
                "</p>\n" +
                "<p>Rest of the content starts here.\n" +
                "Post test is over.</p>\n";

        Post post = transformer.transformPost(content);

        assertThat("Post content should match expected", post.getContent(), is(expected));
    }

}
