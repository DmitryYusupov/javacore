package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.storage.initor.datasourcereader;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.xml.stax.XmlStaxUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public class StaxReader implements AutoCloseable {
    private XMLStreamReader reader;

    private StaxReader(){
    }

    public static StaxReader getStaxReader(InputStream inputStream) throws XMLStreamException {
        StaxReader staxReader = new StaxReader();
        staxReader.reader = XmlStaxUtils.getReader(inputStream);
        return staxReader;
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }

    public XMLStreamReader getReader() {
        return reader;
    }

}
