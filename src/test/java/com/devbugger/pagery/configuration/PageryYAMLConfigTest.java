package com.devbugger.pagery.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class PageryYAMLConfigTest {

    private PageryConfig yamlConfig = new PageryYAMLConfig();

    @Test
    public void read() throws Exception {
        Config config = yamlConfig.read("example/config.yaml");

        config.getFiles().getResources().forEach(System.out::println);

        assertNotNull("Config object should exist", config);
    }
}
