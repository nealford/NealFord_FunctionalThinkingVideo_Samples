package com.nealford.ft.memoization

/**
 * (probably) Copyright 2011 by Neal Ford. All rights reserved.
 */
class ClassifierMemoized {
  def static dividesBy = { number, potential ->
    number % potential == 0
  }
//  def static isFactor = dividesBy.memoize()
  def static isFactor = dividesBy.memoizeAtMost(1000)

  def static factorsOf(number) {
    (1..number).findAll { i -> isFactor.call(number, i) }
  }

  def static sumFactors = { number ->
    factorsOf(number).inject(0, {i, j -> i + j})
  }
  def static sumOfFactors = sumFactors.memoize()

  def static isPerfect(number) {
    sumOfFactors(number) == 2 * number
  }

  def static isAbundant(number) {
    sumOfFactors(number) > 2 * number
  }

  def static isDeficient(number) {
    sumOfFactors(number) < 2 * number
  }
}
