package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;
import static com.devbugger.pagery.transform.pagery.TransformPageryPost.CATEGORY_SEPARATOR;

public class DefaultTransformPageryPostPage implements TransformPageryPostPage<PostPage, List<Post>> {

    @Override
    public PostPage transform(PostPage postPage, List<Post> posts) {
        String input = postPage.getContent();

        // Set up indexes for pre and post markers
        int indexPre = input.indexOf(POST_ALL)+POST_ALL.length();
        int indexPost = input.indexOf(POST_END)+POST_END.length();

        // Create copies of the content pre and post markers.
        String pre = input.substring(0, input.indexOf(POST_ALL));
        String post = input.substring(indexPost);

        StringBuilder content = new StringBuilder();
        content.append(pre);

        posts.forEach(p -> {
            // Create a copy of the content between the markers.
            String output = input.substring(indexPre, input.indexOf(POST_END));

            // Append to content for each iteration
            if(output.contains(POST_LEAD_PARAGRAPH)) {
                output = output.replace(POST_LEAD_PARAGRAPH,
                        p.getLeadPargraph());
            }

            output = transform(output, p);

            content.append(output);

        });

        postPage.setContent(content.toString());

        return postPage;
    }

    public String transform(String input, Post post) {
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