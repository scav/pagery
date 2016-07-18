package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Post;

import java.util.stream.Collectors;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;

public class TransformPageryPost implements TransformPagery<Post> {

    private static final String CATEGORY_SEPARATOR = " ";
    private static int POST_PARTIAL_OVERFLOW = 100;

    @Override
    public String transform(String input, Post post) {
        if(input.contains(POST_TITLE))
            input = input.replace(POST_TITLE, post.getFontMatterMeta().getTitle());
        if(input.contains(POST_AUTHOR)) {
            input = input.replace(POST_AUTHOR, post.getFontMatterMeta().getAuthor());
        }
        if(input.contains(POST_DATE)) {
            input = input.replace(POST_DATE, post.getFontMatterMeta().getDateTime().toString());
        }
        if(input.contains(POST_CATEGORIES)) {
            input = input.replace(POST_CATEGORIES, post.getFontMatterMeta()
                    .getCategories().stream()
                    .map(p -> p)
                    .collect(Collectors.joining(CATEGORY_SEPARATOR)));
        }
        if(input.contains(POST_PARTIAL)) {
            if(input.length() <= 100) {
                POST_PARTIAL_OVERFLOW = input.length() / 3;
            }
            input = input.replace(POST_PARTIAL, post.getContent().substring(0, POST_PARTIAL_OVERFLOW)+"...");
        }

        return input;
    }

}
