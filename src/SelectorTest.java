import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectorTest {

    @Test
    void min() {
        int[] a = {1, 4, 3, 6, 8, 2};
        int expected = 1;
        int actual = Selector.min(a);
        assertEquals(expected, actual);
    }

    @Test
    void max() {
        int[] a = {1, 4, 3, 6, 8, 2};
        int expected = 8;
        int actual = Selector.max(a);
        assertEquals(expected, actual);

    }

    @Test
    void kmin() {
        int[] a = {1, 1, 4, 3, 6, 8, 2};
        int k = 2;
        int expected = 2;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void kmax() {
        int[] a = {5, 7};
        int k = 1;
        int expected = 7;
        int actual = Selector.kmax(a, k);
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void range() {
        int[] a = {8, 2, 8, 7, 3, 3, 4};
        int low = 3;
        int high = 7;
        int[] expected = {7, 3, 3, 4};
        int[] actual = Selector.range(a, low, high);
        assertArrayEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void ceiling() {
        int[] a = {-3, 3, 9, 7, 0};
        int key = 9;
        int expected = 9;
        int actual = Selector.ceiling(a, key);
        assertEquals(expected, actual);
        System.out.println(actual);

    }

    @Test
    void floor() {
        int[] a = {7};
        int key = 11;
        int expected = 7;
        int actual = Selector.floor(a, key);
        assertEquals(expected, actual);
        System.out.println(actual);

    }
}