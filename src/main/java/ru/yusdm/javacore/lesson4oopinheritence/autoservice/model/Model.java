package ru.yusdm.javacore.lesson4oopinheritence.autoservice.model;

public class Model {
    private Long id;
    private String name;
    private String description;
    private int productionYearStart;
    private Integer productionYearEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productionYearStart=" + productionYearStart +
                ", productionYearEnd=" + productionYearEnd +
                '}';
    }
}
