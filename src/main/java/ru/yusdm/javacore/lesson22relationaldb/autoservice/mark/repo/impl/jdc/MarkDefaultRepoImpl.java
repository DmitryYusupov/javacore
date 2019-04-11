package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.impl.jdc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.KeyGenerationError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.search.MarkSearchCondition;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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
                        appendPreparedStatementParamsForMark(new AtomicInteger(0), ps, mark);
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

    private void appendPreparedStatementParamsForMark(AtomicInteger index, PreparedStatement ps, Mark mark) throws SQLException {
        ps.setString(index.incrementAndGet(), mark.getName());
        ps.setString(index.incrementAndGet(), mark.getCountry());
    }

    @Override
    public void insert(Collection<Mark> marks) {
    }

    @Override
    public void update(Mark mark) {
        String sql = "UPDATE MARK SET " +
                "NAME = ?," +
                "COUNTRY = ?, " +
                "WHERE ID = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger();
                        appendPreparedStatementParamsForMark(index, ps, mark);
                        ps.setLong(index.incrementAndGet(), mark.getId());
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<Mark> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM MARK WHERE ID = ?", ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
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
        } catch (Exception e) {
            throw new SqlError(e);
        }

    }

}
