/**
 * (probably) Copyright 2013 by Neal Ford. All rights reserved.
 */
import org.scalatest.FunSuite

class PrimeTestSuite extends FunSuite {
  def PrimesBelow50 =
    List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)

  test("factors") {
    assert(List(1, 2) == Prime.factors(2))
    assert(List(1, 2, 3, 6) == Prime.factors(6))
    assert(List(1, 2, 4, 8, 16) == Prime.factors(16))
    assert(List(1, 2, 3, 4, 6, 12) == Prime.factors(12))
    assert(List(1, 2, 4, 5, 10, 20, 25, 50, 100) == Prime.factors(100))
  }

  test("primes below 50") {
    for (i <- 2 to 50)
      if (PrimesBelow50.contains(i))
        assert(Prime.isPrime(i) == true)
      else
        assert(Prime.isPrime(i) == false)
    for (i <- PrimesBelow50)
      if (i != 2)
        assert(Prime.isPrime(i+1) == false)
  }
}


