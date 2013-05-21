package com.nealford.conf.ft.numberclassifier;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 15, 2011
 * Time: 11:56:42 AM
 * To change this template use File | Settings | File Templates.
 */

import fj.data.List;
import fj.Ord;
import fj.P2;
import fj.F;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class FNumberClassifierTest {

    @Test
    public void is_factor() {
      FNumberClassifier nc = new FNumberClassifier();
      assertTrue(nc.isFactor(10, 1));
      assertTrue(nc.isFactor(25, 5));
      assertFalse(nc.isFactor(25, 7));
    }

    @Test public void get_factors() {
      FNumberClassifier nc = new FNumberClassifier();
      List<Integer> expected = List.list(1, 2, 3, 6);
      assertTrue(sameList(expected, nc.factorsOf(6)));
    }

    private boolean sameList(List l1, List l2) {
        for (int i = 0; i < l1.length()-1; i++) {
            Integer item = (Integer) l1.index(i);
            if (item.intValue() != ((Integer) l2.index(i)).intValue()) {
                return false;
            }
        }
        return true;
    }

    @Test public void sum() {
        FNumberClassifier nc = new FNumberClassifier();
        assertEquals( 12, nc.sum(nc.factorsOf(6)));
    }

    @Test public void perfection() {
        FNumberClassifier nc = new FNumberClassifier();
        assertTrue(nc.isPerfect(28));
        assertTrue(nc.isPerfect(496));
        assertTrue(nc.isPerfect(8128));
        assertFalse(nc.isPerfect(8127));
    }

    @Test public void unifed_perfection() {
        FNumberClassifier nc = new FNumberClassifier();
        assertTrue(nc.isPerfectUnifed(6));
        assertTrue(nc.isPerfectUnifed(28));
        assertTrue(nc.isPerfectUnifed(496));
        assertTrue(nc.isPerfectUnifed(8128));
        assertFalse(nc.isPerfectUnifed(8127));

    }

    @Test public void test_factors_optimized() {
        FNumberClassifier nc = new FNumberClassifier();
        List expected = List.list(1, 2, 3, 6);
        List actual = nc.factorsOfOptimized(6);
        assertTrue(sameListWithoutOrder(expected, actual));
        assertTrue(sameListWithoutOrder(List.list(1, 2, 3, 4, 6, 12), nc.factorsOfOptimized(12)));
    }

    @Test public void test_perfect_square_edge_case_for_factors_optimzied() {
        FNumberClassifier nc = new FNumberClassifier();
        assertTrue(sameListWithoutOrder(List.list(1, 2, 4), nc.factorsOfOptimized(4)));
        assertTrue(sameListWithoutOrder(List.list(1, 2, 4, 8, 16), nc.factorsOfOptimized(16)));
        assertTrue(sameListWithoutOrder(List.list(1, 2, 4, 5, 10, 20, 25, 50, 100), nc.factorsOfOptimized(100)));
    }

    @Test public void test_optimiized_abundance() {
        final FNumberClassifier nc = new FNumberClassifier();
        assertTrue(List.list(12, 18, 20, 24, 30).dropWhile(new F<Integer, Boolean>() {
            public Boolean f(final Integer i) {
                return nc.isAbundantOptimized(i);
            }
        }).isEmpty());
    }

    @Test public void test_optimized_deficiency() {
        final FNumberClassifier nc = new FNumberClassifier();
        assertTrue(List.list(3, 5, 7, 9, 10, 11, 17, 101, 97)
                       .dropWhile(new F<Integer, Boolean>() {
                            public Boolean f(final Integer i) {
                                return nc.isDecifientOptimized(i);
                            }
                        })
                        .isEmpty());
    }

    private boolean sameListWithoutOrder(List<Integer> l1, List<Integer> l2) {
        return (l1.length() == l2.length())
                &&
                l1.sort(Ord.intOrd)
                   .zip(l2.sort(Ord.intOrd))
                   .filter(new F<P2<Integer, Integer>, Boolean>() {
                        public Boolean f(P2<Integer, Integer> pair) {
                            return pair._1().intValue() != pair._2().intValue();
                        }
        }).isEmpty();

    }

//    private boolean sameListWithoutOrder(List<Integer> l1, List<Integer> l2) {
//
//        if (l1.length() != l2.length()) return false;
//        Integer[] l1Array = l1.array(Integer[].class);
//        Integer[] l2Array = l2.array(Integer[].class);
//        Arrays.sort(l1Array);
//        Arrays.sort(l2Array);
//        for (int i = 0; i < l1Array.length; i++)
//            if (l1Array[i].intValue() != l2Array[i].intValue())
//                return false;
//        return true;
//    }

    @Test public void optimized_perfection() {
        FNumberClassifier nc = new FNumberClassifier();
        assertTrue(nc.isPerfectOptimized(6));
        assertTrue(nc.isPerfectOptimized(28));
        assertTrue(nc.isPerfectOptimized(496));
        assertTrue(nc.isPerfectOptimized(8128));
        assertFalse(nc.isPerfectOptimized(8127));

    }

    public void lots_of_checks_for_optimized_version() {
        FNumberClassifier nc = new FNumberClassifier();
        final ArrayList PERFECT_NUMS = new ArrayList<Integer>() {{
            add(6);
            add(28);
            add(496);
            add(8128);
            add(33550336);
        }};
        long startTime = System.currentTimeMillis();
        for (int i = 2; i < 34000000; i++) {
            if (i % 100000 == 0) System.out.print(".");
            if (PERFECT_NUMS.contains(i))
                assertTrue(nc.isPerfectOptimized(i));
            else
                assertFalse(nc.isPerfectOptimized(i));
        }
        System.out.println("\ntime (in ms): " + (System.currentTimeMillis() - startTime));
    }

}
