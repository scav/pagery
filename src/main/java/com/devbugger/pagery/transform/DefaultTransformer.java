package com.devbugger.pagery.transform;

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
        String content = generate(path);

        BasePage basePage = new BasePage(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        basePage.setContent(transformMarkdown.transform(content));
        basePage.setContent(transformPagery.transform(basePage.getContent(), pages));

        return basePage;
    }

    @Override
    public Page transformPage(String path) {
        TransformPagery<Page> transformPagery = new TransformPageryPage();
        String content = generate(path);

        Page page = new Page(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        page.setContent(transformMarkdown.transform(content));
        page.setContent(transformPagery.transform(page.getContent(), page));

        return page;
    }

    @Override
    public PostPage transformPostPage(String path, List<Post> posts) {
        TransformPagery<List<Post>> transformPagery = new TransformPageryPostPage();
        String content = generate(path);

        PostPage postPage = new PostPage(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        postPage.setContent(transformMarkdown.transform(content));

        postPage.setContent(transformPagery.transform(postPage.getContent(), posts));

        return postPage;
    }

    @Override
    public Post transformPost(String path) {
        TransformPageryPost transformPagery = new TransformPageryPost();
        String content = generate(path);

        Post post = new Post(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        post.setContent(transformMarkdown.transform(content));

        post = transformPagery.transform(post);

        return post;
    }

}
