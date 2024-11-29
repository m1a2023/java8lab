package com.example.task01;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(
            new File("/home/mia/dev/csu/java/java8lab/task01/src/main/resources/3726.mp3"))
        );
    }
    
    public static String extractSoundName(File file) throws IOException, InterruptedException {
        
        ProcessBuilder processBuilder = 
            new ProcessBuilder(                                         
                "ffprobe", "-v", "error", "-of", "flat", "-show_format", 
                file.getAbsolutePath()                                   
            );
        
        Process process = processBuilder.start();
        
        String generalOutput = getGeneralOutput(process.getInputStream());

        return findTitle(generalOutput); 
    }

    private static String getGeneralOutput(InputStream inputStream) throws IOException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];

        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }

        return result.toString("UTF-8");
    }

    private static String findTitle(String general) {
        String find = "format.tags.title=\"";

        int startIndexOfExpression = general.indexOf(find) + find.length();
        int endIndexOfExpresiion = startIndexOfExpression;
        
        for (int i = startIndexOfExpression;
            general.charAt(i) != '"'; i++, 
            endIndexOfExpresiion = i);

        return general.substring(startIndexOfExpression, endIndexOfExpresiion);
    }
}
