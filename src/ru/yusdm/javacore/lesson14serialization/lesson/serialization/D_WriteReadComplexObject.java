package ru.yusdm.javacore.lesson14serialization.lesson.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Admin on 3/22/2019.
 */
public class D_WriteReadComplexObject {

    private static class Passport implements Serializable{
        private int serial;
        private int number;

        public Passport(int serial, int number) {
            this.serial = serial;
            this.number = number;
        }
    }

    private static class Person implements Serializable {
        private String name;
        private String lastName;
        private Passport passport;

        public Person() {
        }

        public Person(String name, String lastName, Passport passport) {
            this.name = name;
            this.lastName = lastName;
            this.passport = passport;
        }
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person("Ivan", "Ivanov", new Passport(11, 22));

        String pathToSerializedData = getSerializedPerson(person);
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
        Path file = Files.createTempFile("lesson14serialization", ".txt");
        System.out.println(file.toAbsolutePath());

        try (ObjectOutput objectOutput = new ObjectOutputStream(
                new FileOutputStream(file.toFile())
        )) {
            objectOutput.writeObject(person);
        }
        return file.toAbsolutePath() + "";

    }
}
