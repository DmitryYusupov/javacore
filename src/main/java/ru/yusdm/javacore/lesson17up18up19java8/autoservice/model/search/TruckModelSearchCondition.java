package ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.search;

public class TruckModelSearchCondition extends ModelSearchCondition {
    private Integer weight;
    private Boolean embeddedKitchen;
    private Integer tankSize;

    public boolean searchByWeight() {
        return weight != null;
    }

    public boolean searchByEmbeddedKitchen() {
        return embeddedKitchen != null;
    }

    public boolean searchByTankSize() {
        return tankSize != null;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getEmbeddedKitchen() {
        return embeddedKitchen;
    }

    public void setEmbeddedKitchen(Boolean embeddedKitchen) {
        this.embeddedKitchen = embeddedKitchen;
    }

    public Integer getTankSize() {
        return tankSize;
    }

    public void setTankSize(Integer tankSize) {
        this.tankSize = tankSize;
    }
}
