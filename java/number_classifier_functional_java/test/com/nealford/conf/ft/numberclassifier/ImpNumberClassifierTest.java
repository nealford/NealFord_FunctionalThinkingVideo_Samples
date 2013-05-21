package com.nealford.conf.ft.numberclassifier;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.core.Is.is;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.lang.reflect.Method;

public class ImpNumberClassifierTest {
    private static final Integer[] PERFECT_NUMS = 
            {6, 28, 496, 8128, 33550336};

    @Test
    public void is_1_a_factor_of_10() {
        ImpNumberClassifier c = new ImpNumberClassifier(10);
        assertTrue(isFactor(c, 1));
    }

    @Test public void is_5_a_factor_of_25() {
        ImpNumberClassifier c = new ImpNumberClassifier(25);
        assertTrue(isFactor(c, 5));
    }

    @Test public void is_3_not_a_factor_of_7() {
        ImpNumberClassifier c = new ImpNumberClassifier(7);
        assertFalse(isFactor(c, 3));
    }

    @Test public void add_factors() {
        Set<Integer> expected =
                new HashSet<Integer>(Arrays.asList(1, 2, 3, 6));
        ImpNumberClassifier c = new ImpNumberClassifier(6);
        addFactor(c, 2);
        addFactor(c, 3);
        assertThat(c.getFactors(), is(expected));
    }

    @Test public void factors_for_6() {
        Set<Integer> expected = expectationSetWith(1, 2, 3, 6);
        ImpNumberClassifier c = new ImpNumberClassifier(6);
        calculateFactors(c);
        assertThat(c.getFactors(), is(expected));
    }

    @Test public void factors_for_12() {
        ImpNumberClassifier c = new ImpNumberClassifier(12);
        calculateFactors(c);
        assertThat(c.getFactors(),
                is(expectationSetWith(1, 2, 3, 4, 6, 12)));
    }

    @Test public void factors_for_100() {
        ImpNumberClassifier c = new ImpNumberClassifier(100);
        calculateFactors(c);
        assertThat(c.getFactors(),
                is(expectationSetWith(1, 100, 2, 50, 4, 25, 5, 20, 10)));
    }

    @Test public void factors_for_prime() {
        ImpNumberClassifier c = new ImpNumberClassifier(23);
        calculateFactors(c);
        assertThat(c.getFactors(), is(expectationSetWith(1, 23)));
    }

    @Test public void test_a_bunch_of_numbers() {
        Set<Integer> expected = new HashSet<Integer>(
                Arrays.asList(PERFECT_NUMS));
        for (int i = 2; i < 1000; i++) {
            if (expected.contains(i))
                assertTrue(classifierFor(i).isPerfect());
            else
                assertFalse(classifierFor(i).isPerfect());
        }
    }

    @Test public void sum() {
        ImpNumberClassifier c = new ImpNumberClassifier(20);
        int expected = 1 + 2 + 4 + 5 + 10 + 20;
        int actual = getSum(c);
        assertThat(actual, is(expected));
    }

    @Test public void perfection() {
        Integer[] perfectNumbers = PERFECT_NUMS;
        for (int number : perfectNumbers)
            assertTrue(classifierFor(number).isPerfect());
    }

    @Test public void abundance() {
        int[] abundantNumbers = new int[] {12, 18, 20, 24, 30};
        for (int i : abundantNumbers)
            assertTrue(new ImpNumberClassifier(i).isAbundant());
    }

    @Test public void deficicency() {
        int[] deficientNumbers = new int[] {3, 5, 7, 9, 10, 11};
        for (int i : deficientNumbers) {
            assertTrue(new ImpNumberClassifier(i).isDeficient());
        }
    }

    @Test public void static_helper_method() {
        Integer[] perfectNumbers = PERFECT_NUMS;
        for (int number : perfectNumbers)
            assertTrue(ImpNumberClassifier.isPerfect(number));
    }

    private Set<Integer> expectationSetWith(Integer... numbers) {
        return new HashSet<Integer>(Arrays.asList(numbers));
    }

    private ImpNumberClassifier classifierFor(int number) {
        ImpNumberClassifier c = new ImpNumberClassifier(number);
        calculateFactors(c);
        return c;
    }

    private boolean isFactor(ImpNumberClassifier c, int factor) {
        try {
            Method m = ImpNumberClassifier.class.getDeclaredMethod(
                    "isFactor", int.class);
            m.setAccessible(true);
            return (Boolean) m.invoke(c, factor);
        } catch (Throwable t) {
            fail();
        }
        return false;
    }

    private void calculateFactors(ImpNumberClassifier c) {
        try {
            Method m = ImpNumberClassifier.class.getDeclaredMethod("calculateFactors");
            m.setAccessible(true);
            m.invoke(c);
        } catch (Throwable e) {
            fail();
        }
    }

    private void addFactor(ImpNumberClassifier c, int factor) {
        try {
            Method m = ImpNumberClassifier.class.getDeclaredMethod("addFactor", int.class);
            m.setAccessible(true);
            m.invoke(c, factor);
        } catch (Throwable e) {
            fail();
        }
    }

    private int getSum(ImpNumberClassifier c) {
        int sum = 0;
        try {
            Method m = ImpNumberClassifier.class.getDeclaredMethod("getSum");
            m.setAccessible(true);
            sum = ((Integer) m.invoke(c)).intValue();
        } catch (Throwable e) {
            fail();
        }
        return sum;
    }


}
