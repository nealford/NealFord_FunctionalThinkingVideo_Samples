package com.nealford.ft.errorhandling;

import fj.P1;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeralParser {
    private static final int MIN = 0;
    private static final int MAX = 1000;

    public static Either<Exception, Integer> parseNumber(String s) {
        if (! s.matches("[IVXLXCDM]+"))
            return Either.left(new Exception("Invalid Roman numeral"));
        else
            return Either.right(new RomanNumeral(s).toInt());
    }

    public static P1<Either<Exception, Integer>> parseNumberLazy(final String s) {
        if (! s.matches("[IVXLXCDM]+"))
            return new P1<Either<Exception, Integer>>() {
                public Either<Exception, Integer> _1() {
                    return Either.left(new Exception("Invalid Roman numeral"));
                }
            };
        else
            return new P1<Either<Exception, Integer>>() {
                public Either<Exception, Integer> _1() {
                    return Either.right(new RomanNumeral(s).toInt());
                }
            };
    }

    public static Either<Exception, Integer> parseNumberDefaults(final String s) {
        if (! s.matches("[IVXLXCDM]+"))
            return Either.left(new Exception("Invalid Roman numeral"));
        else {
            int number = new RomanNumeral(s).toInt();
            return Either.right(new RomanNumeral(number >= MAX ? MAX : number).toInt());
        }
    }

    public static Map<String, Object> divide(int x, int y) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (y == 0)
            result.put("exception", new Exception("div by zero"));
        else
            result.put("answer", (double) x / y);
        return result;
    }
}
