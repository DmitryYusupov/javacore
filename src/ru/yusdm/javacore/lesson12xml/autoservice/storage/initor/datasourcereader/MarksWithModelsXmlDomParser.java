package ru.yusdm.javacore.lesson12xml.autoservice.storage.initor.datasourcereader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.yusdm.javacore.lesson12xml.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson12xml.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson12xml.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson12xml.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson12xml.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson12xml.autoservice.storage.initor.exception.cheked.InvalidModelDiscriminatorException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static ru.yusdm.javacore.lesson12xml.autoservice.common.solutions.utils.xml.XmlDomUtils.*;
import static ru.yusdm.javacore.lesson12xml.autoservice.common.solutions.utils.xml.XmlDomUtils.getOnlyElementTextContent;
import static ru.yusdm.javacore.lesson12xml.autoservice.storage.initor.exception.InitDataExceptionMeta.PARSE_MODEL_DISCRIMINATOR_ERROR;

public class MarksWithModelsXmlDomParser implements FileParser<List<Mark>> {

    @Override
    public List<Mark> parseFile(String file) throws Exception {

        Document doc = getDocument(file);
        Element root = getOnlyElement(doc, "marks");

        NodeList xmlMarks = root.getElementsByTagName("mark");
        List<Mark> result = new ArrayList<>();

        for (int i = 0; i < xmlMarks.getLength(); i++) {
            result.add(getMarkFromXmlElement(xmlMarks.item(i)));
        }
        return result;
    }

    private Mark getMarkFromXmlElement(Node xmlMark) throws Exception {
        Mark mark = new Mark();

        mark.setName(getOnlyElementTextContentOrNull((Element) xmlMark, "name"));
        mark.setCountry(getOnlyElementTextContentOrNull((Element) xmlMark, "country"));

        NodeList models = ((Element) xmlMark).getElementsByTagName("model");
        if (models.getLength() > 0) {
            mark.setModels(new ArrayList<>());

            for (int i = 0; i < models.getLength(); i++) {
                Model model = getModelFromXmlElement((Element) models.item(i));
                mark.getModels().add(model);
            }
        }
        return mark;
    }

    private Model getModelFromXmlElement(Element modelXml) throws Exception {

        String type = modelXml.getAttribute("type");
        if (ModelDiscriminator.isDiscriminatorExists(type)) {
            Model model = null;
            switch (ModelDiscriminator.valueOf(type)) {

                case PASSENGER: {
                    model = new PassengerModel();
                    PassengerModel passenger = (PassengerModel) model;
                    passenger.setNumberOfSeats(parseInt(getOnlyElementTextContent(modelXml, "numberOfSeats")));
                    passenger.setNumberOfAirbags(parseInt(getOnlyElementTextContent(modelXml, "numberOfAirbags")));
                    passenger.setAudioSystemName(getOnlyElementTextContent(modelXml, "audioSystemName"));
                    break;
                }
                case TRUCK: {
                    model = new TruckModel();
                    TruckModel truck = (TruckModel) model;
                    truck.setTankSize(parseInt(getOnlyElementTextContent(modelXml, "tankSize")));
                    truck.setEmbeddedKitchen("Y".equals(getOnlyElementTextContent(modelXml, "embeddedKitchen")));
                    truck.setWeight(parseInt(getOnlyElementTextContent(modelXml, "weight")));
                    break;
                }
            }

            model.setName(getOnlyElementTextContent(modelXml, "name"));
            model.setDescription(getOnlyElementTextContent(modelXml, "description"));

            String stringValue = getOnlyElementTextContent(modelXml, "productionYearStart");
            if (stringValue != null) {
                model.setProductionYearStart(parseInt(stringValue));
            }

            stringValue = getOnlyElementTextContentOrNull(modelXml, "productionYearEnd");
            if (stringValue != null) {
                model.setProductionYearEnd(parseInt(stringValue));
            }

            return model;
        } else {
            throw new InvalidModelDiscriminatorException(PARSE_MODEL_DISCRIMINATOR_ERROR.getCode(), PARSE_MODEL_DISCRIMINATOR_ERROR.getDescription());
        }
    }

}
