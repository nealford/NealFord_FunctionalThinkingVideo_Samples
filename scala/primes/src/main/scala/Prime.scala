/**
 * (probably) Copyright 2013 by Neal Ford. All rights reserved.
 */

object Prime {
  def factors(number: Int) =
    (1 to number) filter (number % _ == 0)

  def isPrime(number: Int) : Boolean =
    factors(number) == List(1, number)
}