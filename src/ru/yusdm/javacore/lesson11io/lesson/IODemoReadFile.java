package ru.yusdm.javacore.lesson11io.lesson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/13/2019.
 */
public class IODemoReadFile {
    private final String path;

    public IODemoReadFile(String path) {
        this.path = path;
    }

    public void readFile() {
        File src = new File(path);

        InputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(src);
            int data = -1;
            StringBuilder content = new StringBuilder();

            while ((data = fileInputStream.read()) != -1) {
                char c = (char) data;
                content.append(c);
            }

            System.out.println(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    System.out.println("Error while close file");
                }
            }
        }
    }

    public void readFileByChunks() {
        File src = new File(path);

        InputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(src);
            byte[] chunk = new byte[4096];
            int readNumber = -1;
            StringBuilder content = new StringBuilder();

            while ((readNumber = fileInputStream.read(chunk, 0, chunk.length)) != -1) {
                System.out.println(readNumber);
                for (int i = 0; i < readNumber; i++) {
                    content.append((char) chunk[i]);
                }
            }

            System.out.println(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    System.out.println("Error while close file");
                }
            }
        }
    }

    public void readFileWithBufferedReader() {
        File src = new File(path);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(src));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Error while close file");
                }
            }
        }
    }

    public void readFileWithBufferedReaderAutoClosable() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
