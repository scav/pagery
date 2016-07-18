package com.devbugger.pagery.transform;

import com.devbugger.pagery.export.ExportHtml;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.fontmatter.FontMatterMeta;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class DefaultTransformerTest {

    private DefaultTransformer transformer;

    @Before
    public void setup() {
        transformer = new DefaultTransformer();
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
    }

    @Test
    public void transformPage() throws Exception {

    }

    @Test
    public void transformPostPage() throws Exception {
        List<Post> posts = new ArrayList<>();

        FontMatterMeta fontMatterMeta = new FontMatterMeta();
        fontMatterMeta.setTitle("One test");
        fontMatterMeta.setAuthor("Dag");
        fontMatterMeta.setCategories(Arrays.asList("stuff", "more stuff"));
        fontMatterMeta.setDateTime(LocalDateTime.now());
        fontMatterMeta.setType("post");
        Post post1 = new Post();
        post1.setFontMatterMeta(fontMatterMeta);
        post1.setContent("The first post is a very special post. It is a test case and it will be followed by a second soon.");
        posts.add(post1);

        fontMatterMeta = new FontMatterMeta();
        fontMatterMeta.setTitle("Second test");
        fontMatterMeta.setAuthor("Dag");
        fontMatterMeta.setCategories(Arrays.asList("second", "more second"));
        fontMatterMeta.setDateTime(LocalDateTime.now().plusDays(2));
        fontMatterMeta.setType("post");
        Post post2 = new Post();
        post2.setFontMatterMeta(fontMatterMeta);
        post2.setContent("Second post is very exiting. It contains things that are seconds.");
        posts.add(post2);

        PostPage postPage = transformer.transformPostPage("postpage.md", posts);

        System.out.println(postPage);

        //Some assert based on how we expect the final string to look.
    }

    @Test
    public void transformPost() throws Exception {
        Post post = transformer.transformPost("example/post.md");

        ExportHtml exportHtml = new ExportHtml();
        exportHtml.write(post.getContent());

        assertNotNull("Post should have font matter data", post.getFontMatterMeta());
        assertNotNull("Post should have content", post.getContent());
    }
}