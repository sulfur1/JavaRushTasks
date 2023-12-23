package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        File target = new File(args[0]);
        File oldArchive = new File(args[1]);
        Map<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(oldArchive));
            FileInputStream fileInputStream = new FileInputStream(target)) {
            ByteArrayOutputStream byteOutputStream;
            while (true) {
                ZipEntry zipEntry = zipInputStream.getNextEntry();
                if (zipEntry == null) break;

                byte[] buffer = zipInputStream.readAllBytes();
                byteOutputStream = new ByteArrayOutputStream();
                byteOutputStream.write(buffer);
                byteOutputStream.flush();
                map.put(zipEntry, byteOutputStream);
                zipInputStream.closeEntry();
            }
            String fileName = "new/" + target.getName();

            ZipEntry zipEntry = new ZipEntry(fileName);

            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            byteOutputStream = new ByteArrayOutputStream();
            byteOutputStream.write(buffer);
            byteOutputStream.flush();
            map.put(zipEntry, byteOutputStream);
            zipInputStream.closeEntry();
        }



        boolean bol = oldArchive.delete();
        File newArchive = null;
        if (bol) {
            newArchive = new File(args[1]);
            newArchive.createNewFile();
        } else {
            throw new FileNotFoundException();
        }


        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(newArchive))) {
            for (Map.Entry<ZipEntry, ByteArrayOutputStream> entry : map.entrySet()) {
                ZipEntry zipEntry = entry.getKey();
                ByteArrayOutputStream byteArrayOutputStream = entry.getValue();

                zipOutputStream.putNextEntry(zipEntry);
                byteArrayOutputStream.writeTo(zipOutputStream);
                byteArrayOutputStream.flush();
                zipOutputStream.closeEntry();
            }
        }


    }
}
