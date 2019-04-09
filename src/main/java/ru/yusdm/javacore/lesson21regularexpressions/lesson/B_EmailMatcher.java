package ru.yusdm.javacore.lesson21regularexpressions.lesson;

import java.util.regex.Pattern;

/**
 * Created by Admin on 4/8/2019.
 */
public class B_EmailMatcher {
    private static final String REG_EXP = "[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,3}";
    private static final Pattern PATTERN = Pattern.compile(REG_EXP);

    public static boolean match(String test) {
        return PATTERN.matcher(test).matches();
    }
}
