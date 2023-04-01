/**
 * Count the number of even values in a chain of linked nodes.
 *
 */
public class CountEvens {

    /**
     * Returns the number of even values in the parameter.
     */
    public int countEvens(Node firstNode) {

        int count = 0;

        Node current = firstNode;

        while (current != null) {

            if (current.value % 2 == 0) {
                //iter
                count++;
            }
            current = current.next;
        }
        return count;

    }

    class Node {
        int value;
        Node prev;
        Node next;

        public Node(int val) {
            value = val;
            prev = null;
            next = null;
        }
    }
}

