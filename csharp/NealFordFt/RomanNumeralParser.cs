using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

using NealFordFt.ErrorHandling;

namespace NealFordFt
{
    public static class RomanNumeralParser
    {
        private static readonly int MIN = 0;
        private static readonly int MAX = 1000;

        public static Either<Exception, int> ParseNumber(string s)
        {
            if (!Regex.IsMatch(s, "[IVXLXCDM]+"))
                return Either<Exception, int>.MakeLeft(new Exception("Invalid Roman numeral"));
            else
                return Either<Exception, int>.MakeRight(new RomanNumeral(s).ToInt());
        }

        public static Func<Either<Exception, int>> ParseNumberLazy(string s)
        {
            if (!Regex.IsMatch(s, "[IVXLXCDM]+"))
                return () => Either<Exception, int>.MakeLeft(new Exception("Invalid Roman numeral"));
            else
                return () => Either<Exception, int>.MakeRight(new RomanNumeral(s).ToInt());
        }

        public static Either<Exception, int> ParseNumberDefaults(string s)
        {
            if (!Regex.IsMatch(s, "[IVXLXCDM]+"))
                return Either<Exception, int>.MakeLeft(new Exception("Invalid Roman numeral"));
            else
            {
                int number = new RomanNumeral(s).ToInt();
                return Either<Exception, int>.MakeRight(new RomanNumeral(number >= MAX ? MAX : number).ToInt());
            }
        }

        public static IDictionary<string, object> Divide(int x, int y)
        {
            var result = new Dictionary<string, object>();
            if (y == 0)
                result.Add("exception", new Exception("div by zero"));
            else
                result.Add("answer", (double)x / (double)y);

            return result;
        }
    }
}
