package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.PageryYAMLConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DefaultTransformPageryBasePageTest {

    private DefaultTransformPageryBaseBage transformPagery;

    @Before
    public void setup() {
        transformPagery = new DefaultTransformPageryBaseBage();
        transformPagery.setConfig(new PageryYAMLConfig().read("example/config.yaml"));
    }

    @Test
    public void test() throws Exception {

    }

}
