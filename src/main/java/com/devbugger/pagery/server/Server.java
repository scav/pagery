package com.devbugger.pagery.server;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;

import java.nio.file.Paths;

import static io.undertow.Handlers.resource;

/**
 * Simple configuration of Undertow serving static content from the path.
 */
class Server {

    private static String FILE_ROOT = System.getProperty("user.home")+"/dev/pagery/pagery-out/";
    private static int PORT = 8080;
    private static String HOST = "localhost";

    Undertow server() {
        Undertow server = Undertow.builder()
                .addHttpListener(PORT, HOST)
                .setHandler(resource(new PathResourceManager(Paths.get(FILE_ROOT), 100))
                        .setDirectoryListingEnabled(true)
                )
                .build();

        return server;
    }
}
