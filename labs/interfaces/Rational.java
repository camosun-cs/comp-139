import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Objects;

/**
 * Immutable, arbitrary-precision rational numbers, expressed as fractions.
 * A Rational consists of integral numerator and denominator parts, either or
 * both of which may be negative. Two equal Rational values may be expressed as
 * different fractions, but their canonical forms will always be identical.
 *
 * <p>The denominator of a Rational value cannot be zero. As such, this class
 * cannot not represent infinite or NaN values, and will throw an
 * {@link ArithmeticException} in cases where such a value would be the result.</p>
 *
 * <p>Methods that return a Rational value will always return {@code Rational.ZERO}
 * or {@code Rational.ONE} if the resulting value of the operation is
 * <samp>0</samp> or <samp>1</samp> respectively. In all other cases, the result
 * might not be returned in canonical form.</p>
 *
 * <p>All methods and constructors for this class throw
 * {@link NullPointerException} when passed a <samp>null</samp>
 * object reference for any input parameter.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Rational_number">Wikipedia
 *     definition of Rational number</a>
 * @author Maxwell Terpstra <C0380979@intra.camosun.bc.ca>
 */
public class Rational extends Number implements Comparable<Rational> {
    private final BigInteger numerator;
    private final BigInteger denominator;

    /**
     * The canonical value <samp>0/1</samp>.
     * @see BigInteger#ZERO
     */
    public static final Rational ZERO = new Rational(0, 1);

    /**
     * The canonical value <samp>1/1</samp>.
     * @see BigInteger#ONE
     */
    public static final Rational ONE = new Rational(1, 1);


// CONSTRUCTORS + FACTORIES
////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a Rational number given a numerator and denominator.
     * @param n numerator value
     * @param d denominator value
     * @throws ArithmeticException if the given denominator is zero.
     */
    public Rational(long n, long d) {
        if (d == 0) { throw new ArithmeticException("denominator must not be zero"); }
        this.numerator = BigInteger.valueOf(n);
        this.denominator = BigInteger.valueOf(d);
    }

    /**
     * Creates a Rational number given a numerator and denominator.
     * @param n numerator value
     * @param d denominator value
     * @throws ArithmeticException if the given denominator is zero.
     * @throws NullPointerException if either parameter is null.
     */
    public Rational(BigInteger n, BigInteger d) {
        this.numerator = Objects.requireNonNull(n);
        this.denominator = Objects.requireNonNull(d);
        if (d.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("denominator must not be zero");
        }
    }

    /**
     * Translates the given double into an equivalent Rational value.
     *
     * <p>If {@code source} has a value of <samp>0</samp> or <samp>1</samp>,
     * returns {@code Rational.ZERO} or {@code Rational.ONE} respectively.</p>
     *
     * <p>Note that the results of this method may be confusing if invoked with
     * a literal floating-point value that is not exactly representable by Java.
     * In all cases, the returned Rational will be equivalent to the actual
     * binary value of {@code source} (for example, calling
     * {@code Rational.valueOf(0.1)} will <em>not</em> result in a value of
     * <samp>1/10</samp>). For more intuitively predictable results, use the
     * {@link #valueOf(String)} method instead.</p>
     *
     * @param source a value to convert
     * @return the given value as a Rational instance
     * @throws IllegalArgumentException if source is NaN or infinite.
     * @see BigDecimal#valueOf(double)
     */
    public static Rational valueOf(double source) {
        if (Double.isNaN(source)) {
            throw new IllegalArgumentException("Rational cannot express NaN values");
        } else if (Double.isInfinite(source)) {
            throw new IllegalArgumentException("Rational cannot express infinite values");
        } else if (source == 0) {
            return Rational.ZERO;
        } else if (source == 1) {
            return Rational.ONE;
        } else {
            BigDecimal decimal = BigDecimal.valueOf(source);
            return new Rational(
                decimal.unscaledValue(),
                BigInteger.TEN.pow(decimal.scale())
            );
        }
    }

