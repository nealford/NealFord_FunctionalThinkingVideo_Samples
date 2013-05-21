package com.nealford.conf.ft.numberclassifier;

import fj.F;
import fj.data.List;

import static fj.data.List.range;
import static fj.function.Integers.add;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class FNumberClassifier {

    public boolean isFactor(int number, int potential_factor) {
        return number % potential_factor == 0;
    }

    public List<Integer> factorsOf(final int number) {
        return range(1, number + 1)
                .filter(new F<Integer, Boolean>() {
                    public Boolean f(final Integer i) {
                        return isFactor(number, i);
                    }
                });
    }

    public int sum(List<Integer> factors) {
        return factors.foldLeft(fj.function.Integers.add, 0);
    }

    public boolean isPerfect(int number) {
        return sum(factorsOf(number)) - number == number;
    }

    public boolean isAbundant(int number) {
        return sum(factorsOf(number)) - number > number;
    }

    public boolean isDeficient(int number) {
        return sum(factorsOf(number)) - number < number;
    }

    public List<Integer> factorsOfOptimized(final int number) {
        final List<Integer> factors = range(1, (int) round(sqrt(number) + 1))
                .filter(new F<Integer, Boolean>() {
                    public Boolean f(final Integer i) {
                        return isFactor(number, i);
                    }
                });
        return factors.append(factors.map(new F<Integer, Integer>() {
            public Integer f(final Integer i) {
                return number / i;
            }
        }))
                .nub();
    }

    // Why name things you're only going to call 1 time?

    public boolean isPerfectUnifed(final int number) {
        return range(1, number + 1)
                .filter(new F<Integer, Boolean>() {
                    public Boolean f(final Integer i) {
                        return isFactor(number, i);
                    }
                })
                .foldLeft(add, 0) == 2 * number;
    }

    public boolean isPerfectOptimized(final int number) {
        return sum(factorsOfOptimized(number)) == 2 * number;
    }

    public boolean isAbundantOptimized(final int number) {
        return sum(factorsOfOptimized(number)) > 2 * number;
    }

    public boolean isDecifientOptimized(final int number) {
        return sum(factorsOfOptimized(number)) < 2 * number;
    }

}