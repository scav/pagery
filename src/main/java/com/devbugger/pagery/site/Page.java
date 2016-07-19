package com.devbugger.pagery.site;


import com.devbugger.pagery.transform.fontmatter.FontMatterMeta;

/**
 * Defines a page in the system.
 * A Page can be any kind of content making up a site.
 */
public class Page {

    private FontMatterMeta fontMatterMeta;

    private String content;

    public Page() {

    }

    public Page(FontMatterMeta fontMatterMeta) {
        this.fontMatterMeta = fontMatterMeta;
    }

    public FontMatterMeta getFontMatterMeta() {
        return fontMatterMeta;
    }

    public void setFontMatterMeta(FontMatterMeta fontMatterMeta) {
        this.fontMatterMeta = fontMatterMeta;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Page{" +
                "fontMatterMeta=" + fontMatterMeta +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;

        Page page = (Page) o;

        if (getFontMatterMeta() != null ? !getFontMatterMeta().equals(page.getFontMatterMeta()) : page.getFontMatterMeta() != null)
            return false;
        return getContent() != null ? getContent().equals(page.getContent()) : page.getContent() == null;

    }

    @Override
    public int hashCode() {
        int result = getFontMatterMeta() != null ? getFontMatterMeta().hashCode() : 0;
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }
}
