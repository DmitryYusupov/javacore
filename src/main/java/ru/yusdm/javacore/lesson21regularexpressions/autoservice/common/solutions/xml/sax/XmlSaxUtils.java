package ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public final class XmlSaxUtils {

    private XmlSaxUtils() {

    }

    public static SAXParser getParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        return saxParserFactory.newSAXParser();
    }
}
