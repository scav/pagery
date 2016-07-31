package com.devbugger.pagery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public Path getPath(String name) {
        return Paths.get(this.getClass().getClassLoader().getResource(name).getFile());
    }

    public String generatePath(String name) {
        return this.getClass().getClassLoader().getResource(name).getFile();
    }

    public String generate(String name) {
        return generate(Paths.get(this.getClass().getClassLoader().getResource(name).getFile()));
    }

    public String generate(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
