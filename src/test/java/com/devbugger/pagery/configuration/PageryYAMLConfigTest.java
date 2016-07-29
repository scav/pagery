package com.devbugger.pagery.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class PageryYAMLConfigTest {

    private PageryConfig yamlConfig = new PageryYAMLConfig();

    @Test
    public void read() throws Exception {
        Config config = yamlConfig.read("example/config.yaml");

        System.out.println(config);

        assertNotNull("Config object should exist", config);
    }

    @Test
    public void mapTemplate() throws Exception {
        Template postTitle = new Template();
        postTitle.setAttribute("class");
        postTitle.setValues(new String[]{"post-title", "aheader"});

        Template postTitle2 = new Template();
        postTitle2.setAttribute("id");
        postTitle2.setValues(new String[]{"post-title2", "aheader2"});

        Map<String, Template> templates = new HashMap<>();
        templates.put("post.title", postTitle);
        templates.put("post.content", postTitle2);

        Config config = new Config();
        config.setTemplates(templates);
        String output = new Yaml().dump(config);



        System.out.println(output);
    }
}
