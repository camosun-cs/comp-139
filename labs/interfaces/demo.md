Session 1
=========
intro to lab assignment
interfaces

Session 2
=========

why BigInteger and BigDecimal are needed

BigDecimal - sign, unscaled value, and scale

long to Rational
double to Rational

BigDecimal decimal = BigDecimal.valueOf(source);
return new Rational(
    decimal.unscaledValue(),
    BigInteger.TEN.pow(decimal.scale())
);

String to Rational

step-by-step implement and test

Exceptions
