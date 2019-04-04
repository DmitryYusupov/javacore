package ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.initor.datasourcereader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.parser.FileParser;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.initor.exception.cheked.InvalidModelDiscriminatorException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.xml.dom.XmlDomUtils.*;
import static ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.initor.exception.InitDataExceptionMeta.PARSE_MODEL_DISCRIMINATOR_ERROR;

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

        mark.setName(getOnlyElementTextContent((Element) xmlMark, "name"));
        mark.setCountry(getOnlyElementTextContent((Element) xmlMark, "country"));

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
        return ModelDiscriminator.getDiscriminatorByName(modelXml.getAttribute("type"))
                .map(modelDiscriminator -> createModelByDiscriminatorAndXmlElement(modelDiscriminator, modelXml))
                .orElseThrow(() -> new InvalidModelDiscriminatorException(PARSE_MODEL_DISCRIMINATOR_ERROR.getCode(),
                        PARSE_MODEL_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(modelXml.getAttribute("type"))));
    }

    private Model createModelByDiscriminatorAndXmlElement(ModelDiscriminator modelDiscriminator, Element modelXml) {
        Model model = null;
        switch (modelDiscriminator) {
            case PASSENGER: {
                model = new PassengerModel();
                fillPassengerModel((PassengerModel) model, modelXml);
                break;
            }
            case TRUCK: {
                model = new TruckModel();
                fillTruckModel((TruckModel) model, modelXml);
                break;
            }
        }
        fillCommonModelData(model, modelXml);
        return model;
    }

    private void fillPassengerModel(PassengerModel passenger, Element modelXml) {
        passenger.setNumberOfSeats(parseInt(getOnlyElementTextContent(modelXml, "numberOfSeats")));
        passenger.setNumberOfAirbags(parseInt(getOnlyElementTextContent(modelXml, "numberOfAirbags")));
        passenger.setAudioSystemName(getOnlyElementTextContent(modelXml, "audioSystemName"));
    }

    private void fillTruckModel(TruckModel truck, Element modelXml) {
        truck.setTankSize(parseInt(getOnlyElementTextContent(modelXml, "tankSize")));
        truck.setEmbeddedKitchen("Y".equals(getOnlyElementTextContent(modelXml, "embeddedKitchen")));
        truck.setWeight(parseInt(getOnlyElementTextContent(modelXml, "weight")));
    }

    private void fillCommonModelData(Model model, Element modelXml) {
        model.setName(getOnlyElementTextContent(modelXml, "name"));
        model.setDescription(getOnlyElementTextContent(modelXml, "description"));

        String stringValue = getOnlyElementTextContent(modelXml, "productionYearStart");
        model.setProductionYearStart(parseInt(stringValue));

        stringValue = getOnlyElementTextContentOrNull(modelXml, "productionYearEnd");
        if (stringValue != null) {
            model.setProductionYearEnd(parseInt(stringValue));
        }

    }
}
