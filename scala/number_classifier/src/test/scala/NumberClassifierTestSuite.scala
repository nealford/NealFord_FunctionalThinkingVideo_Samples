package test.scala

import org.scalatest.FunSuite
import main.scala.NumberClassifier
import NumberClassifier._

class NumberClassifierTestSuite extends FunSuite {
  test("factors") {
    assert(List(1, 2, 3, 6) == factors(6))
    assert(List(1, 2, 4, 8, 16) == factors(16))
    assert(List(1, 2, 3, 4, 6, 12) == factors(12))
    assert(List(1, 2, 4, 5, 10, 20, 25, 50, 100) == factors(100))
  }

  test("sum") {
    assert(12 == sum(List(1, 2, 3, 6)))
    assert(28 * 2 == sum(List(1, 2, 4, 7, 14, 28)))
  }

}