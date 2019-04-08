package ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.initor.datasourcereader;


import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.parser.FileParser;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.initor.exception.cheked.InvalidModelDiscriminatorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.initor.exception.InitDataExceptionMeta.PARSE_MODEL_DISCRIMINATOR_ERROR;

/**
 * Class read TXT file and parse it. As result it extract marks and models.
 * <p>
 * Example of file's content:
 * Mark: Toyota | Japan
 * PASSENGER |Land cruiser 200   | Big like a gym | 1990 | -1 | 4 | 8 | Sony
 * <p>
 * Mark: Ural | Russia
 * TRUCK | 53125 | Power yeaah | 1970 | -1  | 10000 | N | 300
 */
public class MarksWithModelsTxtFileParser implements FileParser<List<Mark>> {

    private static final String MARK_PLACEHOLDER = "Mark:";

    @Override
    public List<Mark> parseFile(String file) throws Exception {
        List<String> fileAsList = readFileToList(file);

        List<Mark> result = new ArrayList<>();
        if (!fileAsList.isEmpty()) {
            List<List<String>> marksWithModels = splitFileToSeparateMarksWithModels(fileAsList);

            for (List<String> markWithModels : marksWithModels) {
                result.add(parseMark(markWithModels));
            }
        }

        return result;
    }

    private List<String> readFileToList(String file) throws IOException {
        List<String> fileAsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileAsList.add(line);
            }
        }

        return fileAsList;
    }

    private List<List<String>> splitFileToSeparateMarksWithModels(List<String> fileAsList) {
        List<List<String>> marksWithModels = new ArrayList<>();

        List<String> singlMarkWithModels = null;
        for (String dataFromFile : fileAsList) {
            if (!dataFromFile.isEmpty()) {

                //check if mark begin
                if (dataFromFile.contains(MARK_PLACEHOLDER)) {
                    if (singlMarkWithModels != null && !singlMarkWithModels.isEmpty()) {
                        marksWithModels.add(singlMarkWithModels);
                    }
                    singlMarkWithModels = new ArrayList<>();
                    singlMarkWithModels.add(dataFromFile);
                } else if (singlMarkWithModels != null) {
                    singlMarkWithModels.add(dataFromFile);
                }

            }
        }

        return marksWithModels;
    }

    private Mark parseMark(List<String> markWithModels) throws InvalidModelDiscriminatorException {
        String markAsStr = markWithModels.get(0).replaceAll(MARK_PLACEHOLDER, "");
        markWithModels.remove(0);

        String[] modelCsv = markWithModels.toArray(new String[0]);
        return getMark(markAsStr, modelCsv);
    }

    private Mark getMark(String markCsv, String[] modelsCsv) throws InvalidModelDiscriminatorException {
        String[] attrs = markCsv.split("\\|");
        int attrIndex = -1;

        Mark mark = new Mark(attrs[++attrIndex].trim(), attrs[++attrIndex].trim());
        mark.setModels(new ArrayList<>());

        for (int i = 0; i < modelsCsv.length; i++) {
            String csvModel = modelsCsv[i];
            attrIndex = -1;
            attrs = csvModel.split("\\|");

            String discriminatorAsStr = attrs[++attrIndex].trim();
            Model model = createModelByDiscriminator(discriminatorAsStr);
            model.setName(attrs[++attrIndex].trim());
            model.setDescription(attrs[++attrIndex].trim());
            model.setProductionYearStart(Integer.parseInt(attrs[++attrIndex].trim()));
            int productionYearEnd = Integer.parseInt(attrs[++attrIndex].trim());
            model.setProductionYearEnd(productionYearEnd == -1 ? null : productionYearEnd);

            if (PassengerModel.class.equals(model.getClass())) {
                appendPassengerAttributes((PassengerModel) model, attrs, attrIndex);
            } else if (TruckModel.class.equals(model.getClass())) {
                appendTruckAttributes((TruckModel) model, attrs, attrIndex);
            }

            mark.getModels().add(model);
        }

        return mark;
    }

    private Model createModelByDiscriminator(String discriminatorAsStr) throws InvalidModelDiscriminatorException {
        return ModelDiscriminator.getDiscriminatorByName(discriminatorAsStr)
                .map(modelDiscriminator -> {
                            if (ModelDiscriminator.TRUCK.equals(modelDiscriminator)) {
                                return new TruckModel();
                            }
                            return new PassengerModel();
                        }
                )
                .orElseThrow(() -> new InvalidModelDiscriminatorException(
                        PARSE_MODEL_DISCRIMINATOR_ERROR.getCode(),
                        PARSE_MODEL_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(discriminatorAsStr)));
    }

    private void appendPassengerAttributes(PassengerModel model, String[] attrs, int attrIndex) {
        model.setNumberOfAirbags(Integer.parseInt(attrs[++attrIndex].trim()));
        model.setNumberOfSeats(Integer.parseInt(attrs[++attrIndex].trim()));
        model.setAudioSystemName(attrs[++attrIndex].trim());
    }

    private void appendTruckAttributes(TruckModel model, String[] attrs, int attrIndex) {
        model.setWeight(Integer.parseInt(attrs[++attrIndex].trim()));
        model.setEmbeddedKitchen("Y".equals(attrs[++attrIndex].trim()));
        model.setTankSize(Integer.parseInt(attrs[++attrIndex].trim()));
    }
}