package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.visibility.packageprivate.subpackage;


import ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.visibility.packageprivate.PersonWithPackagePrivateConstructor;

public class DemoPackagePrivateDifferentAccessModifiersInConstructorOtherPackage {

    public static void main(String[] args) {
        //not accessable
        //PersonWithPackagePrivateConstructor person1 = new PersonWithPackagePrivateConstructor("Dmitry");
        PersonWithPackagePrivateConstructor person2 = new PersonWithPackagePrivateConstructor();
        System.out.println("Both constructors are available!");

    }
}
