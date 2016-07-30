package com.devbugger.pagery.transform;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.site.*;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.*;

import java.util.List;

/**
 * Default transformer implementation.
 *
 * This class exposes all available functionality outside of its package.
 */
public class DefaultTransformer implements Transformer {

    private Config config;
    private TransformMarkdown transformMarkdown;
    private TransformFontMatter transformFontMatter;

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void setTransformMarkdown(TransformMarkdown transformMarkdown) {
        this.transformMarkdown = transformMarkdown;
        this.transformMarkdown.setConfig(config);
    }

    @Override
    public void setTransformFontMatter(TransformFontMatter transformFontMatter) {
        this.transformFontMatter = transformFontMatter;
    }

    @Override
    public IndexPage transformIndexPage(String content, List<Post> posts) {
        TransformPageryWithResources<IndexPage, Post> transformPagery = new TransformPageryIndexPage();
        transformPagery.setConfig(config);

        IndexPage indexPage = new IndexPage(transformFontMatter.create(content));
        indexPage.setContent(transformFontMatter.stripFontMatter(content));

        return transformPagery.transform(indexPage, posts);
    }

    @Override
    public BasePage transformBasePage(String content, List<Page> pages) {
        TransformPageryBasePage<BasePage, Page> transformPagery = new DefaultTransformPageryBaseBage();
        transformPagery.setConfig(config);

        BasePage basePage = new BasePage();
        basePage.setContent(content);

        return transformPagery.transform(config, basePage, pages);
    }

    @Override
    public Page transformPage(String content) {
        TransformPagery<Page> transformPagery = new TransformPageryPage();

        Page page = new Page(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        page.setContent(transformMarkdown.transform(content));

        return transformPagery.transform(page);
    }

    @Override
    public PostPage transformPostPage(String content, List<Post> posts) {
        TransformPageryWithResources<PostPage, Post> transformPagery = new TransformPageryPostPage();

        PostPage postPage = new PostPage(transformFontMatter.create(content));
        postPage.setContent(transformFontMatter.stripFontMatter(content));

        return transformPagery.transform(postPage, posts);
    }

    @Override
    public Post transformPost(String content) {
        TransformPageryPost transformPagery = new TransformPageryPost();

        Post post = new Post(transformFontMatter.create(content));
        content = transformFontMatter.stripFontMatter(content);
        post.setContent(transformMarkdown.transform(content));

        post = transformPagery.transform(post);

        return post;
    }

}
