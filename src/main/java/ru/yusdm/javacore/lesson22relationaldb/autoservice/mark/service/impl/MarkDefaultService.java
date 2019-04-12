package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.service.impl;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.ResultSetToExtractor;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.exception.unchecked.DeleteMarkException;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.OrderRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.exception.MarkExceptionMeta.DELETE_MARK_CONSTRAINT_ERROR;

public class MarkDefaultService implements MarkService {

    private final MarkRepo markRepo;
    private final ModelService modelService;
    private final OrderRepo orderRepo;

    public MarkDefaultService(MarkRepo markRepo, ModelService modelService, OrderRepo orderRepo) {
        this.markRepo = markRepo;
        this.modelService = modelService;
        this.orderRepo = orderRepo;
    }

    @Override
    public Mark insert(Mark mark) {
        if (mark != null) {
            markRepo.insert(mark);

            if (isNotEmpty(mark.getModels())) {
                mark.getModels().forEach(model -> {
                    model.setMarkId(mark.getId());
                    modelService.insert(model);
                });
            }
        }

        return mark;
    }

    @Override
    public void insert(Collection<Mark> marks) {
        if (marks != null && !marks.isEmpty()) {

            for (Mark mark : marks) {
                markRepo.insert(mark);

                if (isNotEmpty(mark.getModels())) {
                    mark.getModels().replaceAll(model -> {
                        model.setMarkId(mark.getId());
                        return model;
                    });
                    modelService.insert(mark.getModels());
                }
            }
        }
    }

    @Override
    public Optional<Mark> findById(Long id) {
        if (id != null) {
            return markRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Mark mark) {
        if (mark.getId() != null) {
            this.deleteById(mark.getId());
        }
    }

    @Override
    public void deleteById(Long id) throws AutoServiceUncheckedException {
        if (id != null) {
            boolean noOrders = orderRepo.countByMark(id) == 0;

            if (noOrders) {
                removeAllModelsFromMark(id);
                markRepo.deleteById(id);
            } else {
                throw new DeleteMarkException(DELETE_MARK_CONSTRAINT_ERROR);
            }
        }
    }

    @Override
    public void printAll() {
        markRepo.printAll();
    }


    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return markRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            return markRepo.search(searchCondition);
        }
    }

    @Override
    public void update(Mark mark) {
        if (mark.getId() != null) {
            markRepo.update(mark);
        }
    }

    @Override
    public void removeAllModelsFromMark(Long markId) throws AutoServiceUncheckedException {
        findById(markId).ifPresent(mark -> {
            if (mark.getModels() != null) {
                mark.getModels().forEach(model -> modelService.deleteById(model.getId()));
            }
        });
    }

    @Override
    public List<Mark> findAll() {
        return markRepo.findAll();
    }

    @Override
    public int countAll() {
        return markRepo.countAll();
    }

    /**
     * Mark -> Model ( ID-> MARK_ID )
     */
    @Override
    public List<Mark> findAllMarksFetchingModels() {
        try {
            String sql =
                    "SELECT \n" +
                            " mk.ID as MARK_IDENT, \n" +
                            " md.ID as MODEL_IDENT, \n" +
                            " mk.NAME as MARK_NAME, \n" +
                            " md.NAME as MODEL_NAME, \n" +
                            " mk.*, md.* \n" +
                            " \n" +
                            " FROM MARK mk \n" +
                            " LEFT JOIN MODEL md ON (mk.ID = md.MARK_ID)";
            /**
             *  1 | Vaz | 1 | 2106
             *  1 | Vaz | 2 | 21099
             *
             *  2 | BMW | 3 | X5
             *  2 | BMW | 4 | X3
             *
             *  3 | CAT | NULL | NULL
             */
            List<Mark> select = QueryWrapper.select(sql, new ResultSetToExtractor<Mark>() {
                @Override
                public void extract(ResultSet rs, List<Mark> accomulator) throws SQLException {

                    Map<Long, Mark> marksMap = new LinkedHashMap<>();

                    while (rs.next()) {
                        long markId = rs.getLong("MARK_IDENT");

                        if (!marksMap.containsKey(markId)) {
                            Mark mark = new Mark();
                            mark.setId(markId);
                            mark.setName(rs.getString("MARK_NAME"));
                            marksMap.put(markId, mark);

                            accomulator.add(mark);
                        }

                        Mark mark = marksMap.get(markId);

                        String discrStr = rs.getString("DISCRIMINATOR");
                        if (discrStr != null) {
                            ModelDiscriminator discriminator = ModelDiscriminator.valueOf(discrStr);

                            Model m = null;
                            if (ModelDiscriminator.PASSENGER.equals(discriminator)) {
                                PassengerModel model = new PassengerModel();
                                model.setNumberOfAirbags(rs.getInt("NUMBER_OF_AIR_BAGS"));
                                m = model;
                            } else {
                                TruckModel truckModel = new TruckModel();
                                truckModel.setTankSize(rs.getInt("TANK_SIZE"));
                                m = truckModel;
                            }

                            if (mark.getModels() == null) {
                                mark.setModels(new ArrayList<>());
                            }
                            mark.getModels().add(m);
                        }
                    }
                }
            });

            return select;

        } catch (Exception e) {
            throw new SqlError(e);
        }
    }
}
