package com.devbugger.pagery.transform.fontmatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        int end = input.lastIndexOf(OPEN_CLOSE);

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
     * the list before processing it further.
     * @param input a list with headers
     * @return a new list without headers
     */
    public String stripFontMatter(String input) {
        int end = input.lastIndexOf("---");

        return input.substring(end+1, input.length());
    }
}
