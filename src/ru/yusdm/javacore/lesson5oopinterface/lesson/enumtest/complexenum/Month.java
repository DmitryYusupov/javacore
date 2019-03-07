package ru.yusdm.javacore.lesson5oopinterface.lesson.enumtest.complexenum;

public enum Month {
    JAN("This is cold month"),
    FEB("Blizzards"),
    NOV("Froze"),
    JULY("Warm"),
    AUGUST;

    private String description;

    Month(String description) {
        this.description = description;
    }

    Month() {
        this.description = "Other";
    }

    public String getDescription() {
        return description;
    }

    public static boolean isStrBelongsToEnumValues(String s){
        for (Month month :Month.values()){
            if (month.name().equals(s)){
                return true;
            }
        }

        return false;
    }
}
