package ru.yusdm.javacore.lesson12xml.lesson.xml.parse.parsing.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

/**
 * Created by Admin on 3/20/2019.
 */
public class CustomStaxReader implements AutoCloseable {

    private XMLStreamReader reader = null;
    private FileInputStream fileInputStream;


    private CustomStaxReader(){

    }

    public static CustomStaxReader newInstance(String file) throws Exception {
        CustomStaxReader instance = new CustomStaxReader();
        instance.fileInputStream = new FileInputStream(file);
        instance.reader = XMLInputFactory.newInstance()
                .createXMLStreamReader(instance.fileInputStream);
        return instance;
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }

        if (fileInputStream != null) {
            fileInputStream.close();
        }
    }


    public XMLStreamReader getReader() {
        return reader;
    }
}
