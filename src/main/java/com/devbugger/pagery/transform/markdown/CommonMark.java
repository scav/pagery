package com.devbugger.pagery.transform.markdown;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.PageryConfigSupport;
import com.devbugger.pagery.transform.markdown.attributeprovider.HeaderAttributeProvider;
import org.commonmark.html.HtmlRenderer;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

class CommonMark implements PageryConfigSupport {

    private Config config;
    private String input;

    CommonMark(String input) {
        this.input = input;
    }

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

    public String get() {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(input);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProvider(new HeaderAttributeProvider(config))
                .build();
        return renderer.render(document);
    }
}
