package com.devbugger.pagery.server;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;

import java.nio.file.Paths;

import static io.undertow.Handlers.resource;

/**
 * Simple configuration of Undertow serving static content from the path.
 */
class Server {

    private static String root = System.getProperty("user.home")+"/dev/pagery/";

    Undertow server() {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(resource(new PathResourceManager(Paths.get(root), 100))
                        .setDirectoryListingEnabled(true)
                )
                .build();

        return server;
    }
}
