package com.devbugger.pagery.transform.fontmatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the set of meta data that can be extracted
 * from the data between the font matters.
 */
public class FontMatterMeta {

    private String title;
    private String author;
    private LocalDateTime dateTime;
    private String type;
    private List<String> categories = new ArrayList<>();

    public FontMatterMeta() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "FontMatterMeta{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dateTime=" + dateTime +
                ", type='" + type + '\'' +
                ", categories=" + categories +
                '}';
    }
}
