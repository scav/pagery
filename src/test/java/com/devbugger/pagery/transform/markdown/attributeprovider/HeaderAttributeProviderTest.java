package com.devbugger.pagery.transform.markdown.attributeprovider;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.Template;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class HeaderAttributeProviderTest {

    private DefaultTransformer transformer;

    @Before
    public void setup() {
        Map<String, Template> templates = new LinkedHashMap<>();
        Template templateHeader = new Template();
        templateHeader.setAttribute("class");
        templateHeader.setValues(new String[]{"post-title"});
        templates.put("post.title", templateHeader);

        Config config = new Config();
        config.setTemplates(templates);
        transformer = new DefaultTransformer();
        transformer.setConfig(config);
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
    }

    @Test
    public void setAttributes() throws Exception {
        String rawPost =
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

        Post post = transformer.transformPost(rawPost);

        assertTrue("Should contain class with attribute",
                post.getContent().contains("post-title"));

    }
}
