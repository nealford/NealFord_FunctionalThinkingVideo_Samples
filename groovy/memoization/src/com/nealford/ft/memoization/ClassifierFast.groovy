package com.nealford.ft.memoization

import static java.lang.Math.sqrt

/**
 * (probably) Copyright 2012 by Neal Ford. All rights reserved.
 */
class ClassifierFast {
  def static isFactor(number, potential) {
    number % potential == 0
  }

  def static factorsOf(number) {
    def factors = (1..sqrt(number)).findAll { isFactor(number, it)}
    factors.addAll factors.collect {number / it}
    factors.unique()
  }

  def static sumOfFactors(number) {
    factorsOf(number).inject(0, {i, j -> i + j})
  }

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


