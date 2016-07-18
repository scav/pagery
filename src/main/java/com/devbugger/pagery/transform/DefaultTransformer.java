package com.devbugger.pagery.transform;

import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.TransformPagery;
import com.devbugger.pagery.transform.pagery.TransformPageryPage;
import com.devbugger.pagery.transform.pagery.TransformPageryPost;
import com.devbugger.pagery.transform.pagery.TransformPageryPostPage;

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
    public Page transformBasePage(List<Page> pages) {
        return null;
    }

    @Override
    public Page transformPage(String path) {
        TransformPagery<Page> transformPagery = new TransformPageryPage();
        List<String> content = generate(path);

        Page page = new Page(transformFontMatter.create(content));
        page.setContent(transformMarkdown.transform(generate(path)));
        page.setContent(transformPagery.transform(page.getContent(), page));

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

        return post;
    }

}
