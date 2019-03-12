package ru.yusdm.javacore.lesson8setandcomparator.lesson.compareobjects.comparable;

public class StringComparableWithOrdering implements Comparable<String>{

    private String srcString;
    private boolean invert = false;

    public StringComparableWithOrdering(String srcString) {
        this.srcString = srcString;
    }

    @Override
    public int compareTo(String o) {
        if (invert) {
            return (-1) * this.srcString.compareTo(o);
        }else{
            return  this.srcString.compareTo(o);
        }
    }
}
