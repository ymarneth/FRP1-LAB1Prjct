package fract

import scala.annotation.{tailrec, targetName}

final class Fract(_n: Double, _d: Double) {
  require(_d != 0, "Denominator cannot be zero")

  val numer: Int = (_n / computeGcd(_n.toInt, _d.toInt)).toInt
  val denom: Int = (_d / computeGcd(_n.toInt, _d.toInt)).toInt

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
  def +(addend: Fract): Fract = {
    val newNumer = numer * addend.denom + addend.numer * denom
    val newDenom = denom * addend.denom
    new Fract(newNumer, newDenom)
  }

  @targetName("subtract")
  def -(subtrahend: Fract): Fract = {
    val newNumer = numer * subtrahend.denom - subtrahend.numer * denom
    val newDenom = denom * subtrahend.denom
    new Fract(newNumer, newDenom)
  }

  @targetName("divide")
  def /(divider: Fract): Fract = this * divider.rec()

  @targetName("multiply")
  def *(multiplier: Fract): Fract = {
    val newNumer = numer * multiplier.numer
    val newDenom = denom * multiplier.denom
    new Fract(newNumer, newDenom)
  }

  private def rec(): Fract = new Fract(denom, numer)

  private def neg(): Fract = new Fract(-numer, denom)

  // Compute the greatest common divisor (GCD)
  @tailrec
  private def computeGcd(a: Int, b: Int): Int = if (b == 0) a else computeGcd(b, a % b)
}

object Fract {
  def apply(_n: Double, _d: Double): Fract = new Fract(_n, _d)
}
