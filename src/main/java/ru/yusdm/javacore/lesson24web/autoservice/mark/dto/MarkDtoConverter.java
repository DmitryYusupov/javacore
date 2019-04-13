package ru.yusdm.javacore.lesson24web.autoservice.mark.dto;

import org.apache.commons.collections4.CollectionUtils;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.model.dto.ModelDtoConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class MarkDtoConverter {

    private MarkDtoConverter() {

    }

    public static MarkDto convertToDto(Mark mark) {
        MarkDto result = new MarkDto();
        result.setName(mark.getName());
        result.setCountry(mark.getCountry());

        if (CollectionUtils.isNotEmpty(mark.getModels())) {
            result.setModels(mark.getModels().stream().map(ModelDtoConverter::convertToDto).collect(toList()));
        } else {
            result.setModels(Collections.emptyList());
        }
        return result;
    }

    public static List<MarkDto> convertToDtos(Collection<Mark> marks) {
        return marks.stream().map(MarkDtoConverter::convertToDto).collect(toList());
    }
}
