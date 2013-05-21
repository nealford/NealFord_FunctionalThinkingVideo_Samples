import org.junit.Test

import static org.junit.Assert.assertEquals

import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertThat

/**
 * (probably) Copyright 2012 by Neal Ford. All rights reserved.
 */
class PrimeNumberTest {
  @Test
  public void is_factor() {
    assertTrue(PrimeNumber.isFactor(1, 7));
    assertTrue(PrimeNumber.isFactor(7, 7));
    assertFalse(PrimeNumber.isFactor(4, 7));
  }

  @Test
  public void factors() {
    assertTrue([1, 2, 3, 6].equals(PrimeNumber.factors(6).sort()))
    assertTrue([1, 2, 3, 4, 6, 12].equals(PrimeNumber.factors(12).sort()))
    assertTrue([1, 2, 4, 8, 16].equals(PrimeNumber.factors(16).sort()))
  }

  @Test
  public void sum_factors() {
    assertEquals 1 + 2 + 3 + 4 + 6 + 12, PrimeNumber.sumFactors(12)
    assertEquals 1 + 7, PrimeNumber.sumFactors(7)
  }

  @Test
  public void is_prime() {
    assertTrue(PrimeNumber.isPrime(13));
    assertFalse(PrimeNumber.isPrime(12));
    assertTrue(PrimeNumber.isPrime(2));
    assertFalse(PrimeNumber.isPrime(16));
  }

  @Test
  public void lots_of_primes() {
    def PRIMES = [3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47]
    (3..50).each {n ->
      if (PRIMES.contains(n))
        assertTrue(PrimeNumber.isPrime(n))
      else
        assertFalse(PrimeNumber.isPrime(n))
    }
  }

  private Set<Integer> expectationSetWith(Integer... numbers) {
    return new HashSet<Integer>(Arrays.asList(numbers));
  }
  
}
