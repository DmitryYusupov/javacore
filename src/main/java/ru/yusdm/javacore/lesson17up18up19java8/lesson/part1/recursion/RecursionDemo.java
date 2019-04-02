package ru.yusdm.javacore.lesson17up18up19java8.lesson.part1.recursion;

import java.io.File;

/**
 * Created by Admin on 3/29/2019.
 */
public class RecursionDemo {


    public static void main(String[] args) {
        String file = "C:\\Users\\Admin\\Desktop\\git";
        simpleListFolder(new File(file));
    }

    private static void simpleListFolder(File file) {
        System.out.println(file.getAbsolutePath());

        File[] innerFiles = file.listFiles();
        if (innerFiles != null) {
            for (File f : innerFiles) {
                simpleListFolder(f);
            }
        }
    }


}
