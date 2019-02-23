package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo.impl.MarkMemoryRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.impl.ModelMemoryRepo;

public class MarkDefaultService implements MarkService {

    private final MarkRepo markRepo;
    private final ModelRepo modelRepo;

    public MarkDefaultService(MarkRepo markRepo, ModelRepo modelRepo) {
        this.markRepo = markRepo;
        this.modelRepo = modelRepo;
    }

    @Override
    public void add(Mark mark) {
        markRepo.add(mark);

        if (mark.getModels() != null) {
            for (Model model : mark.getModels()) {
                modelRepo.add(model);
            }
        }
    }

    @Override
    public Mark findById(long id) {
        return markRepo.findById(id);
    }

    @Override
    public void delete(Mark mark) {
        markRepo.delete(mark);
    }

    @Override
    public void deleteById(Long id) {
        markRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        markRepo.printAll();
    }

}
