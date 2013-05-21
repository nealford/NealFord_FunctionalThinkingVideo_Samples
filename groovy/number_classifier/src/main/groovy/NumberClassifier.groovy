package main.groovy


class NumberClassifier {

  static def isFactor(factor, number) {
    number % factor == 0
  }

  static def factors(number) {
    (1..number).findAll {isFactor(it, number)}
  }

  static def sumFactors(number) {
    factors(number).inject(0, {i, j -> i + j})
  }

  static def isPerfect(number) {
    sumFactors(number) - number == number
  }

  static def isAbundant(number) {
    sumFactors(number) - number > number
  }

  static def isDeficient(number) {
    sumFactors(number) - number < number
  }

}
