package com.devbugger.pagery.export;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExportHtml {

    private final String file = "index.html";

    public Path createFile(String name) {
        try {
            Files.deleteIfExists(Paths.get(name));
            return Files.createFile(Paths.get(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(String content) {
        Path path = createFile(file);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
