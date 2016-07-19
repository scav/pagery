package com.devbugger.pagery.transform.markdown;

import org.commonmark.html.HtmlRenderer;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

public class CommonMark {

    private Node document;
    private HtmlRenderer renderer;

    public CommonMark(String input) {
        Parser parser = Parser.builder().build();
        document = parser.parse(input);
        renderer = HtmlRenderer.builder().build();
    }

    public String get() {
        return renderer.render(document);
    }
}
