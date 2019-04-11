package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.ResultSetMappingException;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;

import java.sql.ResultSet;

public final class MarkMapper {

    private static final String MARK_CLASS_NAME = Mark.class.getSimpleName();

    private MarkMapper() {
    }

    public static Mark mapMark(ResultSet rs) throws ResultSetMappingException {
        try {
            Mark mark = new Mark();
            mark.setId(rs.getLong("ID"));
            mark.setName(rs.getString("NAME"));
            mark.setCountry(rs.getString("COUNTRY"));

            return mark;
        } catch (Exception e) {
            throw new ResultSetMappingException(MARK_CLASS_NAME, e);
        }
    }
}
