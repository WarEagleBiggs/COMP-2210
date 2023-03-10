import java.util.Arrays;

/**
 * Defines a library of selection methods
 * on arrays of ints.
 *
 * @author Adam Biggs (ajb0217@auburn.edu)
 * @version 1/17/23
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     */
    private Selector() {
    }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int min(int[] a) {

        if (a == null || a.length == 0) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        int min = a[0];

        for (int i : a) {
            if (i <= min) {
                min = i;
            }
        }
        return min;
    }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int max(int[] a) {
        if (a == null || a.length == 0) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        int max = a[0];

        for (int i : a) {
            if (i >= max) {
                max = i;
            }
        }
        return max;
    }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmin(int[] a, int k) {

        //error message
        if (a == null || k > a.length || k < 1) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        //make copy
        int[] b = a.clone();

        //sort
        Arrays.sort(b);

        //dups
        int[] unique = Arrays.stream(b).distinct().toArray();

        //throw
        if (k < 1 || k > unique.length) {
            throw new IllegalArgumentException("fail");
        }

        //return
        return unique[k - 1];
    }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmax(int[] a, int k) {

        //error message
        if (a == null || k > a.length || k < 1) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        //make copy
        int[] b = a.clone();

        //sort
        Arrays.sort(b);

        //iter var
        int count = 1;

        if (k >= 2) {
            //iter
            for (int i = b.length - 2; i >= 0; i--) {

                if (b[i] != b[i + 1]) {

                    count++;
                }
                if (count == k) {
                    //return
                    return b[i];
                }
            }
        } else {

            int max = b[0];

            for (int i : b) {
                if (i >= max) {
                    max = i;
                }
            }
            return max;

        }
        //throw
        throw new IllegalArgumentException("fail");
    }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
    public static int[] range(int[] a, int low, int high) {

        //error message
        if (a == null || a.length == 0) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        int iter = 0;

        //foreach iter and ensure is in bounds
        for (int i : a) {

            if (i >= low && i <= high) {
                iter++;
            }

        }

        int[] result = new int[iter];

        int temp = 0;

        //foreach iter and ensure is in bounds and add to new array
        for (int i : a) {
            if (i >= low && i <= high) {

                result[temp++] = i;
            }
        }
        //return
        return result;
    }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int ceiling(int[] a, int key) {

        //error message
        if (a == null || a.length == 0) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        //var
        int ceiling = Integer.MAX_VALUE;

        //foreach
        for (int i : a) {

            if (i >= key && i < ceiling) {
                ceiling = i;

            }
        }

        if (ceiling == Integer.MAX_VALUE) {

            throw new IllegalArgumentException("Too big");
        }

        //return
        return ceiling;

    }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int floor(int[] a, int key) {

        //error message
        if (a == null || a.length == 0) {

            //throw
            throw new IllegalArgumentException("Array is null");
        }

        //var - use absolute lowest
        int floor = Integer.MIN_VALUE;

        //foreach
        for (int i : a) {
            if (i <= key && i > floor) {
                floor = i;

            }
        }

        if (floor == Integer.MIN_VALUE) {

            throw new IllegalArgumentException("Too low");
        }

        //return
        return floor;

    }

}
