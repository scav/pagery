package com.devbugger.pagery.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PageryYAMLConfigTest {

    private PageryConfig yamlConfig = new PageryYAMLConfig();

    @Test
    public void read() throws Exception {
        Config config = yamlConfig.read("example/project.yaml");

        System.out.println(config);
    }
}
