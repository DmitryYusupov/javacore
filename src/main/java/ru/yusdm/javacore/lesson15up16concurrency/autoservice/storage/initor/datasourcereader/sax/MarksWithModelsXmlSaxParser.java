package ru.yusdm.javacore.lesson15up16concurrency.autoservice.storage.initor.datasourcereader.sax;

import ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.solutions.xml.sax.XmlSaxUtils;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.storage.initor.datasourcereader.FileParser;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.util.List;

public class MarksWithModelsXmlSaxParser implements FileParser<List<Mark>> {


    @Override
    public List<Mark> parseFile(String file) throws Exception {
        SAXParser saxParser = XmlSaxUtils.getParser();

        MarksWithModelSaxHandler saxHandler = new MarksWithModelSaxHandler();
        saxParser.parse(new File(file), saxHandler);
        return saxHandler.getMarks();
    }
}