    /**
     * Translates the given long into an equivalent Rational value.
     *
     * <p>If {@code source} has a value of <samp>0</samp> or <samp>1</samp>,
     * returns {@code Rational.ZERO} or {@code Rational.ONE} respectively.</p>
     *
     * <p>The <var>denominator</var> of any Rational returned by this method
     * will always be <samp>1</samp>.</p>
     *
     * @param source a value to convert
     * @return the given value as a Rational instance
     * @see BigInteger#valueOf(long)
     */
    public static Rational valueOf(long source) {
        if (source == 0) {
            return Rational.ZERO;
        } else if (source == 1) {
            return Rational.ONE;
        } else {
            return new Rational(
                BigInteger.valueOf(source),
                BigInteger.ONE
            );
        }
    }

    /**
     * Translates the given String into an equivalent Rational value.
     *
     * <p>The {@code source} value may be given in the form
     * <samp>"<var>a</var> / <var>b</var>"</samp> where <var>a</var> and
     * <var>b</var> are integral values and <var>b</var> is non-zero.</p>
     *
     * <p>If {@code source} has a value of <samp>0</samp> or <samp>1</samp>,
     * returns {@code Rational.ZERO} or {@code Rational.ONE} respectively.</p>
     *
     * @param source fractional or numeric value to convert
     * @return the given value as a Rational instance
     * @throws NumberFormatException if source contains a {@code '/'} but
     *     has non-integral numerator or denominator values, or if given any
     *     other String that cannot be interpreted by
     *     {@link BigInteger#BigInteger(String)}
     * @see BigDecimal#BigDecimal(String)
     */
    public static Rational valueOf(String source) {
        int slashIndex = source.indexOf('/');
        if (slashIndex != -1) {
            BigInteger num = new BigInteger(source.substring(0, slashIndex).trim());
            BigInteger denom = new BigInteger(source.substring(slashIndex + 1).trim());
            if (denom.equals(BigInteger.ONE)) {
                if (num.equals(BigInteger.ONE)) {
                    return Rational.ONE;
                } else if (num.equals(BigInteger.ZERO)) {
                   return Rational.ZERO;
                }
            }
            return new Rational(num, denom);
        } else {
            BigDecimal decimal = new BigDecimal(source.trim());
            if (decimal.equals(BigDecimal.ONE)) {
                return Rational.ONE;
            } else if (decimal.equals(BigDecimal.ZERO)) {
                return Rational.ZERO;
            } else {
                return new Rational(
                    decimal.unscaledValue(),
                    BigInteger.TEN.pow(decimal.scale())
                );
            }
        }
    }

    private Rational constantOrNew(BigInteger num, BigInteger denom) {
        if (num.equals(BigInteger.ZERO)) {
            return Rational.ZERO;
        } else if (num.equals(denom)) {
            return Rational.ONE;
        } else {
            return new Rational(num, denom);
        }
    }

// STATE INFORMATION
////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the sign of this value.
     * @return <samp>-1</samp> if this value is negative, <samp>+1</samp> if
     *     positive, or <samp>0</samp> if exactly zero.
     * @see BigInteger#signum()
     */
    public int signum() {
        int numSign = this.numerator.signum();
        int denSign = this.denominator.signum();
        if (numSign == 0) {
            return 0;
        } else if (numSign < 0 && denSign < 0) {
            return 1;
        } else if (numSign < 0 || denSign < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Returns true if this is a proper fraction.
     * @return true if the absolute value of the numerator is less than the
     *     absolute value of the denominator; false otherwise.
     * @see <a href="https://en.wikipedia.org/wiki/Fraction#Proper_and_improper_fractions">
     *     Wikipedia definition of proper fractions</a>
     */
    public boolean isProper() {
        BigInteger topAbs = this.numerator.abs();
        return topAbs.min(this.denominator.abs()) == topAbs;
    }

    /**
     * Returns true if this value has a non-terminating decimal expansion.
     * @return false if this value can be represented by a
     *     {@link BigDecimal} without rounding; false otherwise.
     * @see BigDecimal#divide(BigDecimal)
     */
    public boolean isRepeatingDecimal() {
        try {
            new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator));
            return false;
        } catch (ArithmeticException e) {
            return true;
        }
    }

