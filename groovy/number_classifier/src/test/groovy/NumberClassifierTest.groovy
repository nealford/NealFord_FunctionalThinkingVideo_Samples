package test.groovy

import main.groovy.NumberClassifier


class NumberClassifierTest extends GroovyTestCase {

  void testIsFactor() {
    assertTrue NumberClassifier.isFactor(1, 10)
    assertTrue NumberClassifier.isFactor(5, 25)
    assertFalse NumberClassifier.isFactor(3, 7)
  }

  void testFactors() {
    assertEquals([1, 2, 3, 6], NumberClassifier.factors(6))
    assertEquals([1, 2, 4, 8, 16], NumberClassifier.factors(16))
  }

  void testSumOfFactors() {
    assertEquals(12, NumberClassifier.sumFactors(6))
    assertEquals(1, NumberClassifier.sumFactors(1))
    assertEquals(28 * 2, NumberClassifier.sumFactors(28))
  }

  void testPerfection() {
    [6, 28, 496, 8128].each { assertTrue NumberClassifier.isPerfect(it) }
    (497..8127).each { assertFalse NumberClassifier.isPerfect(it)}
  }

  void testAbundance() {
    [12, 18].each { assertTrue NumberClassifier.isAbundant(it)}
    (1..11).each { assertFalse NumberClassifier.isAbundant(it)}
  }

  void testDeficiency() {
    [3, 5, 9, 10].each {assertTrue NumberClassifier.isDeficient(it)}
    assertFalse NumberClassifier.isDeficient(6)
    assertFalse NumberClassifier.isDeficient(12)
    assertFalse NumberClassifier.isDeficient(28)
    [6, 12, 28].each {assertFalse NumberClassifier.isDeficient(it)}
  }

}
