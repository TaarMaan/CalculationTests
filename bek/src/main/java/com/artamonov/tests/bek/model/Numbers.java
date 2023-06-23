package com.artamonov.tests.bek.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Numbers {
    private int num1;
    private int num2;
    private int system;


    public static int toBinary(int num) {
        return Integer.parseInt(String.valueOf(num), 2);
    }

    public static int toOctalString(int num) {
        return Integer.parseInt(String.valueOf(num), 8);
    }

    public static int toHexString(int num) {
        return Integer.parseInt(String.valueOf(num), 16);
    }
}