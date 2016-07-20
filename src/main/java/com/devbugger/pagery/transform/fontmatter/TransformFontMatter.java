package com.devbugger.pagery.transform.fontmatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.devbugger.pagery.transform.fontmatter.FontMatter.*;
import static java.util.stream.Collectors.toMap;

public class TransformFontMatter {

    /**
     * Process and populate metadata containing the fontMatter
     * data used when gluing together all the components for a build.
     * @param input the entire text string to process
     * @return the populated metadata
     */
    public FontMatterMeta create(String input) {
        int start = input.indexOf(OPEN_CLOSE)+OPEN_CLOSE.length();
        int end = openCloseEndIndex(input);

        Map<String, String> raw = Arrays.stream(input.substring(start, end).split("\n"))
                .filter(s -> !s.isEmpty()) // remove possible empty elements.
                .collect(toMap(
                        key -> key.substring(0, key.indexOf(":")).trim(),
                        val -> val.substring(val.indexOf(":")+1).trim()
                ));

        FontMatterMeta fontMatterMeta = new FontMatterMeta();
        fontMatterMeta.setTitle(raw.get(TITLE) != null ? raw.get(TITLE) : "");
        fontMatterMeta.setAuthor(raw.get(AUTHOR) != null ? raw.get(AUTHOR): "");
        if(raw.get(DATE) != null) {
            fontMatterMeta.setDateTime(LocalDateTime.parse(raw.get(DATE), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        }
        fontMatterMeta.setType(raw.get(TYPE) != null ? raw.get(TYPE) : "");
        fontMatterMeta.setCategories(Arrays.asList((raw.get(CATEGORIES) != null ? raw.get(CATEGORIES) : "").split(CATEGORIES_SEPARATOR)));

        return fontMatterMeta;
    }

    /**
     * Strip the --- content --- based font matter headers from
     * the input string by splitting the line after the first ---.
     * @param input a list with headers
     * @return a new list without headers
     */
    public String stripFontMatter(String input) {
        String endLine = "---";

        if(input.startsWith(endLine)) {
            return input.substring(
                    openCloseEndIndex(input)+OPEN_CLOSE.length(),
                    input.length());
        }

        return input;
    }

    int openCloseEndIndex(String input) {
        int index = input.indexOf(OPEN_CLOSE);
        int counter = 0;
        while (index >= 0) {
            counter++;
            if(counter == 2) {
                break;
            }
            index = input.indexOf(OPEN_CLOSE, index +1);
        }

        return index;
    }
}
