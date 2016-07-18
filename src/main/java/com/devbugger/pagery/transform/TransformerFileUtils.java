package com.devbugger.pagery.transform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple interface giving access to the default method
 * of getting access to template files.
 */
public interface TransformerFileUtils {

    /**
     * Turning a path into a list of strings where each element
     * is one line of the template.
     * @param path to the template file
     * @return a list representation of the file
     */
    default List<String> generate(String path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
