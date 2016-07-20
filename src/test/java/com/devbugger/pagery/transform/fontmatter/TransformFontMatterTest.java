package com.devbugger.pagery.transform.fontmatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class TransformFontMatterTest {

    private String input;
    private String exclude;

    TransformFontMatter transform = new TransformFontMatter();

    @Before
    public void setup() {
        input = "---\n" +
                "title: Hello world!\n" +
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
                "Hello world!\n" +
                "---\n" +
                "raff\n" +
                "daff\n" +
                "gaff\n" +
                "---\n" +
                "12312312\n";

        exclude = "---\n" +
                "title: Hello world!\n" +
                "author: Dag\n" +
                "date: 2016-07-24 10:15:30\n" +
                "type: post\n" +
                "categories: post,first\n" +
                "---\n";
    }

    @Test
    public void stripFontMatter() throws Exception {
        input = transform.stripFontMatter(input);

        System.out.println(input);

        assertFalse("Input should not contain excluded characters",
                input.contains(exclude));
    }

    @Test
    public void findIndex() throws Exception {
        assertThat("Index should be 96",
                transform.openCloseEndIndex(input), is(96));

    }
}
