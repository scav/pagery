package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class TransformPageryPostPageTest {

    private TransformPagery<List<Post>> transformPagery;

    @Before
    public void setup() {
        transformPagery = new TransformPageryPostPage();
    }

    @Test
    public void transform() throws Exception {

    }


}