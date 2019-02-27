package ru.yusdm.javacore.lesson7collectionsmap.map;

/**
 * Created by Admin on 2/27/2019.
 */
public enum MonthEnumWithMap {
    JAN("This is cold MonthEnumWithMap"),
    FEB("Blizzards"),
    NOV("Froze"),
    JULY("Warm"),
    AUGUST;

    private String description;

    MonthEnumWithMap(String description) {
        this.description = description;
    }

    MonthEnumWithMap() {
        this.description = "Other";
    }

    public String getDescription() {
        return description;
    }

    public static boolean isStrBelongsToEnumValues(String s) {
        for (MonthEnumWithMap MonthEnumWithMap :
                MonthEnumWithMap.values()) {
            if (MonthEnumWithMap.name().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
