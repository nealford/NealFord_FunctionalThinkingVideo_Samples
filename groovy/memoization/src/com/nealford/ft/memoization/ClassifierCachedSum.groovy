package com.nealford.ft.memoization


// Things to note:
//   1. can't do static now because of ripple effect from sumOfFactors
//
class ClassifierCachedSum {
  private sumCache

  ClassifierCachedSum() {
    sumCache = [:]
  }

  def sumOfFactors(number) {
    if (sumCache.containsKey(number))
      return sumCache[number]
    else {
      def sum = factorsOf(number).inject(0, {i, j -> i + j})
      sumCache.putAt(number, sum)
      return sum
    }
  }

  def isFactor(number, potential) {
    number % potential == 0;
  }

  def factorsOf(number) {
    (1..number).findAll { i -> isFactor(number, i) }
  }

  def isPerfect(number) {
    sumOfFactors(number) == 2 * number
  }

  def isAbundant(number) {
    sumOfFactors(number) > 2 * number
  }

  def isDeficient(number) {
    sumOfFactors(number) < 2 * number
  }
}
