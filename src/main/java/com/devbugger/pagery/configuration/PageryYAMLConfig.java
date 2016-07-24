package com.devbugger.pagery.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PageryYAMLConfig implements PageryConfig{


    private Yaml yaml;

    public PageryYAMLConfig() {
        this.yaml = new Yaml();
    }

    @Override
    public Config read(String path) {
        try(InputStream in = Files.newInputStream(Paths.get(path))) {
            return yaml.loadAs(in, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
