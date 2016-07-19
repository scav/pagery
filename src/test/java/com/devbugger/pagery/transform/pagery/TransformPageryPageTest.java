package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Page;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TransformPageryPageTest {

    private TransformPagery<Page> transformPagery;

    @Before
    public void setup() {
        transformPagery = new TransformPageryPage();
    }

    @Test
    public void transform() throws Exception {

    }
}
