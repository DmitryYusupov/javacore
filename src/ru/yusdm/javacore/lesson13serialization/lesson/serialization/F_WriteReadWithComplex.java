package ru.yusdm.javacore.lesson13serialization.lesson.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Admin on 3/22/2019.
 */
public class F_WriteReadWithComplex {
    private static class SecretKey implements Serializable {
        private String key;

        public SecretKey(String key) {
            this.key = key;
        }
    }

    private static class Passport implements Serializable {

        private int serial;
        private int number;

        public Passport(int serial, int number) {
            this.serial = serial;
            this.number = number;
        }
    }

    private static class Person implements Serializable {

        private static String someProp = "SOME PROP";

        private String name;
        private String lastName;
        private Passport passport;
        private transient SecretKey secretKey;

        public Person() {
        }

        public Person(String name, String lastName, Passport passport, SecretKey secretKey) {
            this.name = name;
            this.lastName = lastName;
            this.passport = passport;
            this.secretKey = secretKey;
        }
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person("Ivan", "Ivanov", new Passport(11, 22), new SecretKey("AAA"));

        String pathToSerializedData = getSerializedPerson(person);
        Person.someProp = "AAAAAA";
        Person person1 = readDataFromFile(pathToSerializedData);

        System.out.println();
    }

    private static Person readDataFromFile(String file) throws Exception {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = objectInputStream.readObject();
            return (Person) o;
        }
    }

    private static String getSerializedPerson(Person person) throws IOException {
        Path file = Files.createTempFile("lesson13serialization", ".txt");
        System.out.println(file.toAbsolutePath());

        try (ObjectOutput objectOutput = new ObjectOutputStream(
                new FileOutputStream(file.toFile())
        )) {
            objectOutput.writeObject(person);
        }
        return file.toAbsolutePath() + "";

    }
}
