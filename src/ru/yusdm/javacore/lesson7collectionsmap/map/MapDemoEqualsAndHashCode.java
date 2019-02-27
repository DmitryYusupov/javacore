package ru.yusdm.javacore.lesson7collectionsmap.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2/27/2019.
 */
public class MapDemoEqualsAndHashCode {

    private static class Person{
        private String name;
        private String latName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (name != null ? !name.equals(person.name) : person.name != null) return false;
            return latName != null ? latName.equals(person.latName) : person.latName == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (latName != null ? latName.hashCode() : 0);
            return result;
        }
    }

    private static class PersonNoEquals{
        private String name;
        private String latName;

        public PersonNoEquals(String name, String latName) {
            this.name = name;
            this.latName = latName;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (latName != null ? latName.hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) {
       //demoCantFindElemnt();
       //demoCantFindElemnt2();
       //demoCantFindElement3();
       demoCanFindElement4();
      //  putSameKeyToMap();
    }

    public static void demoCantFindElemnt(){
        Map<Person, String> map = new HashMap<>();

        Person person1 = new Person();
        //1) person1.getHash() 16
        //2) person1Hash % bucketSize
        //3) put
        map.put(person1, "One");

        map.put(new Person(), "Two");

        Person key = new Person();
        //key.getHash() = 32
        //
        System.out.println(map.get(key));
    }

    public static void demoCantFindElemnt2(){
        Map<PersonNoEquals, String> map = new HashMap<>();

        PersonNoEquals person1 = new PersonNoEquals("A","A");
        map.put(person1, "One");

        PersonNoEquals person2 = new PersonNoEquals("B","B");
        System.out.println("Hash " + person2.hashCode());
        map.put(person2, "Two");

        PersonNoEquals person3 = new PersonNoEquals("B","B");
        map.put(person3, "Person3");

        PersonNoEquals key = new PersonNoEquals("B", "B");
        System.out.println("Hash " + key.hashCode());
        System.out.println(map.get(key));
        System.out.println(map.size());

    }

    private static void putSameKeyToMap(){
        Map<String, String> map = new HashMap<>();

        System.out.println(map.put("a","A"));
        System.out.println(map.put("a","B"));
        System.out.println(map.size());
        System.out.println(map.get("a"));
    }


    private static class PersonNoHashCode{
        private String name;
        private String latName;

        public PersonNoHashCode(String name, String latName) {
            this.name = name;
            this.latName = latName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PersonNoHashCode that = (PersonNoHashCode) o;

            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return latName != null ? latName.equals(that.latName) : that.latName == null;
        }
    }

    public static void demoCantFindElement3(){
        Map<PersonNoHashCode, String> map = new HashMap<>();

        PersonNoHashCode person1 = new PersonNoHashCode("A","A");
        map.put(person1, "One");

        PersonNoHashCode person2 = new PersonNoHashCode("B","B");
        System.out.println("Hash " + person2.hashCode());
        map.put(person2, "Two");

        PersonNoHashCode person3 = new PersonNoHashCode("B","B");
        map.put(person3, "Person3");

        PersonNoHashCode key = new PersonNoHashCode("B", "B");
        System.out.println("Hash " + key.hashCode());
        System.out.println(map.get(key));
        System.out.println(map.size());

    }

    private static class PersonWithHashCodeAndEquals {
        private String name;
        private String latName;

        public PersonWithHashCodeAndEquals(String name, String latName) {
            this.name = name;
            this.latName = latName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PersonWithHashCodeAndEquals that = (PersonWithHashCodeAndEquals) o;

            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return latName != null ? latName.equals(that.latName) : that.latName == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (latName != null ? latName.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "PersonWithHashCodeAndEquals{" +
                    "name='" + name + '\'' +
                    ", latName='" + latName + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLatName() {
            return latName;
        }

        public void setLatName(String latName) {
            this.latName = latName;
        }
    }

    public static void demoCanFindElement4(){
        Map<PersonWithHashCodeAndEquals, String> map = new HashMap<>();

        PersonWithHashCodeAndEquals person1 = new PersonWithHashCodeAndEquals("A","A");
        map.put(person1, "One");

        PersonWithHashCodeAndEquals person2 = new PersonWithHashCodeAndEquals("B","B");
        System.out.println("Hash " + person2.hashCode());
        map.put(person2, "Two");

        PersonWithHashCodeAndEquals person3 = new PersonWithHashCodeAndEquals("B","B");
        map.put(person3, "Person3");

        PersonWithHashCodeAndEquals key = new PersonWithHashCodeAndEquals("B", "B");
        System.out.println("Hash " + key.hashCode());

        System.out.println(map.get(key));
        key.setName("kjhkh");
        System.out.println(map.get(key));
        System.out.println(map.size());

    }
}
