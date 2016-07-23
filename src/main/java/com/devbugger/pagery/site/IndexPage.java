package com.devbugger.pagery.site;

import com.devbugger.pagery.transform.fontmatter.FontMatterMeta;

public class IndexPage extends Post {

    public IndexPage(FontMatterMeta fontMatterMeta) {
        super(fontMatterMeta);
    }

    @Override
    public String toString() {
        return "IndexPage{} " + super.toString();
    }
}
