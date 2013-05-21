import static java.lang.Math.sqrt

class PrimeNumber {
  static def isFactor(potential, number) {
    number % potential == 0;
  }

  static def factors = { number ->
    def factors = (1..sqrt(number)).findAll { isFactor(it, number) }
    factors.addAll factors.collect { (int) number / it}
    factors.toSet()
  }

  static def getFactors = factors.memoize();

  static def sumFactors(number) {
    getFactors(number).inject(0, {i, j -> i + j})
  }

  static def isPrime(number) {
    number == 2 || sumFactors(number) == number + 1
  }
}

