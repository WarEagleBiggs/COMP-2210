package M4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Adam Biggs (ajb0217@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /**
     * References to the first and last node of the list.
     */
    Node front;
    Node rear;

    /**
     * The number of nodes in the list.
     */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////

    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////

    /**
     * Returns the current size of this collection.
     *
     * @return the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////

    /**
     * Tests to see if this collection is empty.
     *
     * @return true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param element The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {
        if (element == null) {
            return false;
        }

        if (contains(element)) {
            return false;
        }

        Node newNode = new Node(element);

        if (isEmpty()) {

            front = newNode;
            rear = newNode;

        } else if (element.compareTo(front.element) < 0) {

            newNode.next = front;
            front.prev = newNode;
            front = newNode;

        } else {

            Node current = front;

            while (current.next != null && element.compareTo(current.next.element) > 0) {

                current = current.next;
            }
            if (current.next == null) {

                rear.next = newNode;
                newNode.prev = rear;
                rear = newNode;
            } else {

                newNode.next = current.next;
                current.next.prev = newNode;
                current.next = newNode;
                newNode.prev = current;
            }

        }

        size++;
        return true;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {

        if (isEmpty() || element == null) {
            return false;
        }

        Node current = front;

        while (current != null && current.element.compareTo(element) < 0) {

            current = current.next;
        }

        if (current == null || current.element.compareTo(element) > 0) {

            return false;
        }

        if (front == rear) {

            front = null;
            rear = null;
        } else if (current == front) {

            front = front.next;
            front.prev = null;
        } else if (current == rear) {

            rear = rear.prev;
            rear.next = null;
        } else {

            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return true;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
        Node current = front;

        while (current != null) {

            if (current.element.equals(element)) {

                return true;
            }
            
            current = current.next;
        }
        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        LinkedSet<T> fin = new LinkedSet<>();

        for (T i: s){
            fin.add(i);
        }
        return equals(fin);
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {


        if (s == null || size != s.size()) {
            return false;
        }

        Iterator<T> itr = iterator();

        Iterator<T> oItr = s.iterator();

        while (itr.hasNext() && oItr.hasNext()) {

            T element = itr.next();

            T oElement = oItr.next();

            if (!element.equals(oElement)) {

                return false;
            }
        }
        return true;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){

        if (s == null) {
            throw new IllegalArgumentException("null");
        }

        LinkedSet<T> result = new LinkedSet<>();

        for (T element : this) {
            result.add(element);
        }

        for (T element : s) {
            result.add(element);
        }

        return result;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){

        if(s == null) {

            throw new NullPointerException();
        }

        LinkedSet<T> returnSet = new LinkedSet<T>();

        Node i = front;

        while(i != null) {

            returnSet.add(i.element);

            i = i.next;
        }

        Iterator<T> j = s.iterator();

        while(j.hasNext()) {

            returnSet.add(j.next());
        }

        return returnSet;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {

        LinkedSet<T> intersectionSet = new LinkedSet<>();

        for (T element : this) {

            if (s.contains(element)) {
                intersectionSet.add(element);
            }
        }

        return intersectionSet;

    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {

        LinkedSet<T> intersection = new LinkedSet<>();

        if (isEmpty()){

            return intersection;
        }

        Node current0 = front;
        Node current1 = s.front;

        while (current0 != null && current1 != null) {

            int cmp = current0.element.compareTo(current1.element);
            if (cmp == 0) {

                intersection.add(current0.element);

                current0 = current0.next;
                current1 = current1.next;
            } else if (cmp < 0) {

                current0 = current0.next;
            } else {

                current1 = current1.next;
            }
        }

        return intersection;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {

        if (s == null) {
            throw new IllegalArgumentException("null");
        }

        Set<T> newSet = new LinkedSet<T>();

        Iterator<T> itr1 = this.iterator();

        while (itr1.hasNext()) {

            newSet.add(itr1.next());
        }

        Iterator<T> itr2 = s.iterator();

        while (itr2.hasNext()) {

            newSet.remove(itr2.next());
        }
        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {

        LinkedSet result = new LinkedSet();

        Node curr = front;

        while (curr != null) {

            if (!s.contains(curr.element)) {
                result.add(curr.element);
            }
            curr = curr.next;
        }
        return result;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node curr = front;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T returnT = curr.element;
                curr = curr.next;
                return returnT;
            }
        };
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            Node curr = rear;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T returnT = curr.element;
                curr = curr.prev;
                return returnT;
            }
        };
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {
        //attempt #4
        return new Iterator<Set<T>>() {
            Node current = null;
            boolean started = false;
            int counter = 0;

            public boolean hasNext() {

                return ! started || (current != null && counter < (1 << size));
            }

            public Set<T> next() {

                if (!started) {

                    started = true;
                    return new LinkedSet<>();
                }

                LinkedSet<T> sub = new LinkedSet<>();

                int i = 0;

                Node n = front;

                while (i < size) {

                    if ((counter & (1 << i)) != 0) {

                        sub.add(n.element);
                    }

                    n = n.next;

                    i++;
                }

                current = n;

                counter++;

                return sub;
            }

        };

    }




    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Feel free to add as many private methods as you need.

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}
