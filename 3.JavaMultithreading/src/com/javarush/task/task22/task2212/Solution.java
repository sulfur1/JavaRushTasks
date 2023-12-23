package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        /*if ((telNumber.matches("\\+") || telNumber.matches("\\(")
            || telNumber.matches("\\)")) && telNumber.matches("[0-9]$"))
        {
            if (telNumber.matches("\\([0-9]{3}\\)")){
                if (telNumber.matches("[0-9]{10}")) {
                    if (telNumber.matches("^[0-9]")) {
                        return true;
                    } else if (telNumber.matches("^\\(")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else if (telNumber.matches("^\\+")) {
                if (telNumber.matches("[0-9]{12}")) {
                    return true;
                } else {
                    return false;
                }
            }
        }*/
        if (telNumber == null) {
            return false;
        }
        if (telNumber.matches("^(\\+\\d{2})?(\\(?\\d{3}\\)?)?\\d{7}$|^\\d{0,6}(\\(\\d{3}\\))\\d{1,7}$")) {
            //telNumber.matches("^((\\+\\d{2})|\\d?)?(\\(?\\d{3}\\)?)?\\d{10,12}$");
            /*if (telNumber.matches("^\\+[0-9]{2}\\(?[0-9]{3}\\)?[0-9]{7}$")) {
                return true;
            } else if (telNumber.matches("^\\([0-9]{3}\\)[0-9]{7}$")) {
                return true;
            } else if (telNumber.matches("^[0-9]\\([0-9]{3}\\)[0-9]{6}$")) {
                return true;
            } else {
                return false;
            }*/
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String regex = "^\\+?[0-9]{0,2}\\(?[0-9]{3}\\)?[0-9]{7}$";
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("(050)1234567"));
        System.out.println(checkTelNumber("0(501)234567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)123-45-67"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        /*String[] _true = {"+380501234567", "0501234567", "(050)1234567", "+(380)501234567", "+3(805)01234567", "+38(050)1234567",
                "+380(501)234567", "+3805(012)34567", "+38050(123)4567", "+380501(234)567", "+3805012(345)67", "+38050123(456)7",
                "(050)1234567", "0(501)234567", "05(012)34567", "050(123)4567", "0501(234)567", "05012(345)67", "050123(456)7",};
        String[] _false = {"+38050123456", "380501234567", "(380)501234567", "+380(50()12)34567", "+380(501)23(456)7", "38050123(456)7",
                "+380501234(567)", "+(38050123456)7", "050!234567", "+050(123)4567", "+380501(2-3)5674", ")050(1234567", "+(3)8050123456",
                "38050+123456", "+38)050(1234567", "+38(050)123-45-67", "050ххх4567", "050123456", "(0)501234567", "5", "+(050)", "+()()()()()()", ""};
        for(int i = 0; i < 2; i++) {
            System.out.println("\nВсё, что ниже, должно быть " + (i == 0));
            for (String str : _true) {
                System.out.println(str + "                  = ".substring(str.length(), 20) + checkTelNumber(str) + (checkTelNumber(str) == (i == 0) ? "" : "   X"));
            }
            _true = _false;
        }
        System.out.println("null" + "              = " + checkTelNumber(null));*/
    }
}
