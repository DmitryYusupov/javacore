package ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.datasourcereader;

import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.domain.Mark;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.xml.stax.XmlStaxUtils.readCharacters;

public class MarksWithModelXmlStaxParser implements FileParser<List<Mark>> {

    @Override
    public List<Mark> parseFile(String file) throws Exception {

        try (FileInputStream inputStream = new FileInputStream(file);
             StaxReader staxReader = StaxReader.getStaxReader(inputStream)) {
            XMLStreamReader reader = staxReader.getReader();

            List<Mark> marks = new ArrayList<>();

            while (reader.hasNext()) {
                int eventType = reader.next();

                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT: {
                        String elementName = reader.getLocalName();

                        if ("mark".equals(elementName)) {
                            Mark mark = new Mark();
                            appendMarkData(mark, reader);
                            marks.add(mark);
                        }
                        break;
                    }
                }

            }

            return marks;
        }
    }

    private void appendMarkData(Mark mark, XMLStreamReader reader) throws XMLStreamException {
        String elementName = "";

        while (reader.hasNext()) {

            int eventType = reader.next();
            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    elementName = reader.getLocalName();
                    break;
                }

                case XMLStreamConstants.CDATA:
                case XMLStreamConstants.CHARACTERS: {

                    switch (elementName) {
                        case "name": {
                            mark.setName(readCharacters(reader));
                            break;
                        }

                        case "country": {
                            mark.setCountry(readCharacters(reader));
                            break;
                        }
                    }

                    break;
                }
            }
        }
    }


}