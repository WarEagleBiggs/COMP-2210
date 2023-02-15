import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * GenericsC.java
 * Used to illustrate basic principles of generic types
 * and type safety in Java.
 */

////////////////////////////////////////////////
//
// Add appropriate type parameters and arguments
// to eliminate all unchecked warnings. That is,
// make this code type safe.
//
///////////////////////////////////////////////

public class GenericsC<T> {

    private final List<T> al;

    /**
     * Builds a new instance of this class.
     */
    public GenericsC() {
        al = new ArrayList<>();
    }

    /**
     * Drives execution.
     */
    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<>();
        for (int i = 1; i < 12; i += 2) {
            c.add(i);
        }

        GenericsC<Integer> lab = new GenericsC<>();
        lab.addAll(c);
        System.out.println(lab);
    }

    /**
     * Adds all the values in c to this object.
     */
    public void addAll(Collection<T> c) {
        al.addAll(c);
    }

    /**
     * Returns a string representation of this object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T t : al) {
            s.append(t);
            s.append(" ");
        }
        return s.toString();
    }


}
