package ru.yusdm.javacore.lesson17java8.autoservice.common.solutions.xml.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public final class XmlStaxUtils {

    private XmlStaxUtils() {

    }


    public static XMLStreamReader getReader(InputStream inputStream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLStreamReader(inputStream);
    }

    /**
     * Read data which is located between couple of tags:
     * <p>
     * Ex:
     * <name>Ivan Ivanovich</name>
     *
     * @param reader
     * @return content between couple of tags
     * @throws XMLStreamException
     */
    public static String readContent(XMLStreamReader reader) throws XMLStreamException {

        StringBuilder content = new StringBuilder();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamConstants.CHARACTERS:
                case XMLStreamConstants.CDATA: {
                    content.append(reader.getText());
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return content.toString();
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }


}
