package ru.yusdm.javacore.lesson10.genericscontinue;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.mark.domain.Mark;

/**
 * Created by Admin on 3/11/2019.
 */
public class MarkCrud implements BaseCrud<Mark, Long> {

    @Override
    public void save(Mark entity) {
    }

    @Override
    public Mark getEntityById(Long id) {
        return null;
    }
}
