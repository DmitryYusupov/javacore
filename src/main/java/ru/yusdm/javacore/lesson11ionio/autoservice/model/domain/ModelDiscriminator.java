package ru.yusdm.javacore.lesson11ionio.autoservice.model.domain;

import java.util.HashMap;
import java.util.Map;

public enum ModelDiscriminator {
    PASSENGER, TRUCK;

    static Map<String, ModelDiscriminator> stringModelDiscriminatorMap = new HashMap<>();

    static {
        for (ModelDiscriminator discriminator : ModelDiscriminator.values()) {
            stringModelDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static ModelDiscriminator getDiscriminatorByName(String discriminatorName) {
        return stringModelDiscriminatorMap.get(discriminatorName);
    }

    public static boolean isDiscriminatorExists(String discriminator) {
        return getDiscriminatorByName(discriminator) != null;
    }

    public static boolean isDiscriminatorNotExists(String discriminator) {
        return !isDiscriminatorExists(discriminator);
    }


}
