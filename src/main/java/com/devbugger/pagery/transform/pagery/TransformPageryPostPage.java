package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.site.Post;

import java.util.List;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;

public class TransformPageryPostPage implements TransformPagery<List<Post>> {

    @Override
    public String transform(String input, List<Post> posts) {
        // Set up indexes for pre and post markers
        int indexPre = input.indexOf(POST_ALL)+POST_ALL.length();
        int indexPost = input.indexOf(POST_END)+POST_END.length();

        // Create copies of the content pre and post markers.
        String pre = input.substring(0, input.indexOf(POST_ALL)-1);
        String post = input.substring(indexPost);

        StringBuilder content = new StringBuilder();
        content.append(pre);

        posts.forEach(p -> {
                // Create a copy of the content between the markers.
                String output = input.substring(indexPre, input.indexOf(POST_END));

                // Append to content for each iteration
                content.append(new TransformPageryPost().transform(output, p));
        });

        content.append(post);

        return content.toString();
    }

}