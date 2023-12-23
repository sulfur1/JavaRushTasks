package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            StringBuilder builder = new StringBuilder();
            byteArrayOutputStream = new ByteArrayOutputStream();
            builder.append(getRandomInt());
            builder.append(getRandomUpperCase());
            builder.append(getRandomLowerCase());
            for (int i = 0; i < 5; i++) {
                int random = (int) (Math.random() * 3);
                switch (random) {
                    case 0:
                        builder.append(getRandomInt());
                        break;
                    case 1:
                        builder.append(getRandomUpperCase());
                        break;
                    case 2:
                        builder.append(getRandomLowerCase());
                        break;
                }
            }
            byte[] b = builder.toString().getBytes();
            byteArrayOutputStream.write(b);
        } catch (IOException e) {

        }
        return byteArrayOutputStream;
    }

    static char getRandomUpperCase() {
        Random random = new Random();
        int r = (int) (Math.random() * (91 - 65) + 65);

        return (char) r;
    }
    static char getRandomLowerCase() {
        Random random = new Random();
        int r = (int) (Math.random() * (123 - 97) + 97);

        return (char) r;
    }
    static int getRandomInt() {
        Random random = new Random();
        return (int) (Math.random() * 10);
    }
}
