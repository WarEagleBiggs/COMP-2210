import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectorTest {

    @Test
    void min() {
        int[] a = {1,4,3,6,8,2};
        int expected = 1;
        int actual = Selector.min(a);
        assertEquals(expected, actual );
    }

    @Test
    void max() {
        int[] a = {1,4,3,6,8,2};
        int expected = 8;
        int actual = Selector.max(a);
        assertEquals(expected, actual );

    }

    @Test
    void kmin() {
        int[] a = {1,1,4,3,6,8,2};
        int k = 2;
        int expected = 2;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void kmax() {
        int[] a = {1,1,4,3,6,8,2};
        int k = 3;
        int expected = 4;
        int actual = Selector.kmax(a, k);
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void range() {
        int[] a = {8, 2, 8, 7, 3, 3, 4};
        int low = 3;
        int high = 7;
        int[] expected = {7,3,3,4};
        int[] actual = Selector.range(a, low, high);
        assertArrayEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void ceiling() {
        int[] a = {2,8,7,3,4};
        int key = 1;
        int expected = 2;
        int actual = Selector.ceiling(a, key);
        assertEquals(expected, actual);
        System.out.println(actual);

    }

    @Test
    void floor() {
        int[] a = {2,8,7,3,4};
        int key = 6;
        int expected = 4;
        int actual = Selector.floor(a, key);
        assertEquals(expected, actual);
        System.out.println(actual);

    }
}