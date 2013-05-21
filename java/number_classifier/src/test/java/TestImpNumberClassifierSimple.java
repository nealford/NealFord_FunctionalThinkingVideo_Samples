import nealford.ft.numberclassification.ImpNumberClassifierSimple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * (probably) Copyright 2013 by Neal Ford. All rights reserved.
 */
public class TestImpNumberClassifierSimple {

    @Test
    public void is_factor() {
        assertTrue(new ImpNumberClassifierSimple(10).isFactor(1));
        assertTrue(new ImpNumberClassifierSimple(25).isFactor(5));
        assertFalse(new ImpNumberClassifierSimple(25).isFactor(6));
    }

    @Test
    public void factors() {
        Set<Integer> expected = new HashSet<Integer>() {{
            add(1); add(2); add(3); add(6);
        }};
        assertEquals(expected, new ImpNumberClassifierSimple(6).getFactors());
        expected = new HashSet<Integer>() {{
            add(1);add(2);add(4);add(8);add(16);
        }};
        assertEquals(expected, new ImpNumberClassifierSimple(16).getFactors());
        expected = new HashSet<Integer>() {{
            add(1);
        }};
        assertEquals(expected, new ImpNumberClassifierSimple(1).getFactors());
        expected = new HashSet<Integer>() {{
            add(1);add(2);add(4);add(5);add(10);add(20);add(25);add(50);add(100);
        }};
        assertEquals(expected, new ImpNumberClassifierSimple(100).getFactors());
    }

    @Test
    public void sum_of_factors() {
        assertEquals(12, new ImpNumberClassifierSimple(6).sumFactors());
        assertEquals(1+17, new ImpNumberClassifierSimple(17).sumFactors());
        assertEquals(1+2+4+5+10+20+25+50+100, new ImpNumberClassifierSimple(100).sumFactors());
    }

    @Test
    public void perfection() {
        List<Integer> PERFECT_NUMBERS = new ArrayList<Integer>() {{
            add(6);add(28);add(496);add(8128);
        }};
        for (int n : PERFECT_NUMBERS) {
            assertTrue(new ImpNumberClassifierSimple(n).isPerfect());
        }
        for (int n = 0; n < 9000; n++) {
            boolean result = new ImpNumberClassifierSimple(n).isPerfect();
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
            boolean result = new ImpNumberClassifierSimple(n).isAbundant();
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
            boolean result = new ImpNumberClassifierSimple(n).isDeficient();
            if (DEFICIENT_NUMBERS.contains(n))
                assertTrue(result);
            else
                assertFalse(result);
        }
    }

}
