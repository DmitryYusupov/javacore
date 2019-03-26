package ru.yusdm.javacore.lesson2oopclasses.lesson.staticexplain;

/**
 * Created by Admin on 2/15/2019.
 */
public class Gorilla {

    private String skill;
    private static String[] areas = new String[]{"Asia","Australia"};

    private static String hair = "Black";

    public Gorilla(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Gorilla{" +
                "hair='" + hair + '\'' +
                "skill='" + skill + '\'' +
                '}';
    }

    public static void main(String[] args) {
        //List<Gorilla> gorilas = Arrays.asList()
        Gorilla g1= new Gorilla("Clever");
        Gorilla g2  =new Gorilla("Beauty");
        System.out.println(g1);
        System.out.println(g2);


        Gorilla.hair = "Red";
        g2.hair = "Blue";

        System.out.println(g1);
        System.out.println(g2);

    }



}
