package com.devbugger.pagery.html.attribute;

import com.devbugger.pagery.html.attribute.css.CSSClass;
import com.devbugger.pagery.html.attribute.css.CSSId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.devbugger.pagery.html.attribute.AttributeType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

    @Test
    public void rel() throws Exception {
        Rel rel = new Rel(STYLESHEET);
        assertEquals(rel.get(), "rel=\"stylesheet\"");
    }

    @Test
    public void type() throws Exception {
        Type type = new Type(TEXT_CSS);
        assertEquals(type.get(), "type=\"text/css\"");
    }

    @Test
    public void charset() throws Exception {
        Charset charset = new Charset(CHARSET_UTF8);
        assertEquals(charset.get(), "charset=\"utf-8\"");
    }

    @Test
    public void href() throws Exception {
        Href href = new Href("example.org");
        assertEquals(href.get(), "href=\"example.org\"");
    }
}
