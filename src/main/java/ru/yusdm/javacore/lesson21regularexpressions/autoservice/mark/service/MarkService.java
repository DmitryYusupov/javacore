package ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.service;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkService extends BaseService<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

    void removeAllModelsFromMark(Long markId) throws AutoServiceUncheckedException;
}
