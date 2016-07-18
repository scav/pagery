package com.devbugger.pagery.server;

public class UndertowServerInitializer {

    public static void main(final String[] args) {
        new Server().server().start();
    }
}
