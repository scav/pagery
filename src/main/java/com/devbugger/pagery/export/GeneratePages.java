package com.devbugger.pagery.export;

import com.devbugger.pagery.configuration.Config;
import com.devbugger.pagery.site.*;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.DefaultTransformPageryBaseBage;
import com.devbugger.pagery.transform.pagery.TransformPageryBasePage;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for generating the pages in the project directory.
 * This class runs twice,
 *
 */
public class GeneratePages {

    private final DefaultTransformer transformer;
    private Config config;

    private List<Page> pages = new ArrayList<>();
    private IndexPage indexPage;
    private PostPage postPage;
    private BasePage basePage;

    public GeneratePages(Config config) {
        this.config = config;

        transformer = new DefaultTransformer();
        transformer.setConfig(config);
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());

        generate();
    }

    /**
     * Find existing files and write the to the project folder.
     */
    private void generate() {
        List<Post> posts = new ArrayList<>();

        try (DirectoryStream<Path> postStream = Files.newDirectoryStream(Paths.get(
                config.getFiles().getRoot()+config.getFiles().getPost()), "*."+config.getFiles().getSuffix())) {
            postStream.forEach(post -> posts.add(transformer.transformPost(post.toAbsolutePath().toString())));


        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DirectoryStream<Path> pageStream = Files.newDirectoryStream(Paths.get(
                config.getFiles().getRoot()+config.getFiles().getPage()), "*."+config.getFiles().getSuffix())) {
            pageStream.forEach(post -> pages.add(transformer.transformPage(post.toAbsolutePath().toString())));


        } catch (IOException e) {
            e.printStackTrace();
        }

        indexPage = transformer.transformIndexPage(config.getFiles().getRoot()+"/index.md", posts);
        postPage = transformer.transformPostPage(config.getFiles().getRoot()+"/page/post.md", posts);
        basePage = transformer.transformBasePage(config.getFiles().getRoot()+"/template.html", pages);

        //Combine all page files
        pages.add(postPage);
        pages.addAll(posts);

        TransformPageryBasePage<BasePage, Page> base = new DefaultTransformPageryBaseBage();
        base.attach(basePage, indexPage);
        pages.forEach(p -> p = base.attach(basePage, p));

        ExportHtml exportHtml = new ExportHtml();
        pages.forEach(exportHtml::write);
        exportHtml.write(indexPage);
    }

}
