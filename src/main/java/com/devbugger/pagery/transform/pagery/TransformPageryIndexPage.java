package com.devbugger.pagery.transform.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.site.IndexPage;
import com.devbugger.pagery.site.Post;

import java.util.List;
import java.util.stream.Collectors;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.*;

public class TransformPageryIndexPage implements TransformPageryWithResources<IndexPage, Post> {

    private Config config;

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public IndexPage transform(IndexPage indexPage, List<Post> posts) {
        String input = indexPage.getContent();

        // Set up indexes for pre and post markers
        int indexPre = input.indexOf(POST_ALL)+POST_ALL.length();
        int indexPost = input.indexOf(POST_END)+POST_END.length();

        // Create copies of the content pre and post markers.
        String pre = input.substring(0, input.indexOf(POST_ALL));
        String post = input.substring(indexPost);

        StringBuilder content = new StringBuilder();
        content.append(pre);

        posts.stream().limit(config.getProject().getPostlimit()).collect(Collectors.toList()).forEach(p -> {
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

        indexPage.setContent(content.toString());

        return indexPage;
    }

}
