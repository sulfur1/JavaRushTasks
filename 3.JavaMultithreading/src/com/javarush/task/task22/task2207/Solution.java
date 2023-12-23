package com.javarush.task.task22.task2207;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/* 
Обращенные слова
*/
//D:\MyProject\Solution\src\main\resources\test1.txt
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader(scanner.nextLine()))) {

            StringBuilder builder = new StringBuilder();

            while (reader.ready()) {
                builder.append(reader.readLine().trim()).append(" ");
            }
            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(builder.toString().split(" ")));
            List<String> copyList = new ArrayList<>(list);
            //list.stream().forEach(System.out::println);
            String first = null;
            String second = null;
            int count;
            for (int i = 0; i < list.size() - 1; i++) {
                first = list.get(i);
                count = 1;
                String tmp;
                for (int j = i + 1; j < list.size(); j++) {
                    tmp = copyList.get(j);
                    tmp = new StringBuilder(tmp).reverse().toString();
                    if (first.equals(tmp)) {
                        second = copyList.get(j);
                        count++;
                    }
                }
                if (count % 2 == 0) {
                    Pair pair = new Pair();
                    pair.first = first;
                    pair.second = second;
                    result.add(pair);
                }
            }


        } catch (IOException e) {

        }

        for (Pair p : result) {
            System.out.println(p);
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
