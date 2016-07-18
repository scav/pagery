package com.devbugger.pagery.transform.markdown;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.devbugger.pagery.transform.markdown.Markdown.*;

@RunWith(JUnit4.class)
public class TransformMarkdownTest {

    @Test
    public void stripMarkdown() throws Exception {
        TransformMarkdown transformer = new TransformMarkdown();
        String resultH1 = transformer.stripMarkdown("# HEADER ONE", H1);
        String resultH2 = transformer.stripMarkdown("## HEADER TWO", H2);
        String resultH3 = transformer.stripMarkdown("### HEADER THREE", H3);
        String resultH4 = transformer.stripMarkdown("#### HEADER FOUR", H4);
        String resultH5 = transformer.stripMarkdown("##### HEADER FIVE", H5);
        String resultH6 = transformer.stripMarkdown("###### HEADER SIX", H6);

        String resultBlockQuote = transformer.stripMarkdown("> Quoted text", BLOCK_QUOTE);
        String resultList = transformer.stripMarkdown("* Item", LIST);
        String resultCode = transformer.stripMarkdown("```\n if(a = b)\n```", CODE);

        //TODO: write asserts here
    }
}
