package com.myproject.util;

public class CheckCodeGenerator {

    public static String generate() {
        String toReturn = String.valueOf((int) (Math.random() * 10000));
        if (toReturn.length() != 4) {
            toReturn = generate();
        }
        return toReturn;
    }
}
