package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.model.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ModelDiscriminator {
    PASSENGER, TRUCK;

    static Map<String, ModelDiscriminator> stringModelDiscriminatorMap = new HashMap<>();

    static {
        for (ModelDiscriminator discriminator : ModelDiscriminator.values()) {
            stringModelDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static Optional<ModelDiscriminator> getDiscriminatorByName(String discriminatorName) {
        return Optional.ofNullable(stringModelDiscriminatorMap.get(discriminatorName));
    }


}
