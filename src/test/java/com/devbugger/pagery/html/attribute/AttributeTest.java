package com.devbugger.pagery.html.attribute;

import com.devbugger.pagery.html.attribute.css.CSSClass;
import com.devbugger.pagery.html.attribute.css.CSSId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class AttributeTest {

    @Test
    public void cssClass() throws Exception {
        CSSClass cssClass = new CSSClass("menu", "header", "nav");
        assertEquals(cssClass.get(), "class=\"menu header nav\"");
    }

    @Test
    public void cssId() throws Exception {
        CSSId cssId = new CSSId("nav-bar", "nav");
        assertEquals(cssId.get(), "id=\"nav-bar nav\"");
    }
}
