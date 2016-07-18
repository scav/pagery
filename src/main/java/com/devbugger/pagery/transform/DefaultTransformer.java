package com.devbugger.pagery.transform;

import com.devbugger.pagery.export.ExportHtml;
import com.devbugger.pagery.site.BasePage;
import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.*;

import java.util.List;

public class DefaultTransformer implements Transformer, TransformerFileUtils {

    private TransformMarkdown transformMarkdown;
    private TransformFontMatter transformFontMatter;

    private ExportHtml exportHtml = new ExportHtml();

    @Override
    public void setTransformMarkdown(TransformMarkdown transformMarkdown) {
        this.transformMarkdown = transformMarkdown;
    }

    @Override
    public void setTransformFontMatter(TransformFontMatter transformFontMatter) {
        this.transformFontMatter = transformFontMatter;
    }

    @Override
    public BasePage transformBasePage(String path, List<Page> pages) {
        TransformPagery<List<Page>> transformPagery = new TransformPageryBase();
        List<String> content = generate(path);

        BasePage basePage = new BasePage(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        basePage.setContent(transformMarkdown.transform(content));
        basePage.setContent(transformPagery.transform(basePage.getContent(), pages));

        exportHtml.write(basePage.getContent(), basePage.getFontMatterMeta().getTitle());

        return basePage;
    }

    @Override
    public Page transformPage(String path) {
        TransformPagery<Page> transformPagery = new TransformPageryPage();
        List<String> content = generate(path);

        Page page = new Page(transformFontMatter.create(content));
        page.setContent(transformMarkdown.transform(generate(path)));
        page.setContent(transformPagery.transform(page.getContent(), page));

        exportHtml.write(page.getContent(), "pages/"+page.getFontMatterMeta().getTitle());

        return page;
    }

    @Override
    public PostPage transformPostPage(String path, List<Post> posts) {
        TransformPagery<List<Post>> transformPagery = new TransformPageryPostPage();
        List<String> content = generate(path);

        PostPage postPage = new PostPage(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        postPage.setContent(transformMarkdown.transform(content));

        postPage.setContent(transformPagery.transform(postPage.getContent(), posts));

        exportHtml.write(postPage.getContent(), "pages/"+postPage.getFontMatterMeta().getTitle());

        return postPage;
    }

    @Override
    public Post transformPost(String path) {
        TransformPagery<Post> transformPagery = new TransformPageryPost();
        List<String> content = generate(path);

        Post post = new Post(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        post.setContent(transformMarkdown.transform(content));

        post.setContent(transformPagery.transform(post.getContent(), post));

        exportHtml.write(post.getContent(), "posts/"+post.getFontMatterMeta().getTitle());

        return post;
    }

}
