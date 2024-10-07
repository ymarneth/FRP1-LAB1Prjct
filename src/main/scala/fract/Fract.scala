package fract

import scala.annotation.tailrec

// import gexpr.Field
// import java.util.Objects

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

  // Compute the greatest common divisor (GCD)
  @tailrec
  private def computeGcd(a: Int, b: Int): Int = if (b == 0) a else computeGcd(b, a % b)
}

object Fract 