import nealford.ft.numberclassification.NumberClassifier;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class NumberClassifierTest {
    @Test
    public void is_factor() {
        assertTrue(NumberClassifier.isFactor(1, 10));
        assertTrue(NumberClassifier.isFactor(5, 25));
        assertFalse(NumberClassifier.isFactor(6, 25));
    }

    @Test
    public void factors() {
        Set<Integer> expected = new HashSet<Integer>() {{
            add(1); add(2); add(3); add(6);
        }};
        assertEquals(expected,  NumberClassifier.factors(6));
        expected = new HashSet<Integer>() {{
            add(1);add(2);add(4);add(8);add(16);
        }};
        assertEquals(expected,  NumberClassifier.factors(16));
        expected = new HashSet<Integer>() {{
            add(1);
        }};
        assertEquals(expected,  NumberClassifier.factors(1));
        expected = new HashSet<Integer>() {{
            add(1);add(2);add(4);add(5);add(10);add(20);add(25);add(50);add(100);
        }};
        assertEquals(expected,  NumberClassifier.factors(100));
    }

    @Test
    public void sum_of_factors() {
        assertEquals(12,  NumberClassifier.sum(new HashSet(Arrays.asList(1, 2, 3, 6))));
        assertEquals(1+17,  NumberClassifier.sum(new HashSet(Arrays.asList(1, 17))));
        assertEquals(1+2+4+5+10+20+25+50+100,  NumberClassifier.sum(new HashSet(Arrays.asList(1, 2, 4, 5, 10, 20, 25, 50, 100))));
    }

    @Test
    public void perfection() {
        List<Integer> PERFECT_NUMBERS = new ArrayList<Integer>() {{
            add(6);add(28);add(496);add(8128);
        }};
        for (int n : PERFECT_NUMBERS) {
            assertTrue( NumberClassifier.isPerfect(n));
        }
        for (int n = 0; n < 9000; n++) {
            boolean result =  NumberClassifier.isPerfect(n);
            if (PERFECT_NUMBERS.contains(n))
                assertTrue(result);
            else
                assertFalse(result);
        }
    }

    @Test
    public void abundance() {
        List<Integer> ABUNDANT_NUMBERS = new ArrayList<Integer>() {{
            add(12);add(18);add(20);add(24);add(30);add(36);add(40);add(42);add(48);
        }};
        for (int n = 2; n < 49; n++) {
            boolean result =  NumberClassifier.isAbundant(n);
            if (ABUNDANT_NUMBERS.contains(n))
                assertTrue(result);
            else
                assertFalse(result);
        }
    }

    @Test
    public void deficiency() {
        List<Integer> DEFICIENT_NUMBERS = new ArrayList<Integer>() {{
            add(1);add(15);add(16);add(17);add(19);add(14);add(13);
            add(11);add(10);add(9);add(8);add(7);add(5);add(4);add(3);add(2);
        }};
        for (int n = 2; n < 20; n++) {
            boolean result =  NumberClassifier.isDeficient(n);
            if (DEFICIENT_NUMBERS.contains(n))
                assertTrue(result);
            else
                assertFalse(result);
        }
    }
}
