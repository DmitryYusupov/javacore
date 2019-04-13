package ru.yusdm.javacore.lesson24web.autoservice.order.dto;

import ru.yusdm.javacore.lesson24web.autoservice.mark.dto.MarkDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.model.dto.ModelDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.user.dto.UserDtoConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class OrderDtoConverter {

    public static OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setMark(MarkDtoConverter.convertToDto(order.getMark()));
        orderDto.setModel(ModelDtoConverter.convertToDto(order.getModel()));
        orderDto.setUser(UserDtoConverter.convertToDto(order.getUser()));

        orderDto.setDescription(order.getDescription());
        orderDto.setPrice(order.getPrice());
        orderDto.setId(order.getId());
        return orderDto;
    }

    public static List<OrderDto> convertToDtos(Collection<Order> orders) {
        return orders.stream().map(OrderDtoConverter::convertToDto).collect(Collectors.toList());
    }
}
