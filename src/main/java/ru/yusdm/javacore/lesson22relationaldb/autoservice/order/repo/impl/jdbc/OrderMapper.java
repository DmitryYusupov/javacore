package ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.ResultSetMappingException;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.domain.User;

import java.sql.ResultSet;

public final class OrderMapper {

    private static final String ORDER_CLASS_NAME = Order.class.getSimpleName();

    private OrderMapper() {

    }

    public static Order mapOrder(ResultSet rs) throws ResultSetMappingException {
        try {
            Order order = new Order();
            order.setId(rs.getLong("ID"));
            order.setMarkId(rs.getLong("MARK_ID"));
            order.setModelId(rs.getLong("MODEL_ID"));
            order.setUserId(rs.getLong("USER_ID"));
            order.setPrice(rs.getInt("PRICE"));
            order.setDescription(rs.getString("DESCRIPTION"));
            return order;
        } catch (Exception e) {
            throw new ResultSetMappingException(ORDER_CLASS_NAME, e);
        }
    }

}
