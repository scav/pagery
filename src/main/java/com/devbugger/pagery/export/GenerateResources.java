package com.devbugger.pagery.export;

import com.devbugger.pagery.configuration.Config;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateResources {

    private Config config;

    public GenerateResources(Config config) {
        this.config = config;
        generate();
    }

    public void generate() {
        try (DirectoryStream<Path> postStream = Files.newDirectoryStream(Paths.get(
                config.getFiles().getRoot()+config.getFiles().getResource()))) {

            postStream.forEach(this::copy);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copy(Path source) {
        Path target = Paths.get(config.getFiles().getTarget()+config.getFiles().getResource());

        try {
            Files.deleteIfExists(target.resolve(source.getFileName()));
            Files.copy(source, target.resolve(source.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
