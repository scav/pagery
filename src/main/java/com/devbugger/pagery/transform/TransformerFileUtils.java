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
     * Turn a file into a string.
     * @param path to the template file
     * @return the string representing the file
     */
    default String generate(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
