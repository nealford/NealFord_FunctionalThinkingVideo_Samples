using System;

namespace NealFordFt.ErrorHandling
{
    /// <summary>
    /// A structure that accepts two exclusive values: only one at a time can be accessed.
    /// </summary>
    /// <remarks>
    /// A few differences with the Java implementation:
    /// - the IsRight/IsLeft properties are backed by a boolean, not by testing right/left value equals default. This
    /// allows a value being set to its default without meaning it is missing.
    /// - Added two InvalidOperationException guards when accessing Right and Left properties.
    /// </remarks>
    /// <typeparam name="TLeft">The type of the left value.</typeparam>
    /// <typeparam name="TRight">The type of the right value.</typeparam>
    public class Either<TLeft, TRight>
    {
        private TLeft left = default(TLeft);
        private TRight right = default(TRight);

        private readonly bool isRight;

        private Either(TLeft l, TRight r, bool isRightOption)
        {
            left = l;
            right = r;
            isRight = isRightOption;
        }

        public static Either<TLeft, TRight> MakeLeft(TLeft l)
        {
            return new Either<TLeft, TRight>(l, default(TRight), false);
        }

        public static Either<TLeft, TRight> MakeRight(TRight r)
        {
            return new Either<TLeft, TRight>(default(TLeft), r, true);
        }

        public TLeft Left
        {
            get
            {
                if (IsRight)
                    throw new InvalidOperationException("Left value is not accessible.");
                return left;
            }
        }

        public TRight Right
        {
            get
            {
                if (!IsRight)
                    throw new InvalidOperationException("Right value is not accessible.");
                return right;
            }
        }

        public bool IsLeft
        {
            get { return !isRight; }
        }

        public bool IsRight
        {
            get { return isRight; }
        }

        public void Fold(Action<TLeft> leftOption, Action<TRight> rightOption)
        {
            if (!IsRight)
                leftOption(left);
            else
                rightOption(right);
        }
    }
}
