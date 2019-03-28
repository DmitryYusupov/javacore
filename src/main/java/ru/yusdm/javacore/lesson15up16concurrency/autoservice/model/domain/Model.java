package ru.yusdm.javacore.lesson15up16concurrency.autoservice.model.domain;

import ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.business.domain.BaseDomain;

public abstract class Model extends BaseDomain<Long> {
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
