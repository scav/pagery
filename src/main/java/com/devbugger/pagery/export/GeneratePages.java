package com.devbugger.pagery.export;

import com.devbugger.pagery.site.*;
import com.devbugger.pagery.transform.DefaultTransformer;
import com.devbugger.pagery.transform.fontmatter.TransformFontMatter;
import com.devbugger.pagery.transform.markdown.TransformMarkdown;
import com.devbugger.pagery.transform.pagery.DefaultTransformPageryBaseBage;
import com.devbugger.pagery.transform.pagery.TransformPageryBasePage;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for generating the pages in the project directory.
 *
 */
public class GeneratePages {

    private final DefaultTransformer transformer;
    private String root = "/Users/dag/dev/pagery/example/";
    private final Path postDir;
    private final Path pageDir;

    private List<Page> pages = new ArrayList<>();
    private IndexPage indexPage;
    private PostPage postPage;
    private BasePage basePage;

    public GeneratePages(String postDir, String pageDir) {
        this.pageDir = Paths.get(root+pageDir);
        this.postDir = Paths.get(root+postDir);

        transformer = new DefaultTransformer();
        transformer.setTransformFontMatter(new TransformFontMatter());
        transformer.setTransformMarkdown(new TransformMarkdown());

        //initializeData(root);

        init();

    }

    /**
     * Find existing files and write the to the project folder.
     */
    private void init() {
        List<Post> posts = new ArrayList<>();

        try (DirectoryStream<Path> postStream = Files.newDirectoryStream(postDir, "*.md")) {
            postStream.forEach(post -> {
                posts.add(transformer.transformPost(post.toAbsolutePath().toString()));
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DirectoryStream<Path> pageStream = Files.newDirectoryStream(pageDir, "*.md")) {
            pageStream.forEach(post -> {
                pages.add(transformer.transformPage(post.toAbsolutePath().toString()));
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

        indexPage = transformer.transformIndexPage("example/index.md", posts);
        postPage = transformer.transformPostPage("example/page/post.md", posts);
        basePage = transformer.transformBasePage("example/basepage.md", pages);

        //Combine all page files
        pages.add(postPage);
        pages.addAll(posts);

        TransformPageryBasePage<BasePage, List<Page>> base = new DefaultTransformPageryBaseBage();
        base.attach(basePage, indexPage);
        pages.forEach(p -> p = base.attach(basePage, p));

        ExportHtml exportHtml = new ExportHtml();
        pages.forEach(exportHtml::write);
        exportHtml.write(indexPage);
    }

    public List<Page> getPages() {
        return pages;
    }

    public PostPage getPostPage() {
        return postPage;
    }

    public BasePage getBasePage() {
        return basePage;
    }
}
