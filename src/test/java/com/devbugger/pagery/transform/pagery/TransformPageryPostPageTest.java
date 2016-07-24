package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TransformPageryPostPageTest {

    private TransformPageryWithResources<PostPage, Post> transformPagery;

    @Before
    public void setup() {
        transformPagery = new TransformPageryPostPage();
    }

    @Test
    public void transform() throws Exception {

    }


}
