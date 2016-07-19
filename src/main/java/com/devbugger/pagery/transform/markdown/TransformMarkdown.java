package com.devbugger.pagery.transform.markdown;

import com.devbugger.pagery.transform.TransformerFileUtils;

public class TransformMarkdown implements TransformerFileUtils {
    public String transform(String input) {
        CommonMark commonMark = new CommonMark(input);
        return commonMark.get();
    }
}
