package com.devbugger.pagery.html.element;

import com.devbugger.pagery.html.attribute.Charset;
import com.devbugger.pagery.html.attribute.Href;
import com.devbugger.pagery.html.attribute.Rel;
import com.devbugger.pagery.html.attribute.Type;
import com.devbugger.pagery.html.attribute.css.CSSClass;
import com.devbugger.pagery.html.attribute.css.CSSId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.devbugger.pagery.html.attribute.AttributeType.CHARSET_UTF8;
import static com.devbugger.pagery.html.attribute.AttributeType.STYLESHEET;
import static com.devbugger.pagery.html.attribute.AttributeType.TEXT_CSS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ElementTest {

    @Test
    public void AHref() throws Exception {
        HtmlElement href = new AHref("Result", "data", "result")
                .attributes(new CSSClass("a hover"), new CSSId("menu link"));

        assertThat("AHref should contain the correct path",
                href.get(),
                is("<a href=\"/data/result.html\" class=\"a hover\" id=\"menu link\">Result</a>"));
    }

    @Test
    public void link() throws Exception {
        HtmlElement link = new Link()
                .attributes(new Rel(STYLESHEET), new Type(TEXT_CSS), new Charset(CHARSET_UTF8), new Href("style.css"));

        assertThat("Stylesheet link should contain all elements",
                link.get(),
                is("<link rel=\"stylesheet\" type=\"text/css\" charset=\"utf-8\" href=\"style.css\"</link>"));
    }
}
