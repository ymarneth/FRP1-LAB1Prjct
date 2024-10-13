package fract

import scala.annotation.{tailrec, targetName}
import scala.language.implicitConversions

final class Fract(val numer: Int, val denom: Int) {
  require(denom != 0, "Denominator cannot be zero")

  override def toString: String = s"$numer/$denom"

  override def equals(obj: Any): Boolean = obj match {
    case f: Fract => numer == f.numer && denom == f.denom
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(numer, denom)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  @targetName("add")
  def +(that: Fract): Fract = Fract(numer * that.denom + that.numer * denom, denom * that.denom)

  @targetName("subtract")
  def -(that: Fract): Fract = Fract(numer * that.denom - that.numer * denom, denom * that.denom)

  @targetName("multiply")
  def *(multiplier: Fract): Fract = Fract(numer * multiplier.numer, denom * multiplier.denom)

  @targetName("divide")
  def /(that: Fract): Fract = this * that.rec

  // returns the reciprocal value
  def rec: Fract = Fract(denom, numer)

  // returns the negated Fract
  private def neg: Fract = Fract(-numer, denom)
}

// Compute the greatest common divisor (GCD)
@tailrec
private[fract] def computeGcd(a: Int, b: Int): Int = {
  if (a < 0 || b < 0) computeGcd(a.abs, b.abs)
  else if (b == 0) a else computeGcd(b, a % b)
}

object Fract {
  extension (n: Int) {
    @targetName("addFractToInt")
    def +(that: Fract): Fract = Fract(n * that.denom + that.numer, that.denom)

    @targetName("subtractFractFromInt")
    def -(that: Fract): Fract = Fract(n * that.denom - that.numer, that.denom)

    @targetName("multiplyFractWithInt")
    def *(that: Fract): Fract = Fract(n * that.numer, that.denom)

    @targetName("divideIntByFract")
    def /(that: Fract): Fract = Fract(n * that.denom, that.numer)

    @targetName("fractionShorthand")
    def ~/~(d: Int): Fract = Fract(n, d)
  }

  def apply(n: Int, d: Int): Fract = {
    val gcd = computeGcd(n, d)

    // if both the numerator and denominator are negative, resolve to positive
    if (n < 0 && d < 0) new Fract(n.abs / gcd, d.abs / gcd)
    // if the numerator is negative, move the sign to the numerator
    else if (d < 0) new Fract(-(n / gcd), d.abs / gcd)
    else new Fract(n / gcd, d / gcd)
  }
}
