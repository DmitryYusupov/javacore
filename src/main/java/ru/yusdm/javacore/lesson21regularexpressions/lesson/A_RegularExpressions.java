package ru.yusdm.javacore.lesson21regularexpressions.lesson;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

/**
 * Created by Admin on 4/8/2019.
 */
public class A_RegularExpressions {

    public static void main(String[] args) {
        //block 1--------------------
        //demoSimpleMatch();
        //demoCharacterClasses_1();
        // demoCharacterClasses_2();

        //block 2------------
        //demoPredefinedCharacterClasses();

        //block3----
        //once or not at all
        //demoQuantifier("a?", "aaaa");
        //demoQuantifier("(aa)?", "aaaa");
        //System.out.println();
        //zero or more time
        //demoQuantifier("a*", "aaaa");
        //System.out.println();
        //one or more times
        //demoQuantifier("a+", "aaaa");
        //demoQuantifier("(ivan)+","ivan_ivan_petr");
        //demoQuantifier("(ivan\\w{3})+","ivan123_ivan567_petr789");


        //block 3 -- --GREEDY ---
        //demo greedy
        //demoQuantifier(".*ivan","ivan_petr_ivan_fff_ivan2_tt");

        //demo lazy ()
        //demoQuantifier(".*?ivan","ivan_petr_ivan_fff_ivan2_tt");

        //Super greedy (Possessive)
        //demoQuantifier(".*+ivan", "ivan_petr_ivan_fff_ivan2_tt");
        //1 - ivan_petr_ivan_fff_ivan2_tt
        //2 - ivan_petr_ivan_fff_ivan2_tt_ivan
        //demoQuantifier(".*+", "ivan_petr_ivan_fff_ivan");

        //block 4---------
        //demoCaptureGroups();

        //block 5---------
        demoBoundaryRegExp();
    }

    private static void demoBoundaryRegExp(){
        String s1 = "private String name;";
        String s2 = "private String lastName;";
        String s3 = "private String lastName; ";

        List<String> data = Arrays.asList(s1, s2, s3);
        String regExp = "private (\\w+) (\\w+);$";
        Pattern pattern = Pattern.compile(regExp);

        data.forEach(s->{
            Matcher matcher = pattern.matcher(s);
            System.out.println(s + " matches RegExp: " + matcher.matches());
        });

        //[a-z0-9]+@{1}[a-z0-9]+\.{1}[a-z]{2,3}
        //usikovich@mail.rud
    }

    private static void demoCaptureGroups() {
        String s1 = "private String name;";
        String s2 = "private String lastName;";
        /**
         * this.name = other.name;
         * this.lastName = other.lastName;
         */
        List<String> data = Arrays.asList(s1, s2);
        String regExp = "private (\\w+) (\\w+);";
        Pattern pattern = Pattern.compile(regExp);

        StringBuilder sb = new StringBuilder();
        data.forEach(s -> {
            Matcher matcher = pattern.matcher(s);

            if (matcher.find() && matcher.groupCount() == 2) {
                IntStream.range(1, 1 + matcher.groupCount())
                        .limit(1)
                        .forEach(i -> {
                            sb.append("this.")
                                    .append(matcher.group(2))
                                    .append(" = ")
                                    .append("other.").append(matcher.group(2))
                                    .append(";\n");
                        });
                System.out.println(sb.toString());
            }
        });
    }


    private static void demoQuantifier(String regExp, String toMatch) {
        demoRegularExpression(regExp, toMatch);
    }

    private static void demoRegularExpression(String regExp, List<String> toMatch) {
        Pattern pattern = Pattern.compile(regExp);

        for (String s : toMatch) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                System.out.println(s + " matches " + regExp);
            }
        }
    }

    private static void demoSimpleMatch() {
        demoRegularExpression("ivan", asList("ivan", "fedor", "test"));
    }

    private static void demoCharacterClasses_1() {
        demoRegularExpression("[a-zA-Z]", asList("i", "a", "test2"));
    }

    private static void demoCharacterClasses_2() {
        demoRegularExpression("[^abc]", asList("a", "b", "c", "d", "ss"));
    }

    private static void demoPredefinedCharacterClasses() {
        System.out.println("---Check digital--------");
        //demoRegularExpression("\\d", asList("1", "2", "ss", "3"));

        System.out.println("---Check white spaces----");
        //demoRegularExpression("\\s", asList(" ", "2", " ", "3"));

        System.out.println("---Check word----");
        demoRegularExpression("\\w", asList("^", ":", "a", "3", "_", "%"));

        System.out.println("---Check NONE word----");
        demoRegularExpression("\\W", asList("^", ":", "a", "3", "_", "%"));

        System.out.println("---Check Point----");
        demoRegularExpression("\\.", asList(".", ":", "a", "3", "_", "%"));

        System.out.println("---Check Any----");
        demoRegularExpression(".", asList(".", ":", "a", "3", "_", "%"));
    }

    private static void demoRegularExpression(String regExp, String toMatch) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(toMatch);

        boolean found = false;

        while (matcher.find()) {
            System.out.printf("I found the text \"%s\" starting at index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start(),
                    matcher.end());
        }
    }


}
