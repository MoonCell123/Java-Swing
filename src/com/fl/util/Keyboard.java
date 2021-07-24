package com.fl.util;

import java.util.Scanner;

public class Keyboard {
    private static final Scanner i = new Scanner(System.in);

    public static int getInt(String message) {
        int r = 0;
        while (true) {
            try {
                System.out.println(message);
                String s = i.next();
                r = Integer.parseInt(s);
                break;
            } catch (Exception e) {
                System.out.println("�����������������");
            }
        }
        return r;
    }

    public static String getString(String message) {
        System.out.println(message);
        return i.next();
    }
}
