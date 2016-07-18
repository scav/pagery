package com.devbugger.pagery.transform.fontmatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public FontMatterMeta create(List<String> input) {
        int start = input.indexOf(OPEN_CLOSE);
        int end = input.lastIndexOf(OPEN_CLOSE);

        Map<String, String> raw = input.subList(start+1, end).stream()
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
    public List<String> stripFontMatter(List<String> input) {
        int end = input.lastIndexOf("---");

        return input.subList(end+1, input.size());
    }
}
