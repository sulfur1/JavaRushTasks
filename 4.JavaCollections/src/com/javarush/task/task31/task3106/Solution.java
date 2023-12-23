package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        File result = new File(args[0]);    //Файл результата, по совместительству имя этого файла мы ищем в архиве
        if (!result.exists()) {
            result.createNewFile();
        }
        
        List<String> fileNames = new ArrayList<>();
        fileNames.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(fileNames);

        List<FileInputStream> fileInputStreams = new ArrayList<>();

        for (String s : fileNames) {
            fileInputStreams.add(new FileInputStream(s));
        }

        try (InputStream in = new SequenceInputStream(Collections.enumeration(fileInputStreams));
            ZipInputStream zipInputStream = new ZipInputStream(in, Charset.forName("windows-1251"))) {

            while (true) {
                ZipEntry zipEntry = zipInputStream.getNextEntry();

                if (zipEntry == null) break;

                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(result))) {
                    final int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    for (int readBytes; (readBytes = zipInputStream.read(buffer, 0, bufferSize)) > -1; ) {
                        bufferedOutputStream.write(buffer, 0, readBytes);
                    }
                    bufferedOutputStream.flush();
                }
            }

        }
    }
}
