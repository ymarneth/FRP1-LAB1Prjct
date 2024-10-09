package fract

import fract.Fract.~/~
import org.scalatest.funsuite.AnyFunSuite

class FractTest extends AnyFunSuite {

  test("Fract should correctly simplify a fraction") {
    val fract = Fract.apply(8, 12)
    assert(fract.numer == 2)
    assert(fract.denom == 3)
  }

  test("Fract should handle a negative numerator") {
    val fract = new Fract(-1, 2)
    assert(fract.numer == -1)
    assert(fract.denom == 2)
  }

  test("Fract should handle a negative denominator") {
    val fract = new Fract(1, -2)
    assert(fract.numer == -1)
    assert(fract.denom == 2)
  }

  test("Fract should handle a negative denominator and numerator") {
    val fract = new Fract(-1, -2)
    assert(fract.numer == 1)
    assert(fract.denom == 2)
  }

  test("Fract should not allow zero for denominator") {
    assertThrows[IllegalArgumentException] {
      new Fract(1, 0)
    }
  }

  test("Fract should print a fraction correctly") {
    val fract = new Fract(1, 2)
    assert(fract.toString == "1/2")
  }

  test("Equals should return true for equal fractions") {
    val fract1 = new Fract(1, 2)
    val fract2 = new Fract(1, 2)
    assert(fract1 == fract2)
  }

  test("Equals should return false for different fractions") {
    val fract1 = new Fract(1, 2)
    val fract2 = new Fract(2, 3)
    assert(fract1 != fract2)
  }

  test("Equals should return false for different types") {
    val fract = new Fract(1, 2)
    assert(!fract.equals("1/2"))
  }

  test("Hashcode should be the same for equal fractions") {
    val fract1 = new Fract(1, 2)
    val fract2 = new Fract(1, 2)
    assert(fract1.hashCode() == fract2.hashCode())
  }

  test("Hashcode should be different for different fractions") {
    val fract1 = new Fract(1, 2)
    val fract2 = new Fract(2, 3)
    assert(fract1.hashCode() != fract2.hashCode())
  }

  test("should add Fracts correctly") {
    val fract1 = new Fract(1, 2)
    val fract2 = new Fract(2, 2)
    val result = fract1 + fract2
    val expected = new Fract(3, 2)
    assert(result.equals(expected))
  }

  test("should add Fracts with different denoms correctly") {
    val fract1 = new Fract(1, 2)
    val fract2 = new Fract(3, 4)
    val result = fract1 + fract2
    val expected = new Fract(5, 4)
    assert(result.equals(expected))
  }

  test("should subtract Fracts correctly") {
    val fract1 = new Fract(3, 4)
    val fract2 = new Fract(1, 4)
    val result = fract1 - fract2
    val expected = new Fract(1, 2)
    assert(result.equals(expected))
  }

  test("should subtract Fracts with different denoms correctly") {
    val fract1 = new Fract(2, 5)
    val fract2 = new Fract(1, 4)
    val result = fract1 - fract2
    val expected = new Fract(3, 20)
    assert(result.equals(expected))
  }

  test("should multiply Fracts correctly") {
    val fract1 = new Fract(1, 3)
    val fract2 = new Fract(3, 5)
    val result = fract1 * fract2
    val expected = new Fract(1, 5)
    assert(result.equals(expected))
  }

  test("should divide Fracts correctly") {
    val fract1 = new Fract(1, 2)
    val fract2 = new Fract(1, 6)
    val result = fract1 / fract2
    val expected = new Fract(3, 1)
    assert(result.equals(expected))
  }

  test("should multiply Fract with Int") {
    val fract = new Fract(1, 2)
    val result = 3 * fract
    val expected = new Fract(3, 2)
    assert(result.equals(expected))
  }
  
  test("should recognize values as Fract") {
    val fract = 3 ~/~ 4
    assert(fract.numer == 3)
    assert(fract.denom == 4)
  }
}
