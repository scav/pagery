package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.transform.fontmatter.FontMatterMeta;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TransformPageryPostTest {

    private TransformPagery<Post> transformPagery;

    @Before
    public void setup() {
        transformPagery = new TransformPageryPost();
    }

    @Test
    public void transform() throws Exception {
        String expected =
                "<h2>One test</h2>" +
                "in: stuff more stuff" +
                "Dag" +
                "<h3>Hello world!</h3>" +
                "I have so much to tell you. But first," +
                " lets do a test post to make sure we are transforming correctly.";

        FontMatterMeta fontMatterMeta = new FontMatterMeta();
        fontMatterMeta.setTitle("One test");
        fontMatterMeta.setAuthor("Dag");
        fontMatterMeta.setCategories(Arrays.asList("stuff", "more stuff"));
        fontMatterMeta.setDateTime(LocalDateTime.now());
        fontMatterMeta.setType("post");
        Post post = new Post();
        post.setFontMatterMeta(fontMatterMeta);
        post.setContent(
                    "<h2>@post.title</h2>" +
                    "in: @post.categories" +
                    "@post.author" +
                    "<h3>Hello world!</h3>" +
                    "I have so much to tell you. But first," +
                    " lets do a test post to make sure we are transforming correctly."
        );

        post.setContent(transformPagery.transform(post.getContent(), post));

        assertEquals("Expected output should match actual output", expected, post.getContent());
    }

}