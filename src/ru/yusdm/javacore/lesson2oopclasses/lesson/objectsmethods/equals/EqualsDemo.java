package ru.yusdm.javacore.lesson2oopclasses.lesson.objectsmethods.equals;

/**
 * Created by Admin on 2/18/2019.
 */
public class EqualsDemo {

    private static class Person2{
        private int age;
        private int weight;

        public Person2(int age, int weight) {
            this.age = age;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person2 person2 = (Person2) o;

            if (age != person2.age) return false;
            return weight == person2.weight;
        }

        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + weight;
            return result;
        }
    }

    private static class Person {
        private String name;
        private String lastName;

        public Person(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof Person) {
                Person otherPerson = (Person) obj;

                return otherPerson.name.equals(this.name)
                        && otherPerson.lastName.equals(this.lastName);
            }

            return false;
        }
    }


    public static void main(String[] args) {

        Person2 person = new Person2(100, 10);
        Person2 person2 = new Person2(100, 10);


        System.out.println(person.hashCode());
        System.out.println(person2.hashCode());

        System.out.println("Equals " + person.equals(person2));

       /* Person person1 = new Person("Ivan", "Ivanov");
        Person person2 = new Person("Ivan", "Ivanov");


        System.out.println("HashCode "  + person1.hashCode());
        if (person1 == person2) {
            System.out.println("Equals");
        }

        person1.equals("asdasd");


        if (person1.equals(person2)) {
            System.out.println("Equals using method");
        }

        System.out.println("HashCode "  + person1.hashCode());
*/


    }
}
