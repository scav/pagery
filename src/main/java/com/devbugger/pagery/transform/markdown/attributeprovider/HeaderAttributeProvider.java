package com.devbugger.pagery.transform.markdown.attributeprovider;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.Template;
import org.commonmark.html.AttributeProvider;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.devbugger.pagery.transform.pagery.PageryMarkers.POST_TITLE;

/**
 * Looks for {@link com.devbugger.pagery.transform.pagery.PageryMarkers} that match
 * a post title, adding the CSS class for headers.
 */
public class HeaderAttributeProvider implements AttributeProvider {

    private Config config;

    public HeaderAttributeProvider(Config config) {
        this.config = config;
    }

    @Override
    public void setAttributes(Node node, Map<String, String> attributes) {
        if(node.getFirstChild() instanceof Text) {
            if((((Text) node.getFirstChild()).getLiteral().equals(POST_TITLE))) {
                Template template = config.getTemplates().get("post.title");
                for (int i = 0; i < template.getValues().length; i++) {
                    attributes.put(template.getAttribute(), valuesToString(template.getValues()));
                }
            }
        }
    }

    /**
     * Turn the array of values into a string.
     * [val1, val2, val3] -> "val1 val2 val3"
     * @param values class/id
     * @return the complete string
     */
    private String valuesToString(String[] values) {
        return Arrays.stream(values)
                .map(p -> p)
                .collect(Collectors.joining(" "));
    }
}
