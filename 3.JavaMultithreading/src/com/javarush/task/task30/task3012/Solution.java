package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1234);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        int[] ar = new int[]{1, 3, 9, 27, 81, 243, 729, 2187};
        char[] chars = balancedTernary(number).toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                continue;
            } else if (chars[i] == '1') {
                builder.append(" + ").append(ar[i]);
            } else {
                builder.append(" - ").append(ar[i]);
            }
        }
        System.out.println(number + " = " + builder.toString().trim());
    }

    static String balancedTernary(int n)
    {
        StringBuilder output = new StringBuilder();
            while (n > 0) {
                int rem = n % 3;
                n = n / 3;
                if (rem == 2) {
                    rem = -1;
                    n++;
                }

                if (rem == 0) {
                    output.append("0");
                } else if (rem == 1) {
                    output.append("1");
                } else {
                    output.append("Z");
                }
            }
            return output.toString();

    }
}