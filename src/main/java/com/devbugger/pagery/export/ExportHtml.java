package com.devbugger.pagery.export;


import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.PageryConfigSupport;
import com.devbugger.pagery.site.IndexPage;
import com.devbugger.pagery.site.Page;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Dummy export implementation for running unit tests.
 */
public class ExportHtml {

    private Config config;

    private final static String OPERATOR = "@";
    private final String ROOT_DIR;// = "pagery-out/"+OPERATOR+".html";

    public ExportHtml(Config config) {
        this.config = config;
        ROOT_DIR = config.getFiles().getTarget()+"/"+OPERATOR+".html";
    }

    private Path createFile(String name) {
        String target = config.getFiles().getTarget();
        try {
            // Create all files for testing purposes.
            Files.createDirectories(Paths.get(target));
            Files.createDirectories(Paths.get(target+"/resources"));
            Files.createDirectories(Paths.get(target+"/page"));
            Files.createDirectories(Paths.get(target+"/post"));
            Files.createDirectories(Paths.get(target+"/postpage"));
            Files.deleteIfExists(Paths.get(name));
            System.out.println("Name: " + name);
            System.out.println("Target: " + target);
            return Files.createFile(Paths.get(name));
        } catch (IOException e) {
            System.out.println("Target: " + target);
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
        if(page instanceof IndexPage) {
            write(page.getContent(), "index");
        }
        write(page.getContent(), page.getFontMatterMeta().getType()+"/"+page.getFontMatterMeta().getTitle());
    }

}
