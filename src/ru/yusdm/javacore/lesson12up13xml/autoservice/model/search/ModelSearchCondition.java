package ru.yusdm.javacore.lesson12up13xml.autoservice.model.search;

import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.search.BaseSearchCondition;
import ru.yusdm.javacore.lesson12up13xml.autoservice.model.domain.ModelDiscriminator;

public class ModelSearchCondition extends BaseSearchCondition<Long> {
    private ModelDiscriminator modelDiscriminator;

    public ModelDiscriminator getModelDiscriminator() {
        return modelDiscriminator;
    }

    public void setModelDiscriminator(ModelDiscriminator modelDiscriminator) {
        this.modelDiscriminator = modelDiscriminator;
    }
}
