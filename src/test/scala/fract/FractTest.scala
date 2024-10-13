package fract

import fract.Fract.~/~
import org.scalatest.funspec.AnyFunSpec

class FractTest extends AnyFunSpec {

  describe("Test fraction simplification and rules") {
    it("should correctly simplify a fraction") {
      val fract = Fract(8, 12)
      assert(fract.numer == 2)
      assert(fract.denom == 3)
    }

    it("should handle a negative numerator") {
      val fract = Fract(-1, 2)
      assert(fract.numer == -1)
      assert(fract.denom == 2)
    }

    it("should handle a negative denominator") {
      val fract = Fract(1, -2)
      assert(fract.numer == -1)
      assert(fract.denom == 2)
    }

    it("should handle a negative denominator and numerator") {
      val fract = Fract(-1, -2)
      assert(fract.numer == 1)
      assert(fract.denom == 2)
    }

    it("should recognize values with shorthand as Fract") {
      val fract = 3 ~/~ 4
      assert(fract.numer == 3)
      assert(fract.denom == 4)
    }

    it("should not allow zero for denominator") {
      assertThrows[IllegalArgumentException] {
        Fract(1, 0)
      }
    }
  }

  describe("Test toString, equals and hashCode") {
    it("should print a fraction correctly") {
      val fract = Fract(1, 2)
      assert(fract.toString == "1/2")
    }

    it("should return true for equal fractions") {
      val fract1 = Fract(1, 2)
      val fract2 = Fract(1, 2)
      assert(fract1.equals(fract2))
    }

    it("Equals should return false for different fractions") {
      val fract1 = Fract(1, 2)
      val fract2 = Fract(2, 3)
      assert(!fract1.equals(fract2))
    }

    it("Equals should return false for different types") {
      val fract = Fract(1, 2)
      assert(!fract.equals("1/2"))
    }

    it("Hashcode should be different for different fractions") {
      val fract1 = Fract(1, 2)
      val fract2 = Fract(2, 3)
      assert(fract1.hashCode() != fract2.hashCode())
    }
  }

  describe("Test arithmetic functions") {
    it("should add Fracts correctly") {
      val result = Fract(1, 2) + Fract(2, 2)
      assert(result.equals(Fract(3, 2)))
    }

    it("should add Fracts with different denoms correctly") {
      val result = Fract(1, 2) + Fract(3, 4)
      assert(result.equals(Fract(5, 4)))
    }

    it("should subtract Fracts correctly") {
      val result = Fract(3, 4) - Fract(1, 4)
      assert(result.equals(Fract(1, 2)))
    }

    it("should subtract Fracts with different denoms correctly") {
      val result = Fract(2, 5) - Fract(1, 4)
      assert(result.equals(Fract(3, 20)))
    }

    it("should multiply Fracts correctly") {
      val result = Fract(1, 3) * Fract(3, 5)
      assert(result.equals(Fract(1, 5)))
    }

    it("should divide Fracts correctly") {
      val result = Fract(1, 2) / Fract(1, 6)
      assert(result.equals(Fract(3, 1)))
    }

    it("should add Fract to Int") {
      val result = 3 + Fract(1, 2)
      assert(result.equals(Fract(7, 2)))
    }

    it("should subtract Fract from Int") {
      val result = 3 - Fract(1, 2)
      assert(result.equals(Fract(5, 2)))
    }

    it("should multiply Fract with Int") {
      val result = 3 * Fract(1, 2)
      assert(result.equals(Fract(3, 2)))
    }

    it("should divide Int by Fract") {
      val result = 3 / Fract(1, 2)
      assert(result.equals(Fract(6, 1)))
    }
  }
}
