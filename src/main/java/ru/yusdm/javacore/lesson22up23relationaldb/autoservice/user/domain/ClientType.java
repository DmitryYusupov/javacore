package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ClientType {
    VIP("Very important person"),
    NEW("First time visited our service"),
    BAD("Bad behaviour"),
    ORDINARY("Just another client"),
    IN_THE_BLACK_LIST("Ignore him");

    private String description;

    private static Map<String, ClientType> strNameEnumItemMap;

    static {
        strNameEnumItemMap = new HashMap<>();
        for (ClientType enumItem : ClientType.values()) {
            strNameEnumItemMap.put(enumItem.name(), enumItem);
        }
    }

    public static Optional<ClientType> getClientTypeByStrValue(String clientTypeStr) {
        return Optional.ofNullable(strNameEnumItemMap.get(clientTypeStr));
    }

    ClientType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
