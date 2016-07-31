package com.devbugger.pagery.server;

import com.devbugger.pagery.configuration.Config;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;

import java.nio.file.Paths;

import static io.undertow.Handlers.resource;

/**
 * Simple configuration of Undertow serving static content
 * defined in {@link Config}.
 */
public class Server {

    private final String fileRoot;
    private final int port;
    private final String host;

    private Undertow server;

    public Server(Config config) {
        this.fileRoot = config.getFiles().getTarget();
        this.port = Integer.parseInt(config.getServer().getPort());
        this.host = config.getServer().getHost();

        create();
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }

    private Undertow create() {
        server = Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(resource(new PathResourceManager(Paths.get(fileRoot), 100))
                        .setDirectoryListingEnabled(true)
                )
                .build();

        return server;
    }

}
