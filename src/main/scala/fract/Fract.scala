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
  def +(addend: Fract): Fract = Fract.apply(
    numer * addend.denom + addend.numer * denom,
    denom * addend.denom
  )

  @targetName("subtract")
  def -(subtrahend: Fract): Fract = {
    Fract.apply(
      numer * subtrahend.denom - subtrahend.numer * denom,
      denom * subtrahend.denom
    )
  }

  @targetName("divide")
  def /(divider: Fract): Fract = this * divider.rec

  @targetName("multiply")
  def *(multiplier: Fract): Fract = {
    val newNumer = numer * multiplier.numer
    val newDenom = denom * multiplier.denom
    Fract.apply(newNumer, newDenom)
  }

  // returns the reciprocal value
  def rec: Fract = Fract.apply(denom, numer)

  // returns the negated Fract
  private def neg: Fract = Fract.apply(-numer, denom)
}

// Compute the greatest common divisor (GCD)
@tailrec
private[fract] def computeGcd(a: Int, b: Int): Int = {
  if (a < 0 || b < 0) {
    computeGcd(a.abs, b.abs)
  } else {
    if (b == 0) a else computeGcd(b, a % b)
  }
}

object Fract {
  extension (n: Int) {
    def +(that: Fract): Fract = Fract(n * that.denom + that.numer, that.denom)
    def -(that: Fract): Fract = Fract(n * that.denom - that.numer, that.denom)
    def *(that: Fract): Fract = Fract(n * that.numer, that.denom)
    def /(that: Fract): Fract = Fract(n * that.denom, that.numer)

    def ~/~(d: Int): Fract = Fract(n, d)
  }

  def apply(n: Int, d: Int): Fract = {
    val gcd = computeGcd(n, d)
    new Fract(n / gcd, d / gcd)
  }
}
