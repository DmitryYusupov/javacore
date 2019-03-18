package ru.yusdm.javacore.lesson12.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/18/2019.
 */
public class DomXmlParser implements XmlParser<List<Person>> {

    @Override
    public List<Person> parse(String file) throws Exception {

        if (!new File(file).exists() || new File(file).isDirectory()) {
            throw new Exception("No such file");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(file));

        Node root = doc.getElementsByTagName("persons").item(0);
        NodeList persons = ((Element) root).getElementsByTagName("person");

        List<Person> parsedPersons = new ArrayList<>();

        if (persons.getLength() > 0) {
            for (int i = 0; i < persons.getLength(); i++) {

                Node xmlPerson = persons.item(i);
                Person person = new Person();
                parsedPersons.add(person);

                NodeList personInnerTags = xmlPerson.getChildNodes();

                for (int j = 0; j < personInnerTags.getLength(); j++) {
                    Node personInner = personInnerTags.item(j);

                    switch (personInner.getNodeName()) {
                        case "name": {
                            person.setName(personInner.getTextContent());
                            break;
                        }

                        case "lastName": {
                            person.setLastName(personInner.getTextContent());
                            break;
                        }

                        case "children": {
                            person.setChildren(new ArrayList<>());

                            NodeList xmlChildren = ((Element) personInner).getElementsByTagName("child");

                            for (int k = 0; k < xmlChildren.getLength(); k++) {
                                Element xmlChild = (Element) xmlChildren.item(k);

                                Child child = new Child();
                                person.getChildren().add(child);

                                child.setHobby(xmlChild.getAttribute("hobby"));
                                child.setAge(Integer.parseInt(xmlChild.getElementsByTagName("age").item(0).getTextContent()));
                                child.setName(xmlChild.getElementsByTagName("name").item(0).getTextContent());
                                child.setSchool(xmlChild.getElementsByTagName("school").item(0).getTextContent());
                            }

                            break;
                        }
                    }
                }

            }
        }

        return parsedPersons;
    }


}
