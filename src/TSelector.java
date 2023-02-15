import java.util.*;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author Adam Biggs (ajb0217@auburn.edu)
 * @version 2/8/23
 */
public final class TSelector {

    /**
     * Can't instantiate this class.
     * <p>
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     */
    private TSelector() {
    }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll the Collection from which the minimum is selected
     * @param comp the Comparator that defines the total order on T
     * @return the minimum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {

        if (coll == null) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        //min functionality
        T min = coll.iterator().next();
        for (T i : coll) {
            if (comp.compare(i, min) < 0) {
                min = i;
            }

        }

        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll the Collection from which the maximum is selected
     * @param comp the Comparator that defines the total order on T
     * @return the maximum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {

        if (coll == null) {
            //throw
            throw new IllegalArgumentException("Array is null");
        }

        //max functionality
        T max = coll.iterator().next();
        for (T i : coll) {
            if (comp.compare(i, max) > 0) {
                max = i;
            }

        }

        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll the Collection from which the kth minimum is selected
     * @param k    the k-selection value
     * @param comp the Comparator that defines the total order on T
     * @return the kth minimum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Fail");
        }
        if (coll.isEmpty() || k < 1 || k > coll.size()) {
            throw new NoSuchElementException("Bad");
        }

        //vars
        ArrayList<T> alist = new ArrayList<T>();
        Iterator<T> iter = coll.iterator();

        while (iter.hasNext()) {
            alist.add(iter.next());
        }
        //sort
        java.util.Collections.sort(alist, comp);

        if (k == 1) {
            return alist.get(0);
        }
        //first in arraylist
        T first = alist.get(0);

        //nullify
        T kmin = null;

        int init = 1;

        //loop
        for (int i = 1; i < alist.size(); i++) {

            if (!alist.get(i).equals(first)) {

                init++;
                if (k == init) {

                    kmin = alist.get(i);

                }
            }

            first = alist.get(i);

        }
        //return
        return kmin;
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll the Collection from which the kth maximum is selected
     * @param k    the k-selection value
     * @param comp the Comparator that defines the total order on T
     * @return the kth maximum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Fail");
        }
        if (coll.isEmpty() || k < 1 || k > coll.size()) {
            throw new NoSuchElementException("Bad");
        }

        //vars
        Iterator<T> iter = coll.iterator();
        ArrayList<T> alist = new ArrayList<>();

        while (iter.hasNext()) {
            alist.add(iter.next());
        }

        //sort
        java.util.Collections.sort(alist, comp);

        if (k == 1) {
            return alist.get(alist.size() - 1);
        }

        T end = alist.get(alist.size() - 1);
        //nullify
        T kmax = null;

        int init = 1;

        for (int i = alist.size() - 2; i >= 0; i--) {
            if (!alist.get(i).equals(end)) {

                init++;
                if (k == init) {

                    kmax = alist.get(i);
                }
            }

            end = alist.get(i);
        }
        //return
        return kmax;
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the range values are selected
     * @param low  the lower bound of the range
     * @param high the upper bound of the range
     * @param comp the Comparator that defines the total order on T
     * @return a Collection of values between low and high
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                          Comparator<T> comp) {
        //throw
        if (coll == null || comp == null) {
            throw new IllegalArgumentException("null values");
        }

        Collection<T> range = new ArrayList<>();

        for (T value : coll) {

            if (comp.compare(value, low) >= 0 && comp.compare(value, high) <= 0) {

                range.add(value);
            }

        }
        //throw
        if (range.isEmpty()) {
            throw new NoSuchElementException("fail");
        }
        //return
        return range;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the ceiling value is selected
     * @param key  the reference value
     * @param comp the Comparator that defines the total order on T
     * @return the ceiling value of key in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        //throws
        if (coll == null || comp == null) {
            throw new IllegalArgumentException("null");
        }

        if (coll.isEmpty()) {
            throw new NoSuchElementException("empty");
        }

        //nullify
        T result = null;

        //for each
        for (T element : coll) {

            if (comp.compare(element, key) >= 0) {

                if (result == null || comp.compare(element, result) < 0) {

                    result = element;
                }
            }

        }
        //throw if null
        if (result == null) {
            throw new NoSuchElementException("result is null");
        }

        //return
        return result;
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the floor value is selected
     * @param key  the reference value
     * @param comp the Comparator that defines the total order on T
     * @return the floor value of key in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        //throw
        if (coll == null || comp == null) {
            throw new IllegalArgumentException("null");
        }

        //nullify
        T result = null;

        //for each
        for (T value : coll) {
            if (comp.compare(value, key) <= 0 && (result == null || comp.compare(value, result) > 0)) {
                result = value;
            }
        }

        //other throw
        if (result == null) {
            throw new NoSuchElementException("value is null");
        }

        //return
        return result;
    }

}
