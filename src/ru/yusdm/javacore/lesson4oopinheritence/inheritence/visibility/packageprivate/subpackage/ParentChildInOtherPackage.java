package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.packageprivate.subpackage;


import ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.packageprivate.Parent;

public class ParentChildInOtherPackage extends Parent {

    void sayHello() {
        System.out.println("Not override, because of method is not visible");
    }
}
