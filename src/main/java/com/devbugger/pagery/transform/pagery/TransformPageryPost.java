package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.html.element.AHref;
import com.devbugger.pagery.site.Post;

import java.util.stream.Collectors;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;

public class TransformPageryPost implements TransformPagery<Post> {

    static final String CATEGORY_SEPARATOR = " ";

    @Override
    public Post transform(Post post) {
        String input = post.getContent();

        if(input.contains(POST_TITLE))
            input = input.replace(POST_TITLE,
                    new AHref(post.getFontMatterMeta().getTitle(),
                            post.getFontMatterMeta().getType(), post.getFontMatterMeta().getTitle())
                            .get());
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
        if(input.contains(POST_LEAD_PARAGRAPH_START) || input.contains(POST_LEAD_PARAGRAPH_END)) {
            int start = input.indexOf(POST_LEAD_PARAGRAPH_START)+POST_LEAD_PARAGRAPH_START.length();
            int end = input.indexOf(POST_LEAD_PARAGRAPH_END);

            post.setLeadPargraph(input.substring(start, end));

            input = input.replace(POST_LEAD_PARAGRAPH_START, "");
            input = input.replace(POST_LEAD_PARAGRAPH_END, "");
        }

        post.setContent(input);

        return post;
    }

}
