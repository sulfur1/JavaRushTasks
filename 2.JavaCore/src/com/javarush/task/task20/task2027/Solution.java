package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/*
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        List<Word> words = detectAllWords(crossword, "home", "same", "leor");
        words.forEach(System.out::println);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */


    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        List<Word> temp;
        for (String s : words) {
            temp = arrayVertical(crossword, s);
            if (!temp.isEmpty()) {
                result.addAll(temp);
            }
            temp = arrayHorizontal(crossword, s);
            if (!temp.isEmpty()) {
                result.addAll(temp);
            }
            temp = arrayDiagonal(crossword, s);
            if (!temp.isEmpty()) {
                result.addAll(temp);
            }
        }
        return result;
    }

    public static List<Word> arrayVertical(int[][] array, String str) {
        List<Word> arrays = new ArrayList<>();
        //ArrayList<Integer> integers;
        StringBuilder builder;
        Word word;
        ArrayList<Point> points;
        Point point;
        //Слудубщие два цикла извлекают все правонаправленные диагонали массива и ищут искомое слово
        int indexCharAtStart;
        int indexCharAtEnd;
        for (int i = 0; i < array[0].length; i++) {
            indexCharAtStart = 0;
            indexCharAtEnd = str.length() - 1;
            builder = new StringBuilder();
            points = new ArrayList<>();
            for (int j = 0; j < array.length; j++) {
                //integers.add(array[i][j]);
                if (builder.length() < str.length() && ((char) array[j][i] == str.charAt(indexCharAtStart) || (char) array[j][i] == str.charAt(indexCharAtEnd))) {
                    builder.append((char) array[j][i]);
                    point = new Point();
                    point.setX(i);
                    point.setY(j);
                    points.add(point);
                    indexCharAtStart++;
                    indexCharAtEnd--;
                }
            }
            if (builder.toString().equals(str)) {
                Point startPoint = points.get(0);
                Point endPoint = points.get(points.size() - 1);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            } else if (builder.reverse().toString().equals(str)) {
                Point startPoint = points.get(points.size() - 1);
                Point endPoint = points.get(0);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            }
            //arrays.add(integers);
            builder.delete(0, builder.length());
        }
        return arrays;
    }

    public static List<Word> arrayHorizontal(int[][] array, String str) {
        List<Word> arrays = new ArrayList<>();
        //ArrayList<Integer> integers;
        StringBuilder builder;
        Word word;
        ArrayList<Point> points;
        Point point;
        //Слуду.щие два цикла извлекают все правонаправленные диагонали массива и ищут искомое слово
        int indexCharAtStart;
        int indexCharAtEnd;
        for (int i = 0; i < array.length; i++) {
            indexCharAtStart = 0;
            indexCharAtEnd = str.length() - 1;
            builder = new StringBuilder();
            points = new ArrayList<>();
            for (int j = 0; j < array[0].length; j++) {
                //integers.add(array[i][j]);
                if (builder.length() < str.length() && ((char) array[i][j] == str.charAt(indexCharAtStart) || (char) array[i][j] == str.charAt(indexCharAtEnd))) {
                    builder.append((char) array[i][j]);
                    point = new Point();
                    point.setX(j);
                    point.setY(i);
                    points.add(point);
                    indexCharAtStart++;
                    indexCharAtEnd--;
                }
            }
            if (builder.toString().equals(str)) {
                Point startPoint = points.get(0);
                Point endPoint = points.get(points.size() - 1);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            } else if (builder.reverse().toString().equals(str)) {
                Point startPoint = points.get(points.size() - 1);
                Point endPoint = points.get(0);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            }
            //arrays.add(integers);
            builder.delete(0, builder.length());
        }
        return arrays;
    }
    public static List<Word> arrayDiagonal(int[][] array, String str) {

        List<Word> arrays = new ArrayList<>();

        int j;
        int i;
        //ArrayList<Integer> integers;
        StringBuilder builder;
        Word word;
        ArrayList<Point> points;
        Point point;
        //Слудубщие два цикла извлекают все правонаправленные диагонали массива и ищут искомое слово
        int lengthRow = array[0].length - 1;
        int lengthColumn = array.length - 1;
        int indexCharAtStart;
        int indexCharAtEnd;
        for (int k = 0; k < lengthRow; k++) {
            j = lengthRow;
            j = j - k;
            i = 0;
            indexCharAtStart = 0;
            indexCharAtEnd = str.length() - 1;
            //integers = new ArrayList<>();
            builder = new StringBuilder();
            points = new ArrayList<>();

            for (; j <= lengthRow && i < lengthRow; j++, i++) {
                //integers.add(array[i][j]);
                if (builder.length() < str.length() && ((char) array[i][j] == str.charAt(indexCharAtStart) || (char) array[i][j] == str.charAt(indexCharAtEnd))) {
                    builder.append((char) array[i][j]);
                    point = new Point();
                    point.setX(j);
                    point.setY(i);
                    points.add(point);
                    indexCharAtStart++;
                    indexCharAtEnd--;
                }
            }
            if (builder.toString().equals(str)) {
                Point startPoint = points.get(0);
                Point endPoint = points.get(points.size() - 1);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            } else if (builder.reverse().toString().equals(str)) {
                Point startPoint = points.get(points.size() - 1);
                Point endPoint = points.get(0);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            }
            //arrays.add(integers);
            builder.delete(0, builder.length());
        }

        for (int k = lengthColumn; k >= 0; k--) {
            i = 0;
            i = i + k;
            j = 0;
            //integers = new ArrayList<>();
            indexCharAtStart = 0;
            indexCharAtEnd = str.length() - 1;
            builder = new StringBuilder();
            points = new ArrayList<>();
            for (; j < lengthRow && i < lengthRow; j++, i++) {
                //integers.add(array[i][j]);
                if (((char) array[i][j] == str.charAt(indexCharAtStart) || (char) array[i][j] == str.charAt(indexCharAtEnd)) && builder.length() < str.length()) {
                    builder.append((char) array[i][j]);
                    point = new Point();
                    point.setX(j);
                    point.setY(i);
                    points.add(point);
                    indexCharAtStart++;
                    indexCharAtEnd--;
                }
            }
            if (builder.toString().equals(str)) {
                Point startPoint = points.get(0);
                Point endPoint = points.get(points.size() - 1);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            } else if (builder.reverse().toString().equals(str)) {
                Point startPoint = points.get(points.size() - 1);
                Point endPoint = points.get(0);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            }
            //arrays.add(integers);
            builder.delete(0, builder.length());
        }

        //Слудубщие два цикла извлекают все левонаправленные диагонали массива и ищут искомое слово
        for (int k = 0; k < lengthRow; k++) {
            j = lengthRow;
            j = j - k;
            i = 4;
            indexCharAtStart = 0;
            indexCharAtEnd = str.length() - 1;
            //integers = new ArrayList<>();
            builder = new StringBuilder();
            points = new ArrayList<>();

            for (; j <= lengthRow && i >= 0; j++, i--) {
                //integers.add(array[i][j]);
                if (((char) array[i][j] == str.charAt(indexCharAtStart) || (char) array[i][j] == str.charAt(indexCharAtEnd)) && builder.length() < str.length()) {
                    builder.append((char) array[i][j]);
                    point = new Point();
                    point.setX(j);
                    point.setY(i);
                    points.add(point);
                    indexCharAtStart++;
                    indexCharAtEnd--;
                }
            }
            if (builder.toString().equals(str)) {
                Point startPoint = points.get(0);
                Point endPoint = points.get(points.size() - 1);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            } else if (builder.reverse().toString().equals(str)) {
                Point startPoint = points.get(points.size() - 1);
                Point endPoint = points.get(0);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            }
            //arrays.add(integers);
            builder.delete(0, builder.length());
        }

        for (int k = lengthColumn; k >= 0; k--) {
            i = 0;
            i = i + k;
            j = 0;
            //integers = new ArrayList<>();
            indexCharAtStart = 0;
            indexCharAtEnd = str.length() - 1;
            builder = new StringBuilder();
            points = new ArrayList<>();
            for (; j < lengthRow && i >= 0; j++, i--) {
                //integers.add(array[i][j]);
                if (((char) array[i][j] == str.charAt(indexCharAtStart) || (char) array[i][j] == str.charAt(indexCharAtEnd)) && builder.length() < str.length()) {
                    builder.append((char) array[i][j]);
                    point = new Point();
                    point.setX(j);
                    point.setY(i);
                    points.add(point);
                    indexCharAtStart++;
                    indexCharAtEnd--;
                }
            }
            if (builder.toString().equals(str)) {
                Point startPoint = points.get(0);
                Point endPoint = points.get(points.size() - 1);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            } else if (builder.reverse().toString().equals(str)) {
                Point startPoint = points.get(points.size() - 1);
                Point endPoint = points.get(0);
                word = new Word(str);
                word.setStartPoint(startPoint.getX(), startPoint.getY());
                word.setEndPoint(endPoint.getX(), endPoint.getY());
                arrays.add(word);
            }
            //arrays.add(integers);
            builder.delete(0, builder.length());
        }
        return arrays;
    }

    public static class Word {
        private final String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
