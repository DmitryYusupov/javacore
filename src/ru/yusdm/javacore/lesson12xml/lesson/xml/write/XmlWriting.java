package ru.yusdm.javacore.lesson12xml.lesson.xml.write;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.yusdm.javacore.lesson12xml.lesson.xml.parse.model.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/20/2019.
 */
public class XmlWriting {

    public static void main(String[] args) throws Exception {
        List<Person> people = getPersons();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("persons");
        doc.appendChild(root);

        for (Person person : people) {
            Element personDom = doc.createElement("person");
            root.appendChild(personDom);

            Element name = doc.createElement("name");

            personDom.appendChild(name);
        }


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource domSource = new DOMSource(doc);

    }

    private static List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setName("Ivan");
        person.setLastName("Ivanov");
        persons.add(person);

        person = new Person();
        person.setName("Petr");
        person.setLastName("Petrov");
        persons.add(person);

        return persons;
    }

}
