package ru.yusdm.javacore.lesson24web.autoservice.model.dto;

import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.TruckModel;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ModelDtoConverter {
    private ModelDtoConverter() {

    }

    public static ModelDto convertToDto(Model model) {
        ModelDto modelDto;
        if (ModelDiscriminator.PASSENGER.equals(model.getDiscriminator())) {
            PassengerModel pm = (PassengerModel) model;
            PassengerModelDto dto = new PassengerModelDto();
            dto.setNumberOfAirbags(pm.getNumberOfAirbags());
            dto.setNumberOfSeats(pm.getNumberOfSeats());
            dto.setAudioSystemName(pm.getAudioSystemName());

            modelDto = dto;
        } else {
            TruckModel tm = (TruckModel) model;
            TruckModelDto dto = new TruckModelDto();
            dto.setWeight(tm.getWeight());
            dto.setTankSize(tm.getTankSize());
            dto.setEmbeddedKitchen(tm.isEmbeddedKitchen());

            modelDto = dto;
        }

        modelDto.setId(model.getId());
        modelDto.setDescription(model.getDescription());
        modelDto.setName(model.getName());
        modelDto.setProductionYearStart(model.getProductionYearStart());
        modelDto.setProductionYearEnd(model.getProductionYearEnd());

        return modelDto;
    }


    public static List<ModelDto> convertToDtos(Collection<Model> models) {
        return models.stream().map(ModelDtoConverter::convertToDto).collect(Collectors.toList());
    }

}
