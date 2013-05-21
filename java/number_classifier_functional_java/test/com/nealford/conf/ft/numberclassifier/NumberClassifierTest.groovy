package com.nealford.conf.ft.numberclassifier

import org.junit.Test;
import org.hamcrest.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class NumberClassifierTest {
  public def PERFECT_NUMBERS = setWith(6, 28, 496, 8128, 33550336)

  @Test public void is_factor() {
    assertTrue NumberClassifier.isFactor(10, 1);
    assertTrue NumberClassifier.isFactor(25, 5);
    assertFalse NumberClassifier.isFactor(25, 7);
  }

  @Test public void get_factors() {
    def nc = new NumberClassifier();
    assertEquals setWith(1, 2, 3, 6), NumberClassifier.factors(6)
    assertEquals setWith(1, 100, 2, 5, 50, 20, 4, 25, 10), NumberClassifier.factors(100)
    assertEquals setWith(1, 8128, 2, 254, 1016, 32, 64, 4064, 2032, 4, 8, 16, 508, 127), NumberClassifier.factors(8128)
  }

  @Test public void sum() {
    def nc = new NumberClassifier();
    assertEquals NumberClassifier.sum(setWith(1, 2, 3, 6)), 12
    assertEquals NumberClassifier.sum(setWith(1, 100, 2, 5, 50, 20, 4, 25, 10)), 217
  }

  @Test public void perfect() {
    def nc = new NumberClassifier()
    for (i in 2..9000)
      if (PERFECT_NUMBERS.contains(i))
        assertTrue NumberClassifier.isPerfect(i);
      else
        assertFalse NumberClassifier.isPerfect(i);
  }

  @Test public void abundant() {
    def nc = new NumberClassifier()
    setWith(12, 18, 20, 24, 30).each { number ->
      assertTrue NumberClassifier.isAbundant(number)
    }
    setWith(3, 5, 7, 9, 10, 11).plus(PERFECT_NUMBERS).each { number ->
      assertFalse NumberClassifier.isAbundant(number)
    }
  }

  @Test public void deficient() {
    def nc = new NumberClassifier()
    setWith(3, 5, 7, 9, 10, 11).each { number ->
      assertTrue NumberClassifier.isDeficient(number)
    }
    setWith(12, 18, 20, 24, 30).plus(PERFECT_NUMBERS).each { number ->
      assertFalse NumberClassifier.isDeficient(number)
    }
  }

  public void lots_of_checks_for_optimized_version() {
    NumberClassifier nc = new NumberClassifier();
    def startTime = System.currentTimeMillis();

    for (candidate in 2..200000) {
      if (candidate % 100000 == 0) print(".")
      if (PERFECT_NUMBERS.contains(candidate))
        assertTrue(NumberClassifier.isPerfect(candidate));
      else
        assertFalse(NumberClassifier.isPerfect(candidate));
    }

      println("\nTime (in ms): " + (System.currentTimeMillis() - startTime));
  }



  private Set<Integer> setWith(Integer... numbers) {
      return new HashSet<Integer>(Arrays.asList(numbers));
  }


}