package ru.yusdm.javacore.lesson11io.autoservice.mark.service;

import ru.yusdm.javacore.lesson11io.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson11io.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson11io.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkService extends BaseService<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

}
