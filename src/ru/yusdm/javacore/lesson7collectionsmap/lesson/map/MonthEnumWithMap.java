package ru.yusdm.javacore.lesson7collectionsmap.lesson.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2/27/2019.
 */
public enum MonthEnumWithMap {
    JAN("This is cold MonthEnumWithMap"),
    FEB("Blizzards"),
    NOV("Froze"),
    JULY("Warm"),
    AUGUST;

    private static Map<String, MonthEnumWithMap> strNameEnumItemMap;

    static {
        strNameEnumItemMap = new HashMap<>();
        for (MonthEnumWithMap enumItem : MonthEnumWithMap.values()) {
            strNameEnumItemMap.put(enumItem.getDescription(), enumItem);
        }
    }

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


    public static boolean isStrBelongsToEnumValues(String enumItemAsStr) {
        return strNameEnumItemMap.containsKey(enumItemAsStr);
    }
}
