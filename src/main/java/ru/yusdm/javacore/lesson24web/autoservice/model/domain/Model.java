package ru.yusdm.javacore.lesson24web.autoservice.model.domain;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.domain.BaseDomain;

public abstract class Model extends BaseDomain<Long> {
    protected Long markId;
    protected String name;
    protected String description;
    protected int productionYearStart;
    protected Integer productionYearEnd;
    protected ModelDiscriminator discriminator;

    public Model() {
        initDiscriminator();
    }

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

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    protected abstract void initDiscriminator();

    @Override
    public String toString() {
        return
                 discriminator +
                " id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productionYearStart=" + productionYearStart +
                ", productionYearEnd=" + productionYearEnd
                ;
    }
}
