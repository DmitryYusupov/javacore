package ru.yusdm.javacore.lesson4oopinheritence.autoservice.mark.service;

import ru.yusdm.javacore.lesson4oopinheritence.autoservice.mark.Mark;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.mark.repo.MarkMemoryRepo;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.Model;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.repo.ModelMemoryRepo;

public class MarkMemoryService {

    private MarkMemoryRepo markRepo = new MarkMemoryRepo();
    private ModelMemoryRepo modelRepo = new ModelMemoryRepo();

    public void addMark(Mark mark) {
        markRepo.addMark(mark);
        for (Model model : mark.getModels()) {
            modelRepo.addModel(model);
        }
    }

    public Mark findMarkById(long id) {
        return markRepo.findMarkById(id);
    }

    public void deleteMark(Mark mark) {
        markRepo.deleteMark(mark);
    }

    public void deleteMark(Long id) {
        markRepo.deleteMark(id);
    }

    public void printMarks() {
        markRepo.printMarks();
    }

}
