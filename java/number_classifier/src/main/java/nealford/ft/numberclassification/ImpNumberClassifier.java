package nealford.ft.numberclassification;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static java.lang.Math.sqrt;

public class ImpNumberClassifier {
    private int number;
    private Map<Integer, Integer> cache;

    public ImpNumberClassifier(int number) {
        this.number = number;
        this.cache = new HashMap<>();
    }

    private boolean isFactor(int candidate) {
        return number % candidate == 0;
    }

    private Set<Integer> getFactors() {
        Set<Integer> factors = new HashSet<>();
        factors.add(1);
        factors.add(number);
        for (int i = 2; i < sqrt(number) + 1; i++)
            if (isFactor(i)) {
                factors.add(i);
                factors.add(number / i);
            }
        return factors;
    }

    private int sumFactors() {
        int sum = 0;
        for (int i : getFactors())
            sum += i;
        return sum;
    }

    private int cachedSum() {
        if (cache.containsKey(number))
            return cache.get(number);
        else {
            int sum = sumFactors();
            cache.put(number, sum);
            return sum;
        }
    }

    public boolean isPerfect() {
        return cachedSum() - number == number;
    }

    public boolean isAbundant() {
        return cachedSum() - number > number;
    }

    public boolean isDeficient() {
        return cachedSum() - number < number;
    }
}
