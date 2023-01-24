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
        int k = 3;
        int expected = 3;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void kmax() {
    }

    @Test
    void range() {
    }

    @Test
    void ceiling() {
    }

    @Test
    void floor() {
    }
}