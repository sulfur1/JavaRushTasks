package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
Составить цепочку слов
*/
// D:\MyProject\Solution\src\main\resources\test1.txt
public class Solution {
    public static void main(String[] args) {
        //...
        String[] strings = null;
        Scanner scanner = new Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader(scanner.nextLine()))) {
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append(reader.readLine()).append(" ");
            }
            strings = builder.toString().trim().split(" ");

        } catch (IOException e) {

        }
        StringBuilder result = getLine(strings);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder builder = new StringBuilder();
        if (words.length > 0) {
            ArrayList<Word> listWords = new ArrayList<>();
            for (String s : words) {
                listWords.add(new Word(s));
            }
            //listWords.stream().forEach(System.out::println);
            //ArrayList<Word> copyListWords;
            for (int i = 0; i < listWords.size(); i++) {
                Word current = listWords.get(i);
                char first = current.getFirstChar();
                char last = current.getLastChar();
                Word temp;
                if (current.getNext() == null) {
                    h1: for (int j = 0; j < listWords.size(); j++) {
                        temp = listWords.get(j);

                        if (j == i || temp.getPrev() != null) {
                            continue h1;
                        }
                        if (isNext(listWords, temp, j)) {
                            if (last == temp.getFirstChar()) {
                                current.setNext(temp);
                                if (temp.getPrev() == null) {
                                    temp.setPrev(current);
                                }
                                break h1;
                            }
                        } else {
                            continue h1;
                        }
                    }
                }
                if (current.getPrev() == null) {
                    h1: for (int j = 0; j < listWords.size(); j++) {
                        temp = listWords.get(j);
                        if (j == i || temp.getNext() != null) {
                            continue h1;
                        }
                        if (isPrev(listWords, temp, j)) {
                            if (first == temp.getLastChar()) {
                                current.setPrev(temp);
                                if (temp.getNext() == null) {
                                    temp.setNext(current);
                                }
                                break h1;
                            }
                        } else {
                            continue h1;
                        }
                    }
                }
            }
            ArrayList<Word> result = new ArrayList<>();
            /*Word temp;
            for (int i = 0; i < listWords.size(); i++) {
                *//*Word next;
                Word prev;
                if (word.getNext() != null && word.getPrev() != null && word.next.getNext() != null && word.prev.getPrev() != null) {
                    result.add(word);
                } else if (word.getNext() == null && word.getPrev() != null && word.next.getNext() != null && word.prev.getPrev() != null) {
                    result.add(listWords.size() - 1, word);
                } else if (word.getNext() != null && word.getPrev() == null && word.next.getNext() != null && word.prev.getPrev() != null) {
                    result.add(0, word);
                }*//*
                temp = listWords.get(i);
                if (temp.getPrev() == null) {
                    listWords.add(0, temp);
                    listWords.remove(i);
                }
            }*/

            builder = new StringBuilder();
            Word word = listWords.get(0);
            for (int i = 0; i < listWords.size() - 1; i++) {
                if (i == 0) {
                    builder.append(word.getWord()).append(" ");
                } else {
                    word = word.getNext();
                    builder.append(word.getWord()).append(" ");
                }
            }
            word = word.getNext();
            builder.append(word.getWord());
            //System.out.println(builder.toString());
        }

        return builder;
    }

    public static boolean isNext(List<Word> words, Word current, int index) {
        int count = 0;
        Word word;
        for (int i = 0; i < words.size(); i++) {
            if (i == index) {
                continue;
            }
            word = words.get(i);
            if (current.getLastChar() == word.getFirstChar()) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPrev(List<Word> words, Word current, int index) {
        int count = 0;
        Word word;
        for (int i = 0; i < words.size(); i++) {
            if (i == index) {
                continue;
            }
            word = words.get(i);
            if (current.getFirstChar() == word.getLastChar()) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    static class Word {
        private char firstChar;
        private char lastChar;
        private String word;
        private Word prev;
        private Word next;

        public Word(String word) {
            this.firstChar = word.toLowerCase().charAt(0);
            this.lastChar = word.charAt(word.length() - 1);
            this.word = word;
        }

        public char getFirstChar() {
            return firstChar;
        }

        public char getLastChar() {
            return lastChar;
        }

        public String getWord() {
            return word;
        }

        public void setPrev(Word prev) {
            this.prev = prev;
        }

        public void setNext(Word next) {
            this.next = next;
        }

        public Word getPrev() {
            return prev;
        }

        public Word getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Word{" +
                    "firstChar=" + firstChar +
                    ", lastChar=" + lastChar +
                    ", word='" + word + '\'' +
                    '}';
        }
    }
}
