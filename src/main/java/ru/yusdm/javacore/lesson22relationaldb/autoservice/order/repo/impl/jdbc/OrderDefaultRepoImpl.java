package ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.search.OrderSearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class OrderDefaultRepoImpl implements OrderRepo {
    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return null;
    }

    @Override
    public int countByModel(long modelId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE MODEL_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, modelId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countByMark(long markId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE MARK_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, markId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteByUserId(long userId) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM ORDER_TAB WHERE USER_ID = ?",
                    ps -> {
                        ps.setLong(1, userId);
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<Order> findByUserId(long userId) {
        return null;
    }

    @Override
    public Order insert(Order order) {
        return null;
    }

    @Override
    public void insert(Collection<Order> items) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
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
    public List<Order> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }
}
