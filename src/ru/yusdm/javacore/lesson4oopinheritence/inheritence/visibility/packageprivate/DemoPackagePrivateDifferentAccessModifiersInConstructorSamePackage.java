package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.packageprivate;

public class DemoPackagePrivateDifferentAccessModifiersInConstructorSamePackage {

    public static void main(String[] args) {
        PersonWithPackagePrivateConstructor person1 = new PersonWithPackagePrivateConstructor("Dmitry");
        PersonWithPackagePrivateConstructor person2 = new PersonWithPackagePrivateConstructor();
        System.out.println("Both constructors are available!");

    }
}
