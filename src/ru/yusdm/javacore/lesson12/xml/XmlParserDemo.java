package ru.yusdm.javacore.lesson12.xml;

import java.util.List;

/**
 * Created by Admin on 3/18/2019.
 */
public class XmlParserDemo {
    public static void main(String[] args) {
        try {
            String xmlFile = "C:\\Users\\Admin\\Desktop\\git\\javacore\\src\\ru\\yusdm\\javacore\\lesson12\\xml\\persons.xml";
            List<Person> persons = new DomXmlParser().parse(xmlFile);
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
