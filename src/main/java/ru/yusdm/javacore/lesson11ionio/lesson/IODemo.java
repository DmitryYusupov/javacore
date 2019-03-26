package ru.yusdm.javacore.lesson11ionio.lesson;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 3/13/2019.
 */
public class IODemo implements IO {

    public static final String FILE_PATH = "C:\\Users\\Admin\\Desktop\\demoio\\44.txt";
    public static final String FILE_PATH_ZIP_SRC = "C:\\Users\\Admin\\Desktop\\demoio\\zipFile.zip";
    public static final String FILE_PATH_ZIP_DEST = "C:\\Users\\Admin\\Desktop\\demoio\\zipFileCopy.zip";
    public static final String FILE_TO_WRITE_PATH = "C:\\Users\\Admin\\Desktop\\demoio\\to_write.txt";
    public static final String DIR_PATH = "C:\\Users\\Admin\\Desktop\\demoio\\";

    @Override
    public void demoFile() {
        File file = new File(FILE_PATH);
        System.out.println("File exists " + file.exists());
        if (file.exists() && file.isDirectory()) {
            System.out.println(file + " is Directory");
        } else {
            System.out.println("Not directory");
        }

        System.out.println("File name " + file.getName());
        System.out.println("File parent " + file.getParent());
        System.out.println("File absolute path " + file.getAbsolutePath());
    }


    @Override
    public void demoListFiles() {
        File dir = new File(DIR_PATH);

        for (String file : dir.list()) {
            System.out.println(file);
        }

        System.out.println("-----------");
        for (File file : dir.listFiles()) {
            System.out.println(file.getName() + (file.isDirectory() ? " DIR" : " FILE"));
        }
    }

    public static void main(String[] args) {
        IODemo ioDemo = new IODemo();
        //   ioDemo.demoFile();
        //ioDemo.demoListFiles();
        //  ioDemo.demoReadFile();
        //ioDemo.demoWriteFile();
        ioDemo.demoWriteBinaryFile();
    }

    @Override
    public void demoReadFile() {
        IODemoReadFile ioDemoReadFile = new IODemoReadFile(FILE_PATH);
        //ioDemoReadFile.readFile();
        // ioDemoReadFile.readFileByChunks();
        //ioDemoReadFile.readFileWithBufferedReader();

    }

    @Override
    public void demoWriteFile() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(
                FILE_TO_WRITE_PATH
        ))) {
            List<String> content = Arrays.asList("a", "b", "c", "Для отоб222");
            for (String contentItem : content) {
                writer.println(contentItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demoWriteBinaryFile() {
        try (DataOutputStream dataOutputStream
                     = new DataOutputStream(new FileOutputStream(FILE_PATH_ZIP_DEST));
             DataInputStream inputStream = new DataInputStream(new FileInputStream(FILE_PATH_ZIP_SRC))
        ) {
            int read = -1;
            while ((read = inputStream.read()) != -1) {
                dataOutputStream.write(read);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * BaseCountry
     discriminator = COLD | HOT


     CountryColdClimate
     telephoneCode
     polarNight:bool

     CountryWithHotClimate
     -hottestMonth
     -averageTemp
     */
}
