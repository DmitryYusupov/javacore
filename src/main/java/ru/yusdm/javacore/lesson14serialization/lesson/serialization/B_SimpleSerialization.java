package ru.yusdm.javacore.lesson14serialization.lesson.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Admin on 3/22/2019.
 */
public class B_SimpleSerialization {

    private static class Person implements Serializable{
        private String name;
        private String lastName;

        public Person() {
        }

        public Person(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }
    }

    public static void main(String[] args) throws IOException {
        Path file = null;
        try {
            file = Files.createTempFile("lesson14serialization", ".txt");
            System.out.println(file.toAbsolutePath());

            try(ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile())
            )){
                objectOutput.writeObject(new Person("Ivan", "Ivanov"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

}
