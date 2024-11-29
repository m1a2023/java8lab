package com.example.task02;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    
    private static final List<Path> list = new ArrayList<Path>();
    public static void main(String[] args) throws IOException, InterruptedException {
        listFiles(Paths.get("task02/src/main/resources/")).forEach(System.out::println);
    }

    public static List<Path> listFiles(Path rootDir) throws IOException {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(rootDir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    listFiles(entry); 
                } else {
                    list.add(entry);
                }
            }
        }

        return list;
    }
}