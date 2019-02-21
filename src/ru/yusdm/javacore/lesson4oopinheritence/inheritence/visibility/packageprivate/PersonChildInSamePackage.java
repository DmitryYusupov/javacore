package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.packageprivate;

public class PersonChildInSamePackage extends Person {

    @Override
    public void printPerson() {
        super.printPerson();
    }
}
