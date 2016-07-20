package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TransformPageryPostTest {

    private TransformPagery<Post> transformPagery;

    @Before
    public void setup() {
        transformPagery = new TransformPageryPost();
    }

    @Test
    public void transform() throws Exception {

    }

}