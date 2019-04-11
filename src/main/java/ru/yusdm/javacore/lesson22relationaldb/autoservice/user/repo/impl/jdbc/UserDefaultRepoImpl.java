package ru.yusdm.javacore.lesson22relationaldb.autoservice.user.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.KeyGenerationError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.search.UserSearchCondition;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDefaultRepoImpl implements UserRepo {
    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        return null;
    }

    @Override
    public User insert(User user) {
        String sql = "INSERT INTO USER (" +
                "FIRST_NAME," +
                "LAST_NAME," +
                "AGE," +
                "CLIENT_TYPE" +
                ")" +
                "VALUES (?, ? ,? ,?)";
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(sql,
                    ps -> {
                        appendPreparedStatementParamsToUser(new AtomicInteger(0), ps, user);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                user.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return user;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private void appendPreparedStatementParamsToUser(AtomicInteger index, PreparedStatement ps, User user) throws SQLException {
        ps.setString(index.incrementAndGet(), user.getFirstName());
        ps.setString(index.incrementAndGet(), user.getLastName());
        ps.setInt(index.incrementAndGet(), user.getAge());
        ps.setString(index.incrementAndGet(), user.getClientType().toString());
    }

    @Override
    public void insert(Collection<User> users) {

    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USER SET " +
                "FIRST NAME = ?," +
                "LAST_NAME = ?, " +
                "AGE = ?, " +
                "CLIENT_TYPE = ? " +
                "WHERE ID = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger(0);
                        appendPreparedStatementParamsToUser(index, ps, user);
                        ps.setLong(index.incrementAndGet(), user.getId());
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return QueryWrapper.selectOne("SELECT * FROM USER WHERE ID = ?", UserMapper::mapUser);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM USER WHERE ID = ?", ps -> {
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
    public List<User> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM USER", UserMapper::mapUser);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM USER",
                    rs -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }

    }
}
