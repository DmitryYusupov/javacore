package ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.parsing.stax;

import ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.xml.stax.parse.CustomStaxReader;
import ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.model.Child;
import ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.model.Person;
import ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.parsing.XmlParser;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.xml.stax.XmlStaxUtils.readContent;

/**
 * Created by Admin on 3/20/2019.
 */
public class StaxXmlParser implements XmlParser<List<Person>> {

    @Override
    public List<Person> parse(String file) throws Exception {
        try (CustomStaxReader customStaxReader = CustomStaxReader.newInstance(file)) {
            return readDocument(customStaxReader.getReader());
        }
    }

    private List<Person> readDocument(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();

                    if ("persons".equals(elementName)) {
                        return readPersons(reader);
                    }
                    break;
                }

                case XMLStreamReader.END_ELEMENT: {
                    String elementName = reader.getLocalName();
                    System.out.println("End elem " + elementName);
                    break;
                }
            }
        }

        throw new RuntimeException("I didn't find suitable end tag");
    }

    private List<Person> readPersons(XMLStreamReader reader) throws XMLStreamException {
        List<Person> persons = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();

                    if ("person".equals(elementName)) {
                        persons.add(readPerson(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return persons;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private Person readPerson(XMLStreamReader reader) throws XMLStreamException {

        Person person = new Person();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();
                    if ("name".equals(elementName)) {
                        person.setName(readContent(reader));
                    } else if ("lastName".equals(elementName)) {
                        person.setLastName(readContent(reader));
                    } else if ("children".equals(elementName)) {
                        person.setChildren(readChildren(reader));
                    }

                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return person;
                }

            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private List<Child> readChildren(XMLStreamReader reader) throws XMLStreamException {
        List<Child> children = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();
                    if ("child".equals(elementName)) {
                        children.add(readChild(reader));
                    }

                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return children;
                }
            }
        }

        throw new RuntimeException("I didn't find suitable end tag");
    }

    private Child readChild(XMLStreamReader reader) throws XMLStreamException {
        Child child = new Child();
        child.setHobby(reader.getAttributeValue(null, "hobby"));

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();
                    //<name>
                    if ("name".equals(elementName)) {
                        child.setName(readContent(reader));
                        //</name>

                    } else if ("school".equals(elementName)) {
                        //  "<school>"
                        child.setSchool(readContent(reader));
                        //"</school>"

                    } else if ("age".equals(elementName)) {
                        child.setAge(Integer.parseInt(readContent(reader)));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    //"</child>";
                    return child;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }


}
