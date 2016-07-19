package com.devbugger.pagery.markdown;

import com.devbugger.pagery.transform.TransformerFileUtils;
import com.devbugger.pagery.transform.markdown.CommonMark;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class CommonMarkTest {

    // simple framework test.
    @Test
    public void commonMark() throws Exception {
        String expected = "<h1>hello</h1>\n";
        CommonMark commonMark = new CommonMark("# hello");

        assertThat("Should be " + expected, commonMark.get(), is(expected));
    }

    @Test
    public void test() throws Exception {

        String inputS = generate("example/page/markdown.md");

        CommonMark commonMark = new CommonMark(inputS);

        System.out.println(commonMark.get());
    }


    String generate(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
