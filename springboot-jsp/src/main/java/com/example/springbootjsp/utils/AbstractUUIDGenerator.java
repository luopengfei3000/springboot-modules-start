//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.springbootjsp.utils;

import java.net.InetAddress;

public abstract class AbstractUUIDGenerator {
    private static final int IP;
    private static short counter;
    private static final int JVM;

    public AbstractUUIDGenerator() {
    }

    protected int getJVM() {
        return JVM;
    }

    protected short getCount() {
        Class var1 = AbstractUUIDGenerator.class;
        synchronized(AbstractUUIDGenerator.class) {
            if (counter < 0) {
                counter = 0;
            }

            return counter++;
        }
    }

    protected int getIP() {
        return IP;
    }

    protected short getHiTime() {
        return (short)((int)(System.currentTimeMillis() >>> 32));
    }

    protected int getLoTime() {
        return (int)System.currentTimeMillis();
    }

    static {
        int ipadd;
        try {
            ipadd = BytesHelper.toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception var2) {
            ipadd = 0;
        }

        IP = ipadd;
        counter = 0;
        JVM = (int)(System.currentTimeMillis() >>> 8);
    }
}
