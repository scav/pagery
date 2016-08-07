package com.devbugger.pagery;

public class Pagery {

    private static String SCAFFOLD = "scaffold";
    private static String SERVER = "server";

    public static void main(String[] args) {
        if(args.length == 0) {
            PageryDefault pageryDefault = new PageryDefault();
            pageryDefault.generate();
            pageryDefault.server();
        }
        else {
            for (String arg : args) {
                if(arg.equals(SCAFFOLD)) {
                    System.out.println("Starting scaffold");
                    //scaffolding code here.
                }
                else if(arg.equals(SERVER)) {
                    PageryDefault pageryDefault = new PageryDefault();
                    pageryDefault.server();
                }
            }
        }
    }
}
