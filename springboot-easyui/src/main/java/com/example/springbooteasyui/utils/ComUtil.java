//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.springbooteasyui.utils;

public class ComUtil {

    public ComUtil() {
    }

    public static String getId() {
        UUIDHexGenerator uuid = new UUIDHexGenerator();
        return uuid.generate();
    }

}
