package ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.parsing;

import ru.yusdm.javacore.lesson11ionio.autoservice.common.solutions.utils.FileUtils;
import ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.model.Person;
import ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.parsing.stax.StaxXmlParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Admin on 3/18/2019.
 */
public class XmlParserDemo {
    public static void main(String[] args) {
        File fileWithXml = null;
        try {
            fileWithXml = FileUtils.createFileFromResource("persons", ".xml", "/ru/yusdm/javacore/lesson12xml/lesson/xml/parse/parsing/persons.xml");
            List<Person> persons = new StaxXmlParser().parse(fileWithXml.getAbsolutePath());
            for (Person person : persons) {
                System.out.println(person + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileWithXml != null) {
                try {
                    Files.delete(Paths.get(fileWithXml.toURI()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
