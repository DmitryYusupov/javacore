package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.visibility.packageprivate.subpackage;


import ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.visibility.packageprivate.Parent;

public class ParentChildInOtherPackage extends Parent {

    void sayHello() {
        System.out.println("Not overrideoverload, because of method is not visible");
    }
}
