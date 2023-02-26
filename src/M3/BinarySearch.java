package M3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        //throw
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("null");
        }

        int base = 0;
        int top = a.length - 1;

        if (comparator.compare(a[0], key) == 0) {
            return 0;
        }

        while (base <= top)
        {
            //get the middle
            int mean = base + (top - base) / 2;

            if (comparator.compare(key, a[mean]) < 0) {
                top = mean - 1;
            }
            else if (comparator.compare(key, a[mean]) > 0) {
                base = mean + 1;
            }
            else if (comparator.compare(a[mean - 1], a[mean]) == 0) {
                top = mean - 1;
            }

            else return mean;
        }

        return -1;
    }

    /**
     * Returns the index of the last key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        //throw
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("null");
        }

        int base = 0;
        int top = a.length - 1;

        if (comparator.compare(a[top], key) == 0) {

            return top;

        }

        while (base <= top)
        {
            //middle
            int mean = base + (top - base) / 2;

            if (comparator.compare(key, a[mean]) < 0) {
                top = mean - 1;
            }
            else if (comparator.compare(key, a[mean]) > 0) {
                base = mean + 1;
            }
            else if (comparator.compare(a[mean + 1], a[mean]) == 0) {
                base = mean + 1;
            }

            else return mean;
        }
        return -1;
    }

}
