package nealford.ft.totallylazy;

public class NumberClassifier {
    public static Predicate<Number> isFactor(Number n) {
        return where(remainder(n), is(zero));
    }

    public static Sequence<Number> getFactors(final Number n){
        return range(1, n).filter(isFactor(n));
    }

    public static Sequence<Number> factors(final Number n) {
        return getFactors(n).memorise();
    }

    public static Number sumFactors(Number n){
        return factors(n).reduce(sum);
    }

    public static boolean isPerfect(Number n){
        return equalTo(n, subtract(sumFactors(n), n));
    }

    public static boolean isAbundant(Number n) {
      return greaterThan(subtract(sumFactors(n), n), n);
    }

    public static boolean isDeficient(Number n) {
      return lessThan(subtract(sumFactors(n), n), n);
    }


}
