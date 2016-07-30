package com.devbugger.pagery.transform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Simple interface giving access to the default method
 * of getting access to template files.
 */
public interface TransformerFileUtils {

    /**
     * Turn a location presented by a string into a string based
     * representation of the file.
     * @param path location of file
     * @return the string representing the file
     */
    default String generate(String path) {
        return generate(Paths.get(path));
    }

    /**
     * Turns a location presented as a {@link Path} into a string based
     * representation of the file.
     * @param path path presentation of file
     * @return the string representing the file
     */
    default String generate(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
