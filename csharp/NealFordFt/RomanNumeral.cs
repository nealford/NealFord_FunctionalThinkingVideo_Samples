using System;

namespace NealFordFt
{
    public class RomanNumeral
    {
        private const string numeralMustBePositive = "Value of RomanNumeral must be positive.";
        private const string numeralMustBe3999OrLess = "Value of RomanNumeral must be 3999 or less.";
        private const string doesNotDefineARomanNumeral = "An empty string does not define a Roman numeral.";

        private static int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        private static string[] letters = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        private readonly int num;

        /// <summary>
        /// Initializes a new instance of the <see cref="RomanNumeral"/> class.
        /// </summary>
        /// <param name="arabic">The arabic form of the numeral.</param>
        /// <exception cref="System.FormatException">
        /// </exception>
        public RomanNumeral(int arabic)
        {
            if (arabic < 1)
                throw new FormatException(numeralMustBePositive);
            if (arabic > 3999)
                throw new FormatException(numeralMustBe3999OrLess);

            num = arabic;
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="RomanNumeral"/> class.
        /// </summary>
        /// <param name="roman">The roman numeral expressed as a string.</param>
        /// <exception cref="System.FormatException">
        /// Illegal character in roman numeral.
        /// or
        /// Roman numeral must have value 3999 or less.
        /// </exception>
        public RomanNumeral(string roman)
        {
            if (string.IsNullOrEmpty(roman))
                throw new FormatException(doesNotDefineARomanNumeral);
            roman = roman.ToUpperInvariant();

            int positionInString = 0;
            int arabicEquivalent = 0;

            while (positionInString < roman.Length)
            {
                char letter = roman[positionInString];
                int number = LetterToNumber(letter);
                if (number < 0)
                    throw new FormatException("Illegal character \"" + letter + "\" in roman numeral.");

                positionInString++;
                if (positionInString == roman.Length)
                    arabicEquivalent += number;
                else
                {
                    int nextNumber = LetterToNumber(roman[positionInString]);
                    if (nextNumber > number)
                    {
                        arabicEquivalent += (nextNumber - number);
                        positionInString++;
                    }
                    else arabicEquivalent += number;
                }
            }

            if (arabicEquivalent > 3999)
                throw new FormatException("Roman numeral must have value 3999 or less.");
            num = arabicEquivalent;
        }

        /// <summary>
        /// Returns a <see cref="System.String" /> that represents this instance.
        /// </summary>
        /// <returns>
        /// A <see cref="System.String" /> that represents this instance.
        /// </returns>
        public override string ToString()
        {
            var romanNumeral = string.Empty;
            int remainingPartToConvert = num;
            for (int i = 0; i < numbers.Length; i++)
            {
                while (remainingPartToConvert >= numbers[i])
                {
                    romanNumeral += letters[i];
                    remainingPartToConvert -= numbers[i];
                }
            }

            return romanNumeral;
        }

        /// <summary>
        /// Returns a <see cref="System.Int32" /> that represents this instance.
        /// </summary>
        /// <returns>
        /// A <see cref="System.Int32" /> that represents this instance.
        /// </returns>
        public int ToInt()
        {
            return num;
        }

        private static int LetterToNumber(char letter)
        {
            switch (letter)
            {
                case 'I': return 1;
                case 'V': return 5;
                case 'X': return 10;
                case 'L': return 50;
                case 'C': return 100;
                case 'D': return 500;
                case 'M': return 1000;
                default: return -1;
            }
        }
    }
}
