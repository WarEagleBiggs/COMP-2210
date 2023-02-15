/**
 * GenericsB.java
 * Used to illustrate basic principles of generic types
 * and type safety in Java.
 */
public class GenericsB {

    /**
     * Make this method generic and type safe. Use a
     * type variable named T that will allow you to
     * find the minimum element of an array of any
     * type of mutually-comparable values.
     */
    public static <T extends Comparable<T>> T min(T[] a) {
        T min = a[0];
        for (T val : a) {
            if (val.compareTo(min) < 0) {
                min = val;
            }
        }
        return min;
    }

    /**
     * Drives execution.
     */
    public static void main(String[] args) {
        Integer[] a2 = {4, 10, 2, 8, 6};
        String[] a3 = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};

        // You'll need to change some of these
        // statements once you make the min
        // method generic.
        var min1 = GenericsB.min(a2);
        System.out.println(min1);
        var min2 = GenericsB.min(a3);
        System.out.println(min2);
    }

}
