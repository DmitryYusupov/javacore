package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.concurrency;

public class ImmutableDemo {

    private static final class Passport {
        private int serial;
        private int number;

        public int getSerial() {
            return serial;
        }

        public void setSerial(int serial) {
            this.serial = serial;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    private static final class Person {
        private final String name;
        private final Passport passport;

        public Person(String name, Passport passport) {
            this.name = name;
            this.passport = clonePassport(passport);
        }

        public String getName() {
            return name;
        }

        public Passport getPassport() {
            return clonePassport(this.passport);
        }

        private Passport clonePassport(Passport passport) {
            Passport toReturn = new Passport();

            toReturn.number = passport.number;
            toReturn.serial = passport.serial;

            return toReturn;
        }
    }

    public static void main(String[] args) {
        Passport passport = new Passport();
        passport.serial = 1;
        passport.number = 2;

        new Person("Ivan", passport);

        passport.number = 333;
    }


}
