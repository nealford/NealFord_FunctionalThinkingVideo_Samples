using System;
using NealFordFt.ErrorHandling;

namespace NealFordFt
{
    internal class Program
    {
        private const string invalidRomanNumeral = "Invalid Roman numeral";

        /// <summary>
        /// Defines the entry point of the application.
        /// </summary>
        /// <param name="args">The arguments.</param>
        static void Main(string[] args)
        {
            var prog = new Program();
            prog.RunTests();
        }

        private void RunTests()
        {
            Console.WriteLine("Running Tests:");
            RunTest(maps_success, "maps_success");
            RunTest(maps_failure, "maps_failure");
            RunTest(either_left, "either_left");
            RunTest(either_right, "either_right");
            RunTest(parsing_success, "parsing_success");
            RunTest(parsing_failure, "parsing_failure");
            RunTest(parse_lazy, "parse_lazy");
            RunTest(parse_lazy_exception, "parse_lazy_exception");
            RunTest(parse_defaults_normal, "parse_defaults_normal");
            RunTest(parse_defaults_triggered, "parse_defaults_triggered");
            RunTest(zero_as_right_sucess, "zero_as_right_sucess");
            Console.WriteLine("Done.");
            Console.ReadKey();
        }


        #region Actual Tests

        public void zero_as_right_sucess()
        {
            var e = Either<Exception, int>.MakeRight(0);
            AssertEquals(true, e.IsRight);
            AssertEquals(0, e.Right);
        }

        public void maps_success()
        {
            var result = RomanNumeralParser.Divide(4, 2);
            AssertEquals(2.0, (double)result["answer"], 0.1);
        }

        public void maps_failure()
        {
            var result = RomanNumeralParser.Divide(4, 0);
            AssertEquals("div by zero", ((Exception)result["exception"]).Message);
        }

        public void either_left()
        {
            var result = new string[1];
            var e = Either<string, int>.MakeLeft("foo");
            e.Fold(
                x => result[0] = x,
                x => result[0] = "Integer: " + x);

            AssertEquals(result[0], "foo");
        }

        public void either_right()
        {
            var result = new string[1];
            var e = Either<string, int>.MakeRight(4);
            e.Fold(
                x => result[0] = x,
                x => result[0] = "Integer: " + x);

            AssertEquals(result[0], "Integer: 4");
        }

        public void parsing_success()
        {
            var result = RomanNumeralParser.ParseNumber("XLII");
            AssertEquals(42, result.Right);
        }

        public void parsing_failure()
        {
            var result = RomanNumeralParser.ParseNumber("FOO");     
            AssertEquals(invalidRomanNumeral, result.Left.Message);
        }

        public void parse_lazy()
        {
            var result = RomanNumeralParser.ParseNumberLazy("XLII");
            AssertEquals(42, result().Right);
        }

        public void parse_lazy_exception()
        {
            var result = RomanNumeralParser.ParseNumberLazy("FOO");
            AssertEquals(true, result().IsLeft);
            AssertEquals(invalidRomanNumeral, result().Left.Message);
        }

        public void parse_defaults_normal()
        {
            var result = RomanNumeralParser.ParseNumberDefaults("XLII");
            AssertEquals(42, result.Right);
        }

        public void parse_defaults_triggered()
        {
            var result = RomanNumeralParser.ParseNumberDefaults("MM");
            AssertEquals(1000, result.Right);
        }

        #endregion

        #region Micro Test Fx

        private void RunTest(Action test, string testName)
        {
            try
            {
                Console.Write("{0}: ", testName);
                test();
                LogSuccess();
            }
            catch (Exception ex)
            {
                LogError(ex);
            }
        }
        private static void AssertEquals(double expected, double actual, double delta = 0.0)
        {
            if (Math.Abs(expected - actual) > delta)
                throw new Exception(string.Format("Test Failed: {0}(expected) <> {1}(actual)", expected, actual));
        }

        ////private static void AssertEquals(string expected, string actual)
        ////{
        ////    if (expected != actual)
        ////        throw new Exception(string.Format("Test Failed: {0}(expected) <> {1}(actual)", expected, actual));
        ////}

        private static void AssertEquals<T>(T expected, T actual)
        {
            if (!object.Equals(expected, actual))
                throw new Exception(string.Format("Test Failed: {0}(expected) <> {1}(actual)", expected, actual));
        }

        private static void LogError(Exception ex)
        {
            LogError(ex.Message);
        }

        private static void LogError(string message)
        {
            if (string.IsNullOrEmpty(message))
                LogMessage("ERROR", ConsoleColor.Red);
            else LogMessage(string.Format("ERROR: {0}", message), ConsoleColor.Red);
        }

        private static void LogSuccess(string message = "")
        {
            if (string.IsNullOrEmpty(message))
                LogMessage("SUCCESS", ConsoleColor.Green);
            else LogMessage(string.Format("SUCCESS: {0}", message), ConsoleColor.Green);
        }

        private static void LogMessage(string message, ConsoleColor color)
        {
            Console.ForegroundColor = color;
            Console.WriteLine(message);
            Console.ResetColor();
        }

        #endregion
    }
}
