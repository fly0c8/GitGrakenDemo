package com.zbyte;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class JavaIOEx {

    public static void run(){
        System.out.println("Running JavaIOEx");
//        try(
//                FileWriter fw = new FileWriter("myfile.txt", true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                PrintWriter out = new PrintWriter(bw);
//        ) {
//            out.println("the text");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } ;

        // new file api NIO.2
//        File inputFile = new File("myfile.txt");
//        try(InputStream in = new FileInputStream(inputFile)) {
//            Files.copy(in, Paths.get("outfile.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } ;

//        try {
//            Files.createFile(Paths.get("bull.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // copy complete directory
        // overwrite files in directory
        // if dir already exists, keep it
        try {
            Files.walk(Paths.get("sourcedir")).forEach(source -> {
                Path destination = Paths.get("destdir", source.toString().substring("sourcedir".length()));
                if(Files.isDirectory(source) && Files.exists(source)) return;
                try {
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // modern way of reading text file
        try {
            List<String> lines = Files.readAllLines(Paths.get("sourcedir/source1.txt"));
            System.out.println(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path logFile = Paths.get("/tmp/app.log");
        try {
            if(!Files.exists(logFile)) {
                Files.createFile(logFile);
            }
            Files.write(logFile, new String("content to append\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
