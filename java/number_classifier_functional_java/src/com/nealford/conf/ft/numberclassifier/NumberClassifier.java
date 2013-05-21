package com.nealford.conf.ft.numberclassifier;

import static java.lang.Math.sqrt;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NumberClassifier {

    static public boolean isFactor(int number, int potential_factor) {
        return number % potential_factor == 0;
    }

    static public Set<Integer> factors(int number) {
        HashSet<Integer> factors = new HashSet<Integer>();
        for (int i = 1; i <= sqrt(number); i++)
            if (isFactor(number, i)) {
                factors.add(i);
                factors.add(number / i);
            }
        return factors;
    }

    static public int sum(Set<Integer> factors) {
        int sum = 0;
        for (Integer i : factors)
            sum += i.intValue();
        return sum;
    }

    static public boolean isPerfect(int number) {
        return sum(factors(number)) - number == number;
    }

    static public boolean isAbundant(int number) {
        return sum(factors(number)) - number > number;
    }

    static public boolean isDeficient(int number) {
        return sum(factors(number)) - number < number;
    }
}
