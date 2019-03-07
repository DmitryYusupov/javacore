package ru.yusdm.javacore.lesson5oopinterface.lesson.enumtest.complexenum;

/**
 * Created by Admin on 2/22/2019.
 */
public class PlanetEnumDemo {
    public static void main(String[] args) {

        for (Planet planet : Planet.values()){
            tellAboutYourSelf(planet);
        }

        tellAboutYourSelf(new Person());

        tellAboutYourSelf(new TellAboutYourSelf() {
            @Override
            public void meIs() {
                System.out.println("Anonymous class");
            }
        });
    }

    private static void tellAboutYourSelf(TellAboutYourSelf tellAboutYourSelf){
        tellAboutYourSelf.meIs();
    }
}
