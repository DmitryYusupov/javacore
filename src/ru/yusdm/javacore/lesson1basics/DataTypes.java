package ru.yusdm.javacore.lesson1basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataTypes {

    public static void main(String args[]) throws Exception {
        // demoPrimitives();
        //  demoNumeric();
        //  demoLogical();
        //  demoDateTime();
        //  demoString();
        //demoStringOperations();
        //  demoLogical();
        // moreDemoLogical();
        // demoChars();
        // demoDate();
       // demoCalendar();

        demoBoxUnbox();
    }

    private static void demoPrimitives() {
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println("a + b = " + c);

        double d = (double) 5 / 2;
        System.out.println("(double) 5 / 2 = " + d);

        d = 5 / 2;
        System.out.println("5 / 2 = " + d);

        char sym = 'a';
        System.out.println(sym);

        char sym2 = (char) (sym + 1);
        System.out.println(sym2);
    }

    private static void demoNumeric() {
        Integer a = 1;
        Integer b = 2;

        System.out.println("a + b = " + (a + b));
        System.out.println(a.toString() + b.toString());

        System.out.println("a equals b is " + a.equals(b));
        System.out.println("a equals b is " + (a.compareTo(b) == 0));
    }

    private static void demoString() {
        String s1 = "atmwithabstractbaseclass";
        String s2 = "atmwithabstractbaseclass";

        System.out.println("s1 equals s2 is " + s1.equals(s2));
        System.out.println("s1 == s2 is " + (s1 == s2) + "\n");

        String s3 = new String("atmwithabstractbaseclass");
        System.out.println("s1 equals s3 is " + s1.equals(s3));
        System.out.println("s1 == s3 is " + (s1 == s3) + "\n");

        String s4 = new String("atmwithabstractbaseclass").intern();
        System.out.println("s1 equals s4 is " + s1.equals(s4));
        System.out.println("s1 == s4 is " + (s1 == s4) + "\n");

        String s5 = "te" + "st";
        System.out.println("s1 equals s5 is " + s1.equals(s5));
        System.out.println("s1 == s5 is " + (s1 == s5));
    }

    private static void demoStringOperations() {
        String s = "Hello world";
        System.out.println(s.replaceAll("o", ""));
        System.out.println("S after replace  " + s);

        String s2 = "Hello123world567";
        System.out.println(s2.replaceAll("[a-zA-z]", ""));

        System.out.println(s.substring(0, 5));

        String[] split = s.split(" ");
        System.out.println(split[0]);
        System.out.println(split[1]);

        System.out.println("'Hello world'.length() = " + s.length());

        String concat = "H" + "e" + "l" + "l" + "o";
        System.out.println(concat);

        String concat2 = "H" + 1 + 2 + "e" + "l" + "l" + "0";
        System.out.println("\"H\" + 1 + 2 + \"e\" + \"l\" + \"l\" + \"0\" = " + concat2);

        String concat3 = "H" + (1 + 2) + "e" + "l" + "l" + "0";
        System.out.println("\"H\" + (1 + 2) + \"e\" + \"l\" + \"l\" + \"0\" = " + concat3);

        String concat4 = (1 + 2) + "H" + "e" + "l" + "l" + "0";
        System.out.println("(1 + 2) + \"H\"  + \"e\" + \"l\" + \"l\" + \"0\" = " + concat4);

        String concat5 = 1 + 2 + "H" + "e" + "l" + "l" + "0";
        System.out.println("1 + 2 + \"H\"  + \"e\" + \"l\" + \"l\" + \"0\" = " + concat5);
    }

    private static void demoDate() throws ParseException {
        Date date = new Date();
        System.out.println("Date: " + date);

        date.setMonth(0);
        System.out.println("Date with month: " + date);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println("Formated: " + simpleDateFormat.format(date));

        String dateStr = "20.11.1990 15:23:37";
        date = simpleDateFormat.parse(dateStr);
        System.out.println("Date after parse str: " + simpleDateFormat.format(date));

        System.out.println("Number of milliseconds since January 1, 1970 = " + date.getTime());
    }

    private static void demoCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2005);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 23);

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 38);
        calendar.set(Calendar.SECOND, 59);


        Date date = new Date(calendar.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println("Date after calendar: " + simpleDateFormat.format(date));
    }

    private static void demoLogical() {
        boolean trueValue = true;
        boolean falseValue = false;

        boolean andValue = trueValue & falseValue;
        System.out.println("true & false = " + andValue);

        boolean orValue = trueValue || falseValue;
        System.out.println("true || false = " + orValue);

        Boolean booleanObject = new Boolean("Hello guys!");
        System.out.println("new Boolean(\"Hello guys!\") = " + booleanObject);

        booleanObject = new Boolean("True");
        System.out.println("new Boolean(\"True\") = " + booleanObject);

        booleanObject = new Boolean("true");
        System.out.println("new Boolean(\"true\") = " + booleanObject);

        Boolean booleanObject2 = true;
        System.out.println("booleanObject2.equals(booleanObject) is " + booleanObject2.equals(booleanObject));
    }


    private static void moreDemoLogical() {
        boolean result = fun1() || fun2() || fun3() || fun4();
        System.out.println(result);


        result = fun1() | fun2() | fun3() | fun4();
        System.out.println(result);
    }

    private static boolean fun1() {
        System.out.print("METHOD[fun1 call;] ");
        return true;
    }

    private static boolean fun2() {
        System.out.print("METHOD[fun2 call;] ");
        return true;
    }

    private static boolean fun3() {
        System.out.print("METHOD[fun3 call;] ");
        return true;
    }

    private static boolean fun4() {
        System.out.print("METHOD[fun4 call;] ");
        return false;
    }

    private static void demoChars() {
        char c = 'a';

        System.out.println("Symbol: " + c + " has code " + (int) c);
        for (int i = 0; i < 128; i++) {
            c = (char) i;
            System.out.println("code " + i + "; symbol = " + c + ",");
        }
    }

    private static void demoBoxUnbox(){
        Integer i1 = 5;
        int i2 = 5;
        System.out.println("i1 == i2 " + (i1 == i2));

        Integer i3 = null;
        int i4 = 6;

        System.out.println("i3 == i4 " + (i3 == i4));
    }

}
