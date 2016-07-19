package com.devbugger.pagery.transform.markdown;

import com.devbugger.pagery.transform.TransformerFileUtils;

import java.util.List;

import static com.devbugger.pagery.transform.markdown.Markdown.*;

public class TransformMarkdown implements TransformerFileUtils {

    private boolean openTag = false;
    public String transform(List<String> input) {
        StringBuilder output = new StringBuilder();

        input.forEach(line -> {
            if(line.startsWith(H1)) {
                output.append("<h1>").append(stripMarkdown(line, H1)).append("</h1>").append("\n");
            }
            else if(line.startsWith(H2)) {
                output.append("<h2>").append(stripMarkdown(line, H2)).append("</h2>").append("\n");
            }
            else if(line.startsWith(H3)) {
                output.append("<h3>").append(stripMarkdown(line, H3)).append("</h3>").append("\n");
            }
            else if(line.startsWith(H4)) {
                output.append("<h4>").append(stripMarkdown(line, H4)).append("</h4>").append("\n");
            }
            else if(line.startsWith(H5)) {
                output.append("<h5>").append(stripMarkdown(line, H5)).append("</h5>").append("\n");
            }
            else if(line.startsWith(H6)) {
                output.append("<h6>").append(stripMarkdown(line, H6)).append("</h6>").append("\n");
            }
            else if(line.startsWith(NEW_LINE)) {
                output.append("<br />").append("\n");
            }
            else if(line.startsWith(CODE)) {
                if(openTag) {
                    output.append(stripMarkdown(line, CODE)).append("</code>").append("\n");
                    openTag = false; //close at the end of tag
                }
                else {
                    output.append("<code>").append(stripMarkdown(line, CODE)).append("\n");
                    openTag = true; //opens the tag
                }

            }
            else if(line.startsWith(BLOCK_QUOTE)) {
                if(openTag) {
                    output.append(stripMarkdown(line, BLOCK_QUOTE)).append("</blockquote>").append("\n");
                    //openTag = false; //close at the end of tag
                }
                else {
                    output.append("<blockquote>").append(stripMarkdown(line, BLOCK_QUOTE)).append("\n");
                    openTag = true; //opens the tag
                }
            }
            else { // Defaults back to adding new lines to elements that are not "parsed".
                output.append(line).append("\n");
            }


        });

        return output.toString();
    }

    String stripMarkdown(String input, String tag) {
        return input.replace(tag, "").trim();
    }
}
