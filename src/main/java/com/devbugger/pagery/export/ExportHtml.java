package com.devbugger.pagery.export;


import com.devbugger.pagery.site.Page;
import com.devbugger.pagery.site.PostPage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Dummy export implementation for running unit tests.
 */
public class ExportHtml {

    private final static String OPERATOR = "@";
    private final static String ROOT_DIR = "pagery-out/"+OPERATOR+".html";

    private Path createFile(String name) {
        try {
            // Create all files for testing purposes.
            Files.createDirectories(Paths.get("pagery-out"));
            Files.createDirectories(Paths.get("pagery-out/page"));
            Files.createDirectories(Paths.get("pagery-out/post"));
            Files.createDirectories(Paths.get("pagery-out/postpage"));
            Files.deleteIfExists(Paths.get(name));
            return Files.createFile(Paths.get(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(String content, String filePath) {
        String pathS = ROOT_DIR.replace(OPERATOR, filePath);
        Path path = createFile(pathS);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(Page page) {
        if(page instanceof PostPage) {
            write(page.getContent(), "index");
        }
        write(page.getContent(), page.getFontMatterMeta().getType()+"/"+page.getFontMatterMeta().getTitle());
    }



}
