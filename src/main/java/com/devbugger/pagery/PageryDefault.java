package com.devbugger.pagery;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.configuration.PageryYAMLConfig;
import com.devbugger.pagery.configuration.defaults.DefaultConfig;
import com.devbugger.pagery.export.GeneratePages;
import com.devbugger.pagery.export.GenerateResources;
import com.devbugger.pagery.server.Server;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.Transformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Run the default Pagery process deleting, updating and creating
 * every file used by the application.
 */
public class PageryDefault {

    private Config config;

    public PageryDefault() {
        if(Files.exists(Paths.get("config.yaml"))) {
            config = new PageryYAMLConfig().read("config.yaml");
        }
        else {
            System.out.println("Config not found. Creating default.");
            System.out.println("\tNot really though.");
            //Path source = new FileUtils().getPath("config.yaml");

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("config.yaml"))) {
                writer.write(DefaultConfig.CONFIGYAML);
            } catch (IOException e) {
                e.printStackTrace();
            }
          }

    }

    public void generate() {
        System.out.println("Creating standard project \"" + config.getProject().getTitle() + "\"");
        System.out.println("Writing files to " + config.getFiles().getTarget());

        Transformer transformer = new DefaultTransformer();
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());
        transformer.setConfig(config);

        GeneratePages generatePages = new GeneratePages(config);
        GenerateResources generateResources = new GenerateResources(config);

        System.out.println("\nDone generating project files.");
    }

    public void server() {
        Server server = new Server(config);
        server.start();
    }
}
