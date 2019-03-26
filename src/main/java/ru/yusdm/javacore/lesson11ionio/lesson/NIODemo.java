package ru.yusdm.javacore.lesson11ionio.lesson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Admin on 3/18/2019.
 */
public class NIODemo implements IO {

    public static final String FILE_PATH = "C:\\Users\\Admin\\Desktop\\demoio\\44.txt";
    public static final String FILE_PATH_ZIP_SRC = "C:\\Users\\Admin\\Desktop\\demoio\\zipFile.zip";
    public static final String FILE_PATH_ZIP_DEST = "C:\\Users\\Admin\\Desktop\\demoio\\zipFileCopy.zip";
    public static final String FILE_TO_WRITE_PATH = "C:\\Users\\Admin\\Desktop\\demoio\\to_write.txt";
    public static final String DIR_PATH = "C:\\Users\\Admin\\Desktop\\demoio\\";


    public static void main(String[] args) {
        NIODemo demo = new NIODemo();
        //demo.demoFile();
        //    demo.demoListFiles();
        //demo.demoReadFile();
        // demo.demoWriteFile();
        demo.demoWriteBinaryFile();
    }

    @Override
    public void demoFile() {
        Path file = Paths.get(new File(FILE_PATH).toURI());

        System.out.println(file.getFileName());
        System.out.println(file.getNameCount());
        System.out.println(file.getParent());
        System.out.println(file.getRoot());
    }

    @Override
    public void demoListFiles() {
        try {
            Path dir = Paths.get(new File(DIR_PATH).toURI());
            Stream<Path> list = Files.walk(dir, 2);
            list.forEach((value) -> {
                System.out.println(value);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* try {
            Path dir = Paths.get(new File(DIR_PATH).toURI());
            Stream<Path> list = Files.list(dir);
            list.forEach((value)->{
                System.out.println(value);
            });
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    @Override
    public void demoReadFile() {
        try {
            //Files.readAllBytes()

            /*BufferedReader bufferedReader = Files.newBufferedReader();
            bufferedReader.readLine();
            */

            List<String> strings = Files.readAllLines(Paths.get(FILE_PATH));

            for (String line : strings) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demoWriteFile() {

        try {
            List<String> data = Arrays.asList("1", "2", "3");
            Files.write(Paths.get(new File(DIR_PATH + "/123.txt").toURI()), data);

            List<String> data2 = Arrays.asList("10", "20", "30");
            Files.write(Paths.get(new File(DIR_PATH + "/123.txt").toURI()),
                    data2, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demoWriteBinaryFile() {
        write();
    }

    public void write() {
        try (SeekableByteChannel fin = Files.newByteChannel(Paths.get(new File(FILE_PATH_ZIP_SRC).toURI()));
             FileChannel out = new FileOutputStream(FILE_PATH_ZIP_DEST).getChannel();) {

            ByteBuffer buffer = ByteBuffer.allocate(2);

            int length = -1;
            while ((length = fin.read(buffer)) != -1) {
                /**
                 * 1 1 1 1 1 1 0 0
                 *                |
                 *      position  size = limit
                 */
                buffer.flip();
                out.write(buffer);
                buffer.compact();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeAsBuf() {
        try (FileChannel fin = new FileInputStream(FILE_PATH_ZIP_SRC).getChannel();
             FileChannel out = new FileOutputStream(FILE_PATH_ZIP_DEST).getChannel();) {

            MappedByteBuffer buffer =
                    fin.map(FileChannel.MapMode.READ_ONLY, 0, fin.size());
            out.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
