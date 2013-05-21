package nealford.ft.numberclassification;

import java.util.HashSet;
import java.util.Set;

/**
 * (probably) Copyright 2013 by Neal Ford. All rights reserved.
 */
public class ImpNumberClassifierSimple {
    private int number;

    public ImpNumberClassifierSimple(int number) {
      this.number = number;
    }

    public boolean isFactor(int potential) {
      return number % potential == 0;
    }

    public Set<Integer> getFactors() {
        Set<Integer> factors = new HashSet<>();
        factors.add(1);
        factors.add(number);
        for (int i = 2; i < number; i++)
            if (isFactor(i))
                factors.add(i);
        return factors;
    }

    public int sumFactors() {
        int sum = 0;
        for (int i : getFactors())
            sum += i;
        return sum;
    }

    public boolean isPerfect() {
        return sumFactors() - number == number;
    }

    public boolean isAbundant() {
        return sumFactors() - number > number;
    }

    public boolean isDeficient() {
        return sumFactors() - number < number;
    }
}
