package com.devbugger.pagery.configuration;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PageryYAMLConfig implements PageryConfig{


    private Yaml yaml;

    public PageryYAMLConfig() {
        this.yaml = new Yaml(new Constructor(Config.class));
    }

    @Override
    public Config read(String path) {


        try(InputStream in = Files.newInputStream(Paths.get(path))) {
            return (Config) yaml.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
