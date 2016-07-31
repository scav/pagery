package com.devbugger.pagery;

public class Pagery {

    public static void main(String[] args) {
        if(args.length == 0) {
            PageryDefault pageryDefault = new PageryDefault();
            pageryDefault.generate();
            pageryDefault.server();
        }
    }
}