    /**
     * Get the numerator part of this Rational number's fractional form.
     * @return the numerator value
     */
    public BigInteger getNumerator() {
        return this.numerator;
    }

    /**
     * Get the denominator part of this Rational number's fractional form.
     * @return the denominator value
     */
    public BigInteger getDenominator() {
        return this.denominator;
    }

    /**
     * Convert this Rational to its canonical form - an irreducible fraction
     * with a positive denominator.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if this value is
     * <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @return an equivalent Rational value in canonical form.
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Irreducible_fraction">
     *     Wikipedia definition of rational canonical form.</a>
     * @see BigInteger#gcd(BigInteger)
     */
    public Rational canonical() {
        if (this.numerator.equals(BigInteger.ZERO)) {
            return Rational.ZERO;
        } else if (this.numerator.equals(this.denominator)) {
            return Rational.ONE;
        } else {
            BigInteger gcd = this.numerator.gcd(this.denominator);
            if (gcd.equals(BigInteger.ONE) && this.denominator.signum() == 1) {
                return this;
            } else {
                int sign = this.signum();
                BigInteger top = this.numerator.abs().divide(gcd);
                BigInteger bottom = this.denominator.abs().divide(gcd);
                if (sign < 0) {
                    top = top.negate();
                }
                return constantOrNew(top, bottom);
            }
        }
    }

// MATHEMATICAL OPERATORS
////////////////////////////////////////////////////////////////////////////////

    /**
     * Computes the value of this Rational plus the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param addend a number to add
     * @return <code>this + val</code>
     * @see #valueOf(double)
     * @see #add(Rational)
     */
    public Rational add(double addend) {
        return this.add(Rational.valueOf(addend));
    }

    /**
     * Computes the value of this Rational plus the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param addend a number to add
     * @return <code>this + val</code>
     * @see #valueOf(long)
     * @see #add(Rational)
     */
    public Rational add(long addend) {
        return this.add(Rational.valueOf(addend));
    }

    /**
     * Computes the value of this Rational plus the given other Rational.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param addend a Rational value to add
     * @return <code>this + val</code>
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Addition">
     *     Wikipedia entry on adding rational numbers</a>
     * @see BigInteger#multiply(BigInteger)
     */
    public Rational add(Rational addend) {
        BigInteger thisTop = this.numerator.multiply(addend.denominator);
        BigInteger thatTop = addend.numerator.multiply(this.denominator);
        return this.constantOrNew(thisTop.add(thatTop),
            this.denominator.multiply(addend.denominator)
        );
    }

    /**
     * Computes the value of this Rational minus the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param subtrahend a number to subtract
     * @return <code>this - val</code>
     * @see #valueOf(long)
     * @see #subtract(Rational)
     */
    public Rational subtract(long subtrahend) {
        return this.subtract(Rational.valueOf(subtrahend));
    }

    /**
     * Computes the value of this Rational minus the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param subtrahend a number to subtract
     * @return <code>this - val</code>
     * @see #valueOf(double)
     * @see #subtract(Rational)
     */
    public Rational subtract(double subtrahend) {
        return this.subtract(Rational.valueOf(subtrahend));
    }

    /**
     * Computes the value of this Rational minus the given other Rational.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param subtrahend a Rational value to subtract
     * @return <code>this - val</code>
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Subtraction">
     *     Wikipedia entry on subtracting rational numbers</a>.
     * @see BigInteger#multiply(BigInteger)
     */
    public Rational subtract(Rational subtrahend) {
        BigInteger thisTop = this.numerator.multiply(subtrahend.denominator);
        BigInteger thatTop = subtrahend.numerator.multiply(this.denominator);
        return this.constantOrNew(thisTop.subtract(thatTop),
            this.denominator.multiply(subtrahend.denominator)
        );
    }

