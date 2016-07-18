package com.devbugger.pagery.site;

import com.devbugger.pagery.transform.fontmatter.FontMatterMeta;

/**
 * A page containing a post, such as a blog post or a news article.
 * This is not the same as a {@link Page} but will be rendered by a
 * specialized version {@link PostPage}.
 */
public class Post extends Page {

    private FontMatterMeta fontMatterMeta;

    public Post() {
    }

    public Post(FontMatterMeta fontMatterMeta) {
        this.fontMatterMeta = fontMatterMeta;
    }

    public FontMatterMeta getFontMatterMeta() {
        return fontMatterMeta;
    }

    public void setFontMatterMeta(FontMatterMeta fontMatterMeta) {
        this.fontMatterMeta = fontMatterMeta;
    }

    @Override
    public String toString() {
        return "Post{" +
                "fontMatterMeta=" + fontMatterMeta +
                "} " + super.toString();
    }
}
