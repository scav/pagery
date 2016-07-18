package com.devbugger.pagery.transform;

import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.TransformPagery;
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
    public Page transformPage(String path) {
        return null;
    }

    @Override
    public PostPage transformPostPage(String path, List<Post> posts) {
        TransformPagery<List<Post>> transformPagery = new TransformPageryPostPage();
        PostPage postPage = new PostPage();
        String rawContent = transformMarkdown.transform(generate(path));
        postPage.setContent(transformPagery.transform(rawContent, posts));

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