    /**
     * Computes the value of this Rational multiplied by the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param val a number to multiply by
     * @return <code>this * val</code>
     * @see #valueOf(double)
     * @see #multiply(Rational)
     */
    public Rational multiply(double val) {
        return this.multiply(Rational.valueOf(val));
    }

    /**
     * Computes the value of this Rational multiplied by the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param val a number to multiply by
     * @return <code>this * val</code>
     * @see #valueOf(long)
     * @see #multiply(Rational)
     */
    public Rational multiply(long val) {
        // simple: return this.multiply(Rational.valueOf(val));
        // but this does a bit less work:
        return constantOrNew(
            this.numerator.multiply(BigInteger.valueOf(val)),
            this.denominator
        );
    }

    /**
     * Computes the value of this Rational multiplied by the given other Rational.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param val a Rational value to multiply by
     * @return <code>this * val</code>
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Multiplication">
     *     Wikipedia entry on multiplying rational numbers</a>.
     */
    public Rational multiply(Rational val) {
        return constantOrNew(
            this.numerator.multiply(val.numerator),
            this.denominator.multiply(val.numerator)
        );
    }

    /**
     * Computes the value of this Rational divided by the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param divisor a number to divide by
     * @return <code>this / val</code>
     * @throws ArithmeticException if val is zero
     * @see #valueOf(double)
     * @see #divide(Rational)
     */
    public Rational divide(double divisor) {
        return this.divide(Rational.valueOf(divisor));
    }

    /**
     * Computes the value of this Rational divided by the given number.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param divisor a number to divide by
     * @return <code>this / val</code>
     * @throws ArithmeticException if val is zero
     * @see #valueOf(long)
     * @see #divide(Rational)
     */
    public Rational divide(long divisor) {
        // simple: this.divide(Rational.valueOf(divisor));
        // but this does a bit less work:
        if (divisor == 0) {
            throw new ArithmeticException("divide by zero");
        } else {
            return constantOrNew(this.numerator,
                this.denominator.multiply(BigInteger.valueOf(divisor))
            );
        }
    }

    /**
     * Computes the value of this Rational divided by the given other Rational.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param divisor a Rational value to divide by
     * @return <code>this / val</code>
     * @throws ArithmeticException if val is zero
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Division">
     *     Wikipedia entry on division of rational numbers</a>.
     * @see #multiply(Rational)
     */
    public Rational divide(Rational divisor) {
        if (divisor.numerator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("divide by zero");
        }
        return this.multiply(divisor.reciprocal());
    }

    /**
     * Computes the value of this Rational raised to the power of the given
     * exponent.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @param exponent exponent to raise this value by
     * @return <code>this<sup>exponent</sup></code>
     * @throws ArithmeticException if this value is zero and a negative
     *     exponent is given.
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Exponentiation_to_integer_power">
     *     Wikipedia definition of exponentiation to an integer power</a>
     * @see BigInteger#pow(int)
     */
    public Rational pow(int exponent) {
        if (exponent == 0) {
            return Rational.ONE;
        } else if (exponent < 0) {
            if (this.numerator.equals(BigInteger.ZERO)) {
                throw new ArithmeticException("negative power of zero");
            }
            return constantOrNew(
                this.denominator.pow(-exponent),
                this.numerator.pow(-exponent)
            );
        } else {
            return constantOrNew(
                this.numerator.pow(exponent),
                this.denominator.pow(exponent)
            );
        }
    }

    /**
     * Returns a Rational that is the additive inverse of this value.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @return <code>this * -1</code>
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Inverse">
     *     Wikipedia definition of rational inverses</a>
     * @see BigInteger#negate()
     */
    public Rational negate() {
        return constantOrNew(this.numerator.negate(), this.denominator);
    }

    /**
     * Returns a Rational that is the absolute value of this Rational.
     *
     * <p>Returns {@code Rational.ZERO} or {@code Rational.ONE} if the resulting
     * value is <samp>0</samp> or <samp>1</samp> respectively.</p>
     *
     * @return <code>|this|</code>
     * @see <a href="https://en.wikipedia.org/wiki/Absolute_value">Wikipedia
     *     definition of absolute value</a>
     * @see BigInteger#abs()
     */
    public Rational abs() {
        return constantOrNew(this.numerator.abs(), this.denominator.abs());
    }

