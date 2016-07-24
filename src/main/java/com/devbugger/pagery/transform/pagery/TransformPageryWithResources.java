package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.PageryConfigSupport;
import com.devbugger.pagery.site.Post;

import java.util.List;
import java.util.stream.Collectors;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;
import static com.devbugger.pagery.transform.pagery.TransformPageryPost.CATEGORY_SEPARATOR;

/**
 * Transform a {@link com.devbugger.pagery.site.Page} that require external
 * resources in order to be transformed correctly.
 * @param <T>
 * @param <S>
 */
public interface TransformPageryWithResources<T, S> extends PageryConfigSupport {

    T transform(T t, List<S> s);

    default String transform(String input, Post post) {
        if(input.contains(POST_TITLE))
            input = input.replace(POST_TITLE,
                    "<a href=\"/"+post.getFontMatterMeta().getType()+"/"+post.getFontMatterMeta().getTitle()+".html\">"+
                            post.getFontMatterMeta().getTitle()+"</a>");
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
            input = input.replace(POST_LEAD_PARAGRAPH_START, "").replace(POST_LEAD_PARAGRAPH_END, "");
        }

        return input;

    }
}
