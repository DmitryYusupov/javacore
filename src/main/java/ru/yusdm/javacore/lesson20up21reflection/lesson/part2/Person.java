package ru.yusdm.javacore.lesson20up21reflection.lesson.part2;


import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Admin on 4/8/2019.
 */
public class Person {

//    @Repeatable(repeatTimes = 1, errorMessage = "asas")
    @Deprecated
    private String name;

    public static void main(String[] args) throws Exception{
        Person p = new Person();
        p.test();
    }
    @Repeatable(errorMessage = "Unknown error", repeatTimes = 2)
    public int calculateDateOfBirthInMillis(Date date) {
        //person?.name?.toString();
        //calculateDateOfBirthInMillis(date!!);
        return 0;
    }

    public void test2() throws Exception {
        Person p = new Person();
        p.calculateDateOfBirthInMillis(new Date());
    }

    public void test() throws NoSuchMethodException {
        Person p = new Person();
        Method calculateDateOfBirthInMillis = p.getClass().getDeclaredMethod("calculateDateOfBirthInMillis", Date.class);
        Repeatable annotation = calculateDateOfBirthInMillis.getAnnotation(Repeatable.class);
        String error = annotation.errorMessage();
        int numberOfRepeats = annotation.repeatTimes();

        try {
            for (int i = 0; i < numberOfRepeats; i++) {
                calculateDateOfBirthInMillis.invoke(p, new Date());
            }
        } catch (Exception e) {
            System.out.println(error);
        }
    }

    /*
    @Transactional
    public void addPersons(){
        addPerson()
        updatePerson();
    }
    */
}
