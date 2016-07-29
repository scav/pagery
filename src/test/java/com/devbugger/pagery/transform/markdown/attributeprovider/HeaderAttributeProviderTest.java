package com.devbugger.pagery.transform.markdown.attributeprovider;

import com.devbugger.pagery.configuration.PageryConfig;
import com.devbugger.pagery.configuration.PageryYAMLConfig;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class HeaderAttributeProviderTest {

    private PageryConfig yamlConfig = new PageryYAMLConfig();
    private DefaultTransformer transformer;

    @Before
    public void setup() {
        transformer = new DefaultTransformer();
        transformer.setConfig(yamlConfig.read("example/config.yaml"));
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
    }

    @Test
    public void setAttributes() throws Exception {
        Post post = transformer.transformPost("example/post/post-hello.md");

        assertTrue("Should contain class with attribute",
                post.getContent().contains("post-title"));

    }
}
