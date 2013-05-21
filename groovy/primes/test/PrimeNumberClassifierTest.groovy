import org.junit.Test

import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertFalse

/**
 * (probably) Copyright 2012 by Neal Ford. All rights reserved.
 */
class PrimeNumberClassifierTest {
  @Test
  public void is_factor() {
    PrimeNumberClassifier pnc = new PrimeNumberClassifier(7);
    assertTrue(pnc.isFactor(1));
    assertTrue(pnc.isFactor(7));
    assertFalse(pnc.isFactor(4));
  }

  @Test
  public void factors() {
    PrimeNumberClassifier pnc = new PrimeNumberClassifier(6);
    assertEquals(expectationSetWith(1, 2, 3, 6), pnc.getFactors());
    pnc = new PrimeNumberClassifier(12);
    assertEquals(expectationSetWith(1, 2, 3, 4, 6, 12), pnc.getFactors());
  }

  @Test
  public void sum_factors() {
    PrimeNumberClassifier pnc = new PrimeNumberClassifier(12);
    assertEquals(1 + 2 + 3 + 4 + 6 + 12, pnc.sumFactors());
  }

  @Test
  public void is_prime() {
    PrimeNumberClassifier pnc = new PrimeNumberClassifier(13);
    assertTrue(pnc.isPrime());
    pnc = new PrimeNumberClassifier(12);
    assertFalse(pnc.isPrime());
    pnc = new PrimeNumberClassifier(2);
    assertTrue(pnc.isPrime());
    pnc = new PrimeNumberClassifier(16);
    assertFalse(pnc.isPrime());

  }

  private Set<Integer> expectationSetWith(Integer... numbers) {
    return new HashSet<Integer>(Arrays.asList(numbers));
  }

}
