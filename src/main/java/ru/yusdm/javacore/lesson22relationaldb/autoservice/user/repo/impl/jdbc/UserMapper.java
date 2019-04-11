package ru.yusdm.javacore.lesson22relationaldb.autoservice.user.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.ResultSetMappingException;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.domain.ClientType;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.exception.unchecked.UnknownUserClientTypeException;

import java.sql.ResultSet;
import java.util.Optional;

public final class UserMapper {
    private static final String USER_CLASS_NAME = User.class.getSimpleName();

    private UserMapper() {

    }

    public static User mapUser(ResultSet rs) throws ResultSetMappingException {
        try {

            String clientType = rs.getString("CLIENT_TYPE");
            Optional<ClientType> clientTypeByStrValue = ClientType.getClientTypeByStrValue(clientType);

            if (!clientTypeByStrValue.isPresent()) {
                throw new UnknownUserClientTypeException(clientType);
            }

            User user = new User();
            user.setId(rs.getLong("ID"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setFirstName(rs.getString("FIRST_NAME"));


            int age = rs.getInt("AGE");
            if (!rs.wasNull()) {
                user.setAge(age);
            }

            return user;
        } catch (UnknownUserClientTypeException e) {
            throw e;
        } catch (Exception e) {
            throw new ResultSetMappingException(USER_CLASS_NAME, e);
        }
    }

}
