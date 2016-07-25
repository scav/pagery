package com.devbugger.pagery.html.element;

import com.devbugger.pagery.html.attribute.css.CSSClass;
import com.devbugger.pagery.html.attribute.css.CSSId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ElementTest {

    @Test
    public void href() throws Exception {
        HtmlElement href = new AHref("Result", "data", "result")
                .attributes(new CSSClass("a hover"), new CSSId("menu link"));

        assertThat("href should contain the correct path",
                href.get(),
                is("<a href=\"/data/result.html\" class=\"a hover\" id=\"menu link\">Result</a>"));
    }
}
