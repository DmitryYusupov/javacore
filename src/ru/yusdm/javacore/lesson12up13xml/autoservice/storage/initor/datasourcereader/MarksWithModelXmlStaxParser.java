package ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.datasourcereader;

import ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.xml.stax.parse.CustomStaxReader;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson12up13xml.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson12up13xml.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson12up13xml.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson12up13xml.autoservice.model.domain.TruckModel;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.xml.stax.XmlStaxUtils.readContent;
import static ru.yusdm.javacore.lesson12up13xml.autoservice.model.domain.ModelDiscriminator.PASSENGER;

public class MarksWithModelXmlStaxParser implements FileParser<List<Mark>> {


    private final RuntimeException NO_END_TAG_FOUND_EXCEPTION = new RuntimeException("Suitable end tag NOT found");

    @Override
    public List<Mark> parseFile(String file) throws Exception {

        List<Mark> result = new ArrayList<>();

        try (CustomStaxReader staxReader = CustomStaxReader.newInstance(file)) {

            XMLStreamReader reader = staxReader.getReader();

            while (reader.hasNext()) {
                int eventType = reader.next();

                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("marks".equals(tagName)) {
                            result = new ArrayList<>();
                            result.addAll(readDocument(reader));
                        }
                        break;
                    }

                    case XMLStreamConstants.END_ELEMENT: {
                        return result;
                    }
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<Mark> readDocument(XMLStreamReader reader) throws XMLStreamException {

        List<Mark> marks = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("mark".equals(tagName)) {
                        marks.add(readMark(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return marks;
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private Mark readMark(XMLStreamReader reader) throws XMLStreamException {
        Mark mark = new Mark();
        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("name".equals(tagName)) {
                        mark.setName(readContent(reader));
                    } else if ("country".equals(tagName)) {
                        mark.setCountry(readContent(reader));
                    } else if ("models".equals(tagName)) {
                        mark.setModels(readModels(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return mark;
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<Model> readModels(XMLStreamReader reader) throws XMLStreamException {
        List<Model> models = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("model".equals(tagName)) {
                        models.add(readModel(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return models;
                }
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private Model readModel(XMLStreamReader reader) throws XMLStreamException {
        String type = reader.getAttributeValue(null, "type");
        ModelDiscriminator modelDiscriminator = ModelDiscriminator.valueOf(type);

        Model model;
        if (PASSENGER.equals(modelDiscriminator)) {
            model = new PassengerModel();
        } else {
            model = new TruckModel();
        }

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    appendCommonModelData(model, tagName, reader);
                    if (model instanceof PassengerModel) {
                        appendPassengerAttributes((PassengerModel) model, tagName, reader);
                    } else {
                        appendTruckAttributes((TruckModel) model, tagName, reader);
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return model;
                }
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private void appendCommonModelData(Model model, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("name".equals(tagName)) {
            model.setName(readContent(reader));
        } else if ("description".equals(tagName)) {
            model.setDescription(readContent(reader));
        } else if ("productionYearStart".equals(tagName)) {
            model.setProductionYearStart(Integer.parseInt(readContent(reader)));
        } else if ("productionYearEnd".equals(tagName)) {
            model.setProductionYearEnd(Integer.parseInt(readContent(reader)));
        }
    }

    private void appendPassengerAttributes(PassengerModel model, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("numberOfAirbags".equals(tagName)) {
            model.setNumberOfAirbags(Integer.parseInt(readContent(reader)));
        } else if ("numberOfSeats".equals(tagName)) {
            model.setNumberOfSeats(Integer.parseInt(readContent(reader)));
        } else if ("audioSystemName".equals(tagName)) {
            model.setAudioSystemName(readContent(reader));
        }
    }

    private void appendTruckAttributes(TruckModel model, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("weight".equals(tagName)) {
            model.setWeight(Integer.parseInt(readContent(reader)));
        } else if ("embeddedKitchen".equals(tagName)) {
            model.setEmbeddedKitchen("Y".equals(readContent(reader)));
        } else if ("tankSize".equals(tagName)) {
            model.setTankSize(Integer.parseInt(readContent(reader)));
        }
    }

    protected String name;
    protected String description;
    protected int productionYearStart;
    protected Integer productionYearEnd;


}