package com.devbugger.pagery.configuration;

/**
 * Attach a {@link Config} instance.
 */
public interface PageryConfigSupport {

    /**
     * In cases where a class needs access to the {@link Config} object,
     * this method is useful to provide said access.
     * @param config current config object
     */
    void setConfig(Config config);
}
