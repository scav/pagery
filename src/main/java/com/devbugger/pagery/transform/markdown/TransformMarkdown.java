package com.devbugger.pagery.transform.markdown;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.PageryConfigSupport;
import com.devbugger.pagery.transform.TransformerFileUtils;

public class TransformMarkdown implements TransformerFileUtils, PageryConfigSupport {

    private Config config;

    public String transform(String input) {
        CommonMark commonMark = new CommonMark(input);
        commonMark.setConfig(config);
        return commonMark.get();
    }

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }
}
