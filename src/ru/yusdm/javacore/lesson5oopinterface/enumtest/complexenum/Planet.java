package ru.yusdm.javacore.lesson5oopinterface.enumtest.complexenum;

/**
 * Created by Admin on 2/22/2019.
 */
public enum Planet implements TellAboutYourSelf {
    EARTH {
        @Override
        public void meIs() {
            System.out.println("There is life");
        }
    }, MERCURY {
        @Override
        public void meIs() {
            System.out.println("Acckiy sotona");
        }
    }, SATURN {
        @Override
        public void meIs() {
            System.out.println("Gas giant");
        }
    }, MARS {
        @Override
        public void meIs() {
            System.out.println("War planet");
        }
    }
}
