package ru.yusdm.javacore.lesson20up21reflection.lesson.part2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Admin on 4/8/2019.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Repeatable {
    String errorMessage();
    int repeatTimes();
    int maxNumberOfTries() default 10;
}
