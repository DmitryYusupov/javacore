package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.impl;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.KeyGenerationError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.search.MarkSearchCondition;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MarkDefaultRepoImpl implements MarkRepo {

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        return null;
    }

    @Override
    public Mark insert(Mark mark) {
        String sql = "INSERT INTO MARK (" +
                "NAME," +
                "COUNTRY" +
                ")" +
                "VALUES (?, ?)";

        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(sql,
                    ps -> {
                        appendPreparedStatementParamsForMark(ps, mark);
                        return ps;
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                mark.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return mark;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private void appendPreparedStatementParamsForMark(PreparedStatement ps, Mark mark) throws SQLException {
        int index = 0;
        ps.setString(++index, mark.getName());
        ps.setString(++index, mark.getCountry());
    }

    @Override
    public void insert(Collection<Mark> marks) {
    }

    @Override
    public void update(Mark mark) {

    }

    @Override
    public Optional<Mark> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void printAll() {
        findAll().forEach(System.out::println);
    }

    @Override
    public List<Mark> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM MARK",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (SQLException e) {
            throw new SqlError(e);
        }

    }


}
