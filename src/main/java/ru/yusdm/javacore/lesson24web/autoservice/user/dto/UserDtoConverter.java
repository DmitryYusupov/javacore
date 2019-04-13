package ru.yusdm.javacore.lesson24web.autoservice.user.dto;

import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class UserDtoConverter {
    private UserDtoConverter() {

    }

    public static UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setAge(user.getAge());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setClientType(user.getClientType());
        dto.setId(user.getId());
        return dto;
    }

    public static List<UserDto> convertToDtos(Collection<User> users) {
        return users.stream().map(UserDtoConverter::convertToDto).collect(Collectors.toList());
    }
}
