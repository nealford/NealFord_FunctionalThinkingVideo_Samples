package com.nealford.conf.ft.numberclassifier;

import fj.F2;
import fj.data.List;

import static fj.data.List.list;

import static java.lang.Math.round;
import static java.lang.System.out;

public class FunWithFP {

    static public int sum(List<Integer> factors) {
        return factors.foldLeft(fj.function.Integers.add, 0);
    }

    static public int addOnlyOddNumbersIn(List<Integer> numbers) {
        return numbers.foldLeft(new F2<Integer, Integer, Integer>() {
            @Override
            public Integer f(Integer i1, Integer i2) {
                return (!(i2 % 2 == 0)) ? i1 + i2 : i1;
            }
        }, 0);
    }

    public static void main(String[] args) {
        List sumTest = list(3, 5, 7, 3, 1);
        out.println("Sum is " + sum(sumTest));
        List numList = list(2, 5, 8, 3, 11, 10, 4);
        out.println("Sum of only odd numbers is " + addOnlyOddNumbersIn(numList));

    }
}
