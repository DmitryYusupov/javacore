package ru.yusdm.javacore.lesson13serialization.lesson.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Admin on 3/22/2019.
 */
public class A_SerializationError {

    private static class Person {
        private String name;
        private String lastName;

        public Person(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }
    }

    public static void main(String[] args) throws IOException {
        Path file = null;
        try {
            file = Files.createTempFile("lesson13serialization", ".txt");
            System.out.println(file.toAbsolutePath());

            try(ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile())
            )){
                objectOutput.writeObject(new Person("Ivan", "Ivanov"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                Files.deleteIfExists(file);
            }
        }
    }

}
