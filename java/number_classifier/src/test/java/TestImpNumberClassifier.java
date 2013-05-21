import nealford.ft.numberclassification.ImpNumberClassifier;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


public class TestImpNumberClassifier {
    @Test
    public void is_factor() {
        Method m = null;
        try {
            m = ImpNumberClassifier.class.getDeclaredMethod("isFactor", int.class);
            m.setAccessible(true);
            assertTrue((Boolean) m.invoke(new ImpNumberClassifier(10), 1));
            assertTrue((Boolean) m.invoke(new ImpNumberClassifier(25), 5));
            assertFalse((Boolean) m.invoke(new ImpNumberClassifier(25), 6));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void factors() {
        Method m = null;
        try {
            Set<Integer> expected = new HashSet<Integer>() {{
                add(1);
                add(2);
                add(3);
                add(6);
            }};
            m = ImpNumberClassifier.class.getDeclaredMethod("getFactors");
            m.setAccessible(true);
            assertEquals(expected, m.invoke(new ImpNumberClassifier(6)));

            expected = new HashSet<Integer>() {{
                add(1);
                add(2);
                add(4);
                add(8);
                add(16);
            }};
            assertEquals(expected, m.invoke(new ImpNumberClassifier(16)));

            expected = new HashSet<Integer>() {{
                add(1);
            }};
            assertEquals(expected, m.invoke(new ImpNumberClassifier(1)));

            expected = new HashSet<Integer>() {{
                add(1);
                add(2);
                add(4);
                add(5);
                add(10);
                add(20);
                add(25);
                add(50);
                add(100);
            }};
            assertEquals(expected, m.invoke(new ImpNumberClassifier(100)));
        } catch (Exception x) {
            fail();
        }
    }

    @Test
    public void sum_of_factors() {
        Method m = null;
        try {
            m = ImpNumberClassifier.class.getDeclaredMethod("sumFactors");
            m.setAccessible(true);
            assertEquals(12, m.invoke(new ImpNumberClassifier(6)));
            assertEquals(1 + 17, m.invoke(new ImpNumberClassifier(17)));
            assertEquals(1 + 2 + 4 + 5 + 10 + 20 + 25 + 50 + 100, m.invoke(new ImpNumberClassifier(100)));
        } catch (Exception x) {
            fail();
        }
    }

    @Test
    public void perfection() {
        List<Integer> PERFECT_NUMBERS = new ArrayList<Integer>() {{
            add(6);
            add(28);
            add(496);
            add(8128);
        }};
        for (int n : PERFECT_NUMBERS) {
            assertTrue(new ImpNumberClassifier(n).isPerfect());
        }
        for (int n = 0; n < 9000; n++) {
            boolean result = new ImpNumberClassifier(n).isPerfect();
            if (PERFECT_NUMBERS.contains(n))
                assertTrue(result);
            else
                assertFalse(result);
        }
    }

    @Test
    public void abundance() {
        List<Integer> ABUNDANT_NUMBERS = new ArrayList<Integer>() {{
            add(12);
            add(18);
            add(20);
            add(24);
            add(30);
            add(36);
            add(40);
            add(42);
            add(48);
        }};
        for (int n = 2; n < 49; n++) {
            boolean result = new ImpNumberClassifier(n).isAbundant();
            if (ABUNDANT_NUMBERS.contains(n))
                assertTrue(result);
            else
                assertFalse(result);
        }
    }

    @Test
    public void deficiency() {
        List<Integer> DEFICIENT_NUMBERS = new ArrayList<Integer>() {{
            add(1);
            add(15);
            add(16);
            add(17);
            add(19);
            add(14);
            add(13);
            add(11);
            add(10);
            add(9);
            add(8);
            add(7);
            add(5);
            add(4);
            add(3);
            add(2);
        }};
        for (int n = 2; n < 20; n++) {
            boolean result = new ImpNumberClassifier(n).isDeficient();
            if (DEFICIENT_NUMBERS.contains(n))
                assertTrue(result);
            else
                assertFalse(result);
        }
    }


}
