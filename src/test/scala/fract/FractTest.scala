package fract

import org.scalatest.funsuite.AnyFunSuite

class FractTest extends AnyFunSuite {

  test("Fract should correctly simplify a fraction") {
    val fract = new Fract(8.0, 12.0)
    assert(fract.numer == 2)
    assert(fract.denom == 3)
  }

  test("Fract should handle a negative numerator") {
    val fract = new Fract(-1.0, 2.0)
    assert(fract.numer == -1)
    assert(fract.denom == 2)
  }

  test("Fract should handle a negative denominator") {
    val fract = new Fract(1.0, -2.0)
    assert(fract.numer == -1)
    assert(fract.denom == 2)
  }

  test("Fract should handle a negative denominator and numerator") {
    val fract = new Fract(-1.0, -2.0)
    assert(fract.numer == 1)
    assert(fract.denom == 2)
  }

  test("Fract should not allow zero for denominator") {
    assertThrows[IllegalArgumentException] {
      new Fract(1.0, 0)
    }
  }

  test("Fract should print a fraction correctly") {
    val fract = new Fract(1.0, 2.0)
    assert(fract.toString == "1/2")
  }

  test("Equals should return true for equal fractions") {
    val fract1 = new Fract(1.0, 2.0)
    val fract2 = new Fract(1.0, 2.0)
    assert(fract1 == fract2)
  }

  test("Equals should return false for different fractions") {
    val fract1 = new Fract(1.0, 2.0)
    val fract2 = new Fract(2.0, 3.0)
    assert(fract1 != fract2)
  }

  test("Equals should return false for different types") {
    val fract = new Fract(1.0, 2.0)
    assert(!fract.equals("1/2"))
  }

  test("Hashcode should be the same for equal fractions") {
    val fract1 = new Fract(1.0, 2.0)
    val fract2 = new Fract(1.0, 2.0)
    assert(fract1.hashCode() == fract2.hashCode())
  }

  test("Hashcode should be different for different fractions") {
    val fract1 = new Fract(1.0, 2.0)
    val fract2 = new Fract(2.0, 3.0)
    assert(fract1.hashCode() != fract2.hashCode())
  }

  test("should add Fracts correctly") {
    val fract1 = new Fract(1.0, 2.0)
    val fract2 = new Fract(2.0, 2.0)
    val result = fract1 + fract2
    val expected = new Fract(3.0, 2.0)
    assert(result.equals(expected))
  }

  test("should add Fracts with different denoms correctly") {
    val fract1 = new Fract(1.0, 2.0)
    val fract2 = new Fract(3.0, 4.0)
    val result = fract1 + fract2
    val expected = new Fract(5.0, 4.0)
    assert(result.equals(expected))
  }

  test("should subtract Fracts correctly") {
    val fract1 = new Fract(3.0, 4.0)
    val fract2 = new Fract(1.0, 4.0)
    val result = fract1 - fract2
    val expected = new Fract(1.0, 2.0)
    assert(result.equals(expected))
  }

  test("should subtract Fracts with different denoms correctly") {
    val fract1 = new Fract(2.0, 5.0)
    val fract2 = new Fract(1.0, 4.0)
    val result = fract1 - fract2
    val expected = new Fract(3.0, 20.0)
    assert(result.equals(expected))
  }

  test("should multiply Fracts correctly") {
    val fract1 = new Fract(1.0, 3.0)
    val fract2 = new Fract(3.0, 5.0)
    val result = fract1 * fract2
    val expected = new Fract(1.0, 5.0)
    assert(result.equals(expected))
  }

  test("should divide Fracts correctly") {
    val fract1 = new Fract(1.0, 2.0)
    val fract2 = new Fract(1.0, 6.0)
    val result = fract1 / fract2
    val expected = new Fract(3.0, 1.0)
    assert(result.equals(expected))
  }
}