    /**
     * Returns a Rational that is the multiplicative inverse of this value.
     *
     * Returns {@code Rational.ONE} if the resulting value is <samp>1</samp>.
     *
     * @return <code>this<sup>-1</sup></code>
     * @throws ArithmeticException if the value of {@code this} is zero
     * @see <a href="https://en.wikipedia.org/wiki/Rational_number#Inverse">
     *     Wikipedia definition of rational inverses</a>
     */
    public Rational reciprocal() {
        if (this.numerator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("reciprocal of zero");
        } else {
            return constantOrNew(this.denominator, this.numerator);
        }
    }

// VALUE CONVERSION
////////////////////////////////////////////////////////////////////////////////

    /**
     * Converts this Rational value to an {@code int}. This is a
     * <em>narrowing conversion</em> and any decimal fractional part will be
     * lost (rounded down).
     * @return an approximately equivalent integral value
     * @throws ArithmeticException if the integral magnitude of this value
     *     is too great to be represented by an {@code int}
     * @see BigDecimal#BigDecimal(BigInteger)
     * @see BigDecimal#divideToIntegralValue(BigDecimal)
     * @see BigDecimal#intValueExact()
     */
    @Override
    public int intValue() {
        BigDecimal num = new BigDecimal(this.numerator);
        BigDecimal den = new BigDecimal(this.denominator);
        return num.divideToIntegralValue(den).intValueExact();
    }

    /**
     * Converts this Rational value to a {@code long}. This is a
     * <em>narrowing conversion</em> and any decimal fractional part will be
     * lost (rounded down).
     * @return an approximately equivalent integral value
     * @throws ArithmeticException if the integral magnitude of this value
     *     is too great to be represented by a {@code long}
     * @see BigDecimal#BigDecimal(BigInteger)
     * @see BigDecimal#divideToIntegralValue(BigDecimal)
     * @see BigDecimal#longValueExact()
     */
    @Override
    public long longValue() {
        BigDecimal num = new BigDecimal(this.numerator);
        BigDecimal den = new BigDecimal(this.denominator);
        return num.divideToIntegralValue(den).longValueExact();
    }

    /**
     * Converts this Rational value to a {@code float}. If the value has
     * too great a magnitude to be represented as a {@code float}, it will
     * be converted to {@link Float#NEGATIVE_INFINITY} or
     * {@link Float#POSITIVE_INFINITY} as appropriate. This is a
     * <em>narrowing conversion</em> and may result in loss of precision, even
     * if the result is finite.
     * @see BigDecimal#BigDecimal(BigInteger)
     * @see BigDecimal#divide(BigDecimal, MathContext)
     * @see BigDecimal#floatValue()
     */
    @Override
    public float floatValue() {
        BigDecimal num = new BigDecimal(this.numerator);
        BigDecimal den = new BigDecimal(this.denominator);
        return num.divide(den, MathContext.DECIMAL128).floatValue();
    }

    /**
     * Converts this Rational value to a {@code double}. If the value has
     * too great a magnitude to be represented as a {@code double}, it will
     * be converted to {@link Double#NEGATIVE_INFINITY} or
     * {@link Double#POSITIVE_INFINITY} as appropriate. This is a
     * <em>narrowing conversion</em> and may result in loss of precision, even
     * if the result is finite.
     * @see BigDecimal#BigDecimal(BigInteger)
     * @see BigDecimal#divide(BigDecimal, MathContext)
     * @see BigDecimal#doubleValue()
     */
    @Override
    public double doubleValue() {
        BigDecimal num = new BigDecimal(this.numerator);
        BigDecimal den = new BigDecimal(this.denominator);
        return num.divide(den, MathContext.DECIMAL128).doubleValue();
    }

