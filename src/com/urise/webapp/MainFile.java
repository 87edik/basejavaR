package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        File file = new File("./.gitignore");
        File dir = new File("./src/com/urise/webapp");
        try{
            System.out.println(dir.getCanonicalPath());
            System.out.println(dir.isDirectory());
            for (var l : dir.list()){
                System.out.println(l);
            }
        } catch (Exception e){

        }


        try(FileInputStream fis = new FileInputStream(file)) {

            fis.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printDirectoryDeeply(dir,0);

    }

    public static void printDirectoryDeeply(File dir, int offset){
        File[] files = dir.listFiles();
        if(files==null){
            return;
        }
        for (var file : files){
            for (int i=0; i<=offset; i++){
                System.out.print(' ');
            }

            System.out.println(file.getName());
            if (file.isDirectory()) {

                printDirectoryDeeply(file, offset+1);
            }
        }
    }
}
