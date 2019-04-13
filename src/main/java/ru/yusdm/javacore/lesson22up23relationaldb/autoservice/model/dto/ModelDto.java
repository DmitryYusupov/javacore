package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.model.dto;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.dto.BaseDto;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.model.domain.ModelDiscriminator;

public abstract class ModelDto extends BaseDto<Long> {

    protected String name;
    protected String description;
    protected int productionYearStart;
    protected Integer productionYearEnd;
    protected ModelDiscriminator discriminator;

    public ModelDto() {
        initDiscriminator();
    }

    protected abstract void initDiscriminator();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductionYearStart() {
        return productionYearStart;
    }

    public void setProductionYearStart(int productionYearStart) {
        this.productionYearStart = productionYearStart;
    }

    public Integer getProductionYearEnd() {
        return productionYearEnd;
    }

    public void setProductionYearEnd(Integer productionYearEnd) {
        this.productionYearEnd = productionYearEnd;
    }

    public ModelDiscriminator getDiscriminator() {
        return discriminator;
    }

}
