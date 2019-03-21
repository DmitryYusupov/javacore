package ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.parsing;

/**
 * Created by Admin on 3/18/2019.
 */
public interface XmlParser<T> {
    T parse(String file) throws Exception;
}
