/**
 * (probably) Copyright 2011 by Neal Ford. All rights reserved.
 */
class ClassifierCached {
  private sumCache, factorCache

  ClassifierCached() {
    sumCache = [:]
    factorCache = [:]
  }

  def sumOfFactors(number) {
    sumCache.containsKey(number) ?
      sumCache[number] :
      sumCache.putAt(number, factorsOf(number).inject(0, {i, j -> i + j}))
  }

  def isFactor(number, potential) {
    number % potential == 0;
  }

  def factorsOf(number) {
    factorCache.containsKey(number) ?
      factorCache[number] :
      factorCache.putAt(number, (1..number).findAll { i -> isFactor(number, i) })
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
