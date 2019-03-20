package ru.yusdm.javacore.lesson12xml.lesson.xml.parse.parsing.sax;

import ru.yusdm.javacore.lesson12xml.lesson.xml.parse.model.Person;
import ru.yusdm.javacore.lesson12xml.lesson.xml.parse.parsing.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

/**
 * Created by Admin on 3/20/2019.
 */
public class SaxXmlParser implements XmlParser<List<Person>> {

    @Override
    public List<Person> parse(String file) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        PersonSaxHandler handler = new PersonSaxHandler();
        saxParser.parse(new File(file), handler);

        return handler.getPersons();
    }

}
