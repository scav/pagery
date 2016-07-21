package com.devbugger.pagery.site;

import com.devbugger.pagery.transform.fontmatter.FontMatterMeta;

/**
 * A page containing a post, such as a blog post or a news article.
 * This is not the same as a {@link Page} but will be rendered by a
 * specialized version {@link PostPage}.
 */
public class Post extends Page {

    private String leadPargraph;

    public Post() {

    }

    public Post(FontMatterMeta fontMatterMeta) {
        super(fontMatterMeta);
    }

    public String getLeadPargraph() {
        return leadPargraph;
    }

    public void setLeadPargraph(String leadPargraph) {
        this.leadPargraph = leadPargraph;
    }

    @Override
    public String toString() {
        return "Post{" +
                "leadPargraph='" + leadPargraph + '\'' +
                "} " + super.toString();
    }
}
