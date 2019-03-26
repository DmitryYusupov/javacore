package ru.yusdm.javacore.lesson14serialization.homework.jaxb.demo;

import ru.yusdm.javacore.lesson14serialization.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.InitData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

public class JaxbDemo {

    private static final String xmlDataPath = "/ru/yusdm/javacore/lesson14serialization/homework/jaxb/init-data.xml";

    public static void main(String[] args) {
        try {
            InitData dataFromXml = readDataFromXmlWithJaxb();
            List<Mark> marks = convertJaxbClassesToDomainClasses(dataFromXml);

            for (Mark mark : marks) {
                System.out.println(mark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static InitData readDataFromXmlWithJaxb() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(InitData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (InitData) jaxbUnmarshaller.unmarshal(JaxbDemo.class.getResourceAsStream(xmlDataPath));
    }

    private static List<Mark> convertJaxbClassesToDomainClasses(InitData initData) {
        List<Mark> result = new ArrayList<>();

        List<ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Mark> marks = initData.getMarks().getMark();
        for (ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Mark mark : marks) {
            result.add(convertJaxbMarkToDomainMark(mark));
        }
        return result;
    }

    private static Mark convertJaxbMarkToDomainMark(ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Mark jaxbMark) {
        Mark domain = new Mark();
        domain.setName(jaxbMark.getName());
        domain.setCountry(jaxbMark.getCountry());

        List<ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Model> models = jaxbMark.getModels().getModel();
        if (!models.isEmpty()) {
            domain.setModels(convertJaxbModelsToDomainModels(models));
        }

        return domain;
    }

    private static List<Model> convertJaxbModelsToDomainModels(List<ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Model> models) {
        List<Model> result = new ArrayList<>();

        for (ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Model model : models) {
            Model domainModel;
            if (ModelDiscriminator.PASSENGER.name().equals(model.getType())) {
                domainModel = toPassengerModel(model);
            } else {
                domainModel = toTruckModel(model);
            }
            fillCommonModelData(model, domainModel);
            result.add(domainModel);
        }

        return result;
    }

    private static PassengerModel toPassengerModel(ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Model model) {
        PassengerModel passenger = new PassengerModel();
        passenger.setNumberOfAirbags(model.getNumberOfAirbags() != null ? model.getNumberOfAirbags() : 0);
        passenger.setNumberOfSeats(model.getNumberOfSeats() != null ? model.getNumberOfSeats() : 0);
        passenger.setAudioSystemName(model.getAudioSystemName());
        return passenger;
    }

    private static TruckModel toTruckModel(ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Model model) {
        TruckModel truck = new TruckModel();
        truck.setWeight(model.getWeight() != null ? model.getWeight() : 0);
        truck.setEmbeddedKitchen("Y".equals(model.getEmbeddedKitchen()));
        truck.setTankSize(model.getTankSize() != null ? model.getTankSize() : 0);
        return truck;
    }

    private static void fillCommonModelData(ru.yusdm.javacore.lesson14serialization.homework.jaxb.generatedclasses.Model src,
                                            Model dest) {
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setProductionYearStart(src.getProductionYearStart());
        dest.setProductionYearEnd(src.getProductionYearEnd());
    }
}
