package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String p = reader.readLine();
            Path path = Paths.get(p);
            if (Files.isRegularFile(path)) {
                System.out.println(path.toAbsolutePath() + " - не папка");
                return;
            }

            MyFileVisitor myFileVisitor = new Solution().new MyFileVisitor();
            Files.walkFileTree(path, myFileVisitor);

            System.out.println("Всего папок - " + (myFileVisitor.getCountDirectory() - 1));
            System.out.println("Всего файлов - " + myFileVisitor.getCountFiles());
            System.out.println("Общий размер - " + myFileVisitor.getAllSize());

        } catch (IOException e) {

        }
    }

    class MyFileVisitor extends SimpleFileVisitor<Path> {
        private int countDirectory;
        private int countFiles;
        private long allSize;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (attrs.isRegularFile()) {
                allSize += attrs.size();
                countFiles++;
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (attrs.isDirectory()) {
                countDirectory++;
            }

            return FileVisitResult.CONTINUE;
        }

        public int getCountDirectory() {
            return countDirectory;
        }

        public int getCountFiles() {
            return countFiles;
        }

        public long getAllSize() {
            return allSize;
        }
    }


}
