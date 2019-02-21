package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.packageprivate;

public class DemoPackagePrivateVisibilitySamePackage {

    public static void main(String[] args) {
        Person person = new Person("Dmitry", "Yusupov");
        System.out.println("Person name " + person.name);
        System.out.println("Person name " + person.getName());
    }


}
