
/**
 * (probably) Copyright 2012 by Neal Ford. All rights reserved.
 */
class PrimeNumberClassifier {
  private int number;

  PrimeNumberClassifier(int number) {
    this.number = number
  }

  public def isFactor(int potential) {
    number % potential == 0;
  }

  public def getFactors() {
    (1..number).findAll { isFactor(it) }.toSet()
  }

  public def sumFactors() {
    getFactors().inject(0, {i, j -> i + j})
  }

  public def isPrime() {
    sumFactors() == number + 1
  }
}
