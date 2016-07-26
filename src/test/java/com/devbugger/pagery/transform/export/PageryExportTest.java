package com.devbugger.pagery.transform.export;


import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.PageryYAMLConfig;
import com.devbugger.pagery.export.ExportHtml;
import com.devbugger.pagery.export.GeneratePages;
import com.devbugger.pagery.export.GenerateResources;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.DefaultTransformer;
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

/**
 * Test creating a complete site with all elements,
 * menus, post lists, posts, pages and base page ready.
 */
@RunWith(JUnit4.class)
public class PageryExportTest {

    private Config config;
    private DefaultTransformer transformer;

    @Before
    public void setup() {
        config = new PageryYAMLConfig().read("example/config.yaml");

        transformer = new DefaultTransformer();
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
        transformer.setConfig(config);
    }

    @Test
    public void initializeData() throws Exception {
        GeneratePages generatePages = new GeneratePages(config);
        GenerateResources generateResources = new GenerateResources(config);
    }

}
