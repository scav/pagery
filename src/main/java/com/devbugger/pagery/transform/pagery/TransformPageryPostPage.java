package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.site.Post;
import com.devbugger.pagery.site.PostPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;
import static com.devbugger.pagery.transform.pagery.TransformPageryPost.CATEGORY_SEPARATOR;

public class TransformPageryPostPage implements TransformPageryWithResources<PostPage, Post> {

    private Config config;

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

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

}