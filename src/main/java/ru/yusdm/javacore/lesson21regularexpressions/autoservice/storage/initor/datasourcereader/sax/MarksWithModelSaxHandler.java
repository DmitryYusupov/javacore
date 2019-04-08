package ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.initor.datasourcereader.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.TruckModel;

import java.util.*;

import static ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.utils.CollectionUtils.getLast;

public class MarksWithModelSaxHandler extends DefaultHandler {
    private static final String INIT_DATA_PATH = "init-data";
    private static final String MARKS_PATH = INIT_DATA_PATH + "/marks";
    private static final String MARK_PATH = MARKS_PATH + "/mark";
    private static final String MARK_COUNTRY_PATH = MARK_PATH + "/country";
    private static final String MARK_NAME_PATH = MARK_PATH + "/name";
    private static final String MODELS_PATH = MARK_PATH + "/models";
    private static final String MODEL_PATH = MODELS_PATH + "/model";
    private static final String MODEL_NAME_PATH = MODEL_PATH + "/name";
    private static final String MODEL_DESCRIPTION_PATH = MODEL_PATH + "/description";
    private static final String MODEL_PRODUCTION_YEAR_START_PATH = MODEL_PATH + "/productionYearStart";
    private static final String MODEL_PRODUCTION_YEAR_END_PATH = MODEL_PATH + "/productionYearEnd";

    private static final String MODEL_PASSENGER_NUMBER_OF_AIR_BAGS_PATH = MODEL_PATH + "/numberOfAirbags";
    private static final String MODEL_PASSENGER_NUMBER_OF_SEATS_PATH = MODEL_PATH + "/numberOfSeats";
    private static final String MODEL_PASSENGER_AUDIO_SYS_NAME_PATH = MODEL_PATH + "/audioSystemName";

    private static final String MODEL_TRUCK_WEIGHT_PATH = MODEL_PATH + "/weight";
    private static final String MODEL_TRUCK_EMBEDDED_KITCHEN_PATH = MODEL_PATH + "/embeddedKitchen";
    private static final String MODEL_TRUCK_TANK_SIZE_PATH = MODEL_PATH + "/tankSize";


    private StringBuilder content = new StringBuilder();
    private List<Mark> marks = Collections.emptyList();
    private List<Model> models = Collections.emptyList();

    private Deque<String> tagStack = new ArrayDeque<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        content.setLength(0);
        tagStack.add(qName);

        switch (stackAsStringPath()) {
            case MARKS_PATH: {
                marks = new ArrayList<>();
                break;
            }

            case MARK_PATH: {
                marks.add(new Mark());
                break;
            }

            case MODELS_PATH: {
                models = new ArrayList<>();
                getLast(marks).setModels(models);
                break;
            }

            case MODEL_PATH: {
                if (isPassenger(attributes)) {
                    models.add(new PassengerModel());
                } else {
                    models.add(new TruckModel());
                }
                break;
            }
        }
    }

    private boolean isPassenger(Attributes attributes) {
        return ModelDiscriminator.PASSENGER.equals(ModelDiscriminator.valueOf(attributes.getValue("type")));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String dataAsStr = content.toString();

        switch (stackAsStringPath()) {
            case MARK_NAME_PATH: {
                getLast(marks).setName(dataAsStr);
                break;
            }

            case MARK_COUNTRY_PATH: {
                getLast(marks).setCountry(dataAsStr);
                break;
            }

            case MODEL_NAME_PATH: {
                getLast(models).setName(dataAsStr);
                break;
            }

            case MODEL_DESCRIPTION_PATH: {
                getLast(models).setDescription(dataAsStr);
                break;
            }

            case MODEL_PRODUCTION_YEAR_START_PATH: {
                getLast(models).setProductionYearStart(Integer.parseInt(dataAsStr));
                break;
            }

            case MODEL_PRODUCTION_YEAR_END_PATH: {
                getLast(models).setProductionYearEnd(Integer.parseInt(dataAsStr));
                break;
            }

            case MODEL_PASSENGER_AUDIO_SYS_NAME_PATH: {
                getPassengerModel().setAudioSystemName(dataAsStr);
                break;
            }

            case MODEL_PASSENGER_NUMBER_OF_AIR_BAGS_PATH: {
                getPassengerModel().setNumberOfAirbags(Integer.valueOf(dataAsStr));
                break;
            }

            case MODEL_PASSENGER_NUMBER_OF_SEATS_PATH: {
                getPassengerModel().setNumberOfSeats(Integer.valueOf(dataAsStr));
                break;
            }

            case MODEL_TRUCK_EMBEDDED_KITCHEN_PATH: {
                getTruckModel().setEmbeddedKitchen("Y".equals(dataAsStr));
                break;
            }

            case MODEL_TRUCK_TANK_SIZE_PATH: {
                getTruckModel().setTankSize(Integer.valueOf(dataAsStr));
                break;
            }

            case MODEL_TRUCK_WEIGHT_PATH: {
                getTruckModel().setWeight(Integer.valueOf(dataAsStr));
                break;
            }
        }
        tagStack.removeLast();
    }

    private PassengerModel getPassengerModel() {
        return (PassengerModel) getLast(models);
    }

    private TruckModel getTruckModel() {
        return (TruckModel) getLast(models);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        content.append(value.replaceAll("\\n",""));
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    public List<Mark> getMarks() {
        return marks;
    }


    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = tagStack.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }

}
