package ru.yusdm.javacore.lesson5oopinterface.enumtest.complexenum;

/**
 * Created by Admin on 2/22/2019.
 */
public class MonthDemo {

    public static void main(String[] args) {
        for (Month month : Month.values()) {
            System.out.println(month.getDescription());
        }

        String anyStr = "JAN";
        if (Month.isStrBelongsToEnumValues(anyStr)) {
            Month month = Month.valueOf(anyStr);
            System.out.println(month);
        }
    }

    /**
     * Enum
     *   ARCTIC,
     CONTINENTAL,
     MELT,
     SUB_TROPIC
     *
     * 1 | Russia | ARCTIC
     * 2 | CAMEROON | SUB_TROPIC
     * 3 | Germany | CONTINENTAL
     *
     *
     * Rename SUB_TROPIC -> SUBTROPIC
     *
     * On disk remains old value -'SUB_TROPIC'
     *
     * I try to read data from disk and to convert
     * them into java objects
     *
     * Climate.valueOf(String), but we
     * have new enum values:
     *
     * SUBTROPIC
     *
     * Which will cause
     * IllegalArgumentException: No enum constant
     */

}
