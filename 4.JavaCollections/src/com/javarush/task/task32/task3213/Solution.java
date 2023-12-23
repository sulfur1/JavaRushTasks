package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (reader != null) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    char[] chArray = str.toCharArray();
                    char[] temp = new char[chArray.length];

                    for (int i = 0; i < chArray.length; i++) {
                        int s = chArray[i];
                        temp[i] = (char) (s + key);
                    }
                    builder.append(temp);
                }
            }
        }
        return builder.toString();
    }
}
