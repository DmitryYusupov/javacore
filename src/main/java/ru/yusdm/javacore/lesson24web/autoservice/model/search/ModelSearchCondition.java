package ru.yusdm.javacore.lesson24web.autoservice.model.search;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.search.BaseSearchCondition;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.ModelDiscriminator;

public class ModelSearchCondition extends BaseSearchCondition<Long> {
    private ModelDiscriminator modelDiscriminator;
    public ModelDiscriminator getModelDiscriminator() {
        return modelDiscriminator;
    }

    public void setModelDiscriminator(ModelDiscriminator modelDiscriminator) {
        this.modelDiscriminator = modelDiscriminator;
    }
}
