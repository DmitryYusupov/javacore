package ru.yusdm.javacore.lesson20.lesson;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * Created by Admin on 4/5/2019.
 */
public class A_ReflectionDemo {

    public static void main(String[] args) throws Exception {
        //demoMethods();
        //  Person p = new Person();
        // demoFields(p.getClass().getDeclaredFields());
        //   demoFields(Person.class.getDeclaredFields());
        //demoFields(Person.class.getFields());
        Person person = new Person("Ivan", 33, "Ivanov");
        Person p = prototype(person);

        System.out.println();
    }

    private static <T> T prototype(T src) throws Exception {
        if (src instanceof Cloneable) {
            Class<?>[] interfaces = src.getClass().getInterfaces();

            for (Class<?> c : interfaces) {
                if (c.equals(Cloneable.class)) {
                    Method clone = src.getClass().getDeclaredMethod("clone");
                    return (T) clone.invoke(src);
                }
            }
            throw new IllegalArgumentException("TV 3 Real mistic; RenTV");
        } else {
            throw new IllegalArgumentException("Input data doesn't support Clonable");
        }
    }


    private static void demoAccessVisibility() throws Exception {
        demoAccessField();

        Person p = new Person();
        Field name = p.getClass().getDeclaredField("name");
        name.set(p, "New name");
        System.out.println(p);
    }

    public static void demoMethods() {
        Method[] methods = Person.class.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println("Method name " + method.getName());
            System.out.println("Method modificator " + Modifier.toString(method.getModifiers()));

            int parameterCount = method.getParameterCount();
            System.out.println("Param count " + parameterCount);

            for (Parameter param : method.getParameters()) {
                System.out.println("Param name " + param.getName() + " " + param.getType());
            }

            Class<?> returnType = method.getReturnType();
            System.out.println("Return type " + returnType);
            System.out.println("-----------------------------");
        }
    }

    public static void demoFields(Field[] fields) {
        Arrays.stream(fields).forEach(f -> {
            System.out.println("-----------------");
            System.out.println("Name " + f.getName());
            System.out.println("Modifier " + Modifier.toString(f.getModifiers()));
            System.out.println("Type " + f.getType());
        });
    }


    private static void demoAccessField() throws Exception {
        Person person = new Person();
        Field name = person.getClass().getDeclaredField("name");

        name.setAccessible(true);
        name.set(person, "NewName");
        System.out.println(person);


        Person newPerson = new Person();
        name.set(newPerson, "AAAA");
        System.out.println(newPerson);
    }




    private static void f1(){
        try{

        }catch (Exception e){
            System.out.println("Sent email");
        }
    }


    @NotNull
    private static void f3(){
        //logic
    }

    private static void f2(){
        try{

        }catch (Exception e){
            System.out.println("Sent email");
        }
    }
}
