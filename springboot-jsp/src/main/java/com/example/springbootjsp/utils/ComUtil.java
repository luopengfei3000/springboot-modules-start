//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.springbootjsp.utils;

public class ComUtil {

    public ComUtil() {
    }

    public static String getId() {
        UUIDHexGenerator uuid = new UUIDHexGenerator();
        return uuid.generate();
    }

}