    /**
     * Returns the decimal expansion of this Rational value.
     * @return an equivalent BigDecimal value
     * @throws ArithmeticException if this value has a non-terminating decimal
     *     expansion.
     * @see BigDecimal#BigDecimal(BigInteger)
     * @see BigDecimal#divide(BigDecimal)
     */
    public BigDecimal decimalValue() {
        BigDecimal num = new BigDecimal(this.numerator);
        BigDecimal den = new BigDecimal(this.denominator);
        return num.divide(den);
    }

    /**
     * Returns the string representation of this Rational. If the denominator
     * is <samp>1</samp>, only the numerator will shown. Otherwise, the value
     * is displayed in the format <samp>"<var>numerator</var>/<var>denominator</var>"</samp>.
     * @see BigInteger#toString()
     */
    @Override
    public String toString() {
        if (this.denominator.equals(BigInteger.ONE)) {
            return this.numerator.toString();
        } else {
            return this.numerator + "/" + this.denominator;
        }
    }


// COMPARISONS
////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the largest Rational value given.
     * @param vals values to compare
     * @return the maximum value
     * @see BigInteger#multiply(BigInteger)
     * @see BigInteger#compareTo(Object)
     */
    public static Rational max(Rational ...vals) {
        Rational best = vals[0];
        for (int i = 1; i < vals.length; i++) {
            // cross-multiply numerators
            BigInteger bestNum = best.numerator.multiply(vals[i].denominator);
            BigInteger newNum = vals[i].numerator.multiply(best.numerator);
            // update best if newNum is larger
            if (bestNum.compareTo(newNum) < 0) {
                best = vals[i];
            }
        }
        return best;
    }

    /**
     * Returns the smallest Rational value given.
     * @param vals values to compare
     * @return the minimum value
     * @see BigInteger#multiply(BigInteger)
     * @see BigInteger#compareTo(Object)
     */
    public static Rational min(Rational ...vals) {
        Rational best = vals[0];
        for (int i = 1; i < vals.length; i++) {
            // cross-multiply numerators
            BigInteger bestNum = best.numerator.multiply(vals[i].denominator);
            BigInteger newNum = vals[i].numerator.multiply(best.numerator);
            // update best if newNum is smaller
            if (bestNum.compareTo(newNum) > 0) {
                best = vals[i];
            }
        }
        return best;
    }

    /**
     * @see #canonical()
     * @see BigInteger#compareTo(BigInteger)
     * @see BigInteger#multiply(BigInteger)
     */
    @Override
    public int compareTo(Rational val) {
        if (this == val) {
            return 0;
        } else {
            // simple: compare canonical forms
            // faster: compare by raising both to common denominator
            BigInteger thisNum = this.numerator.multiply(val.denominator);
            BigInteger thatNum = val.numerator.multiply(this.denominator);
            return thisNum.compareTo(thatNum);
        }
    }

    /**
     * Test whether this Rational is equivalent to the given object.
     *
     * <p>Returns true if and only if {@code this} and {@code other} are the
     * same class and represent the same rational number.</p>
     *
     * @param other the object to compare to
     * @return {@code this == other}
     * @see #canonical()
     * @see BigInteger#multiply(BigInteger)
     * @see BigInteger#equals(Object)
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (this.getClass() == other.getClass()) {
            // simple: compare canonical forms
            // faster: compare by raising both to common denominator
            Rational that = (Rational) other;
            BigInteger thisNum = this.numerator.multiply(that.denominator);
            BigInteger thatNum = that.numerator.multiply(that.denominator);
            return thisNum.equals(thatNum);
        } else {
            return false;
        }
    }

    /**
     * Returns the hash code for this Rational.
     * @return hash code for this Rational
     * @see #canonical()
     * @see Objects#hash(Object...)
     * @see Object#equals(Object)
     */
    @Override
    public int hashCode() {
        // note: must use canonical form to fulfill hashCode/equals contract
        Rational canonical = this.canonical();
        return Objects.hash(canonical.numerator, canonical.denominator);
    }

}
