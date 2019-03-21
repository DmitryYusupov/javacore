package ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.parsing.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.model.Child;
import ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/20/2019.
 */
public class PersonSaxHandler extends DefaultHandler {

    private List<Person> persons;
    private Person person;
    private List<Child> children;
    private StringBuilder stringBuilder = new StringBuilder();
    private Child child;
    private boolean nameForPersonWasSet = false;

    List<String> path = new ArrayList<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        path.add(qName);

        switch (qName) {

            case "persons": {
                persons = new ArrayList<>();
                break;
            }

            case "person": {
                person = new Person();
                nameForPersonWasSet = false;
                break;
            }

            case "children": {
                children = new ArrayList<>();
                break;
            }

            case "child": {
                child = new Child();
                String hobby = attributes.getValue("hobby");
                child.setHobby(hobby);
                break;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        path.remove(path.size() -1);

        switch (qName) {
            case "person": {
                persons.add(person);
                break;
            }

            case "name": {
                String data = stringBuilder.toString();
                if (!nameForPersonWasSet) {
                    person.setName(data);
                    nameForPersonWasSet = true;
                } else {
                    child.setName(data);
                }
                stringBuilder.setLength(0);
                break;
            }

            case "lastName": {
                person.setLastName(stringBuilder.toString());
                stringBuilder.setLength(0);
                //throw new RuntimeException("Ivan found");
                break;
            }

            case "age": {
                child.setAge(Integer.parseInt(stringBuilder.toString()));
                stringBuilder.setLength(0);
                break;
            }

            case "school": {
                child.setSchool(stringBuilder.toString());
                stringBuilder.setLength(0);
                break;
            }

            case "children": {
                person.setChildren(children);
                break;
            }

            case "child": {
                children.add(child);
                break;
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        stringBuilder.append(data.trim());
    }

    public List<Person> getPersons() {
        return persons;
    }
}
