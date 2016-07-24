package com.devbugger.pagery.configuration;

/**
 * Give access to user defined configuration files in
 * the project directory.
 */
public interface PageryConfig {

    /**
     * Read a config file from the given path.
     * @param path to config file
     * @return application and project config
     */
    Config read(String path);

}
