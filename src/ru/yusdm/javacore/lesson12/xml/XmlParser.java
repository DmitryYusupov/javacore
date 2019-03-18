package ru.yusdm.javacore.lesson12.xml;

/**
 * Created by Admin on 3/18/2019.
 */
public interface XmlParser<T> {
    T parse(String file) throws Exception;
}
