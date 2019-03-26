package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.overridmethods;

/**
 * Created by Admin on 2/20/2019.
 * ;asjflasjfl
 * dfgpdfpgdpfg
 *
 */
public class ChiefCook extends Cook{

    public ChiefCook(String dish) {
        super("sdfdsf");

    }

   /* public ChiefCook() {
        super();
    }
*/
    @Override
    public void cook() {
        System.out.println("Chief cook in action");
        super.cook();
    }

    public static void main(String[] args) {
        Cook cook = new ChiefCook("kjhkhk");
        cook.cook();
    }
}
