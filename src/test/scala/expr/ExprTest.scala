package expr

import org.scalatest.funspec.AnyFunSpec

class ExprTest extends AnyFunSpec {

  describe("ToString and Infix") {
    it("should print the value of a literal") {
      val lit = Lit(3.0)
      assert(lit.toString == "3.0")
    }

    it("should print the name of a variable") {
      val varX = Var("x")
      assert(varX.toString == "x")
    }

    it("should print an addition in parentheses and with the operator in infix notation") {
      val add = Add(Lit(3.0), Var("x"))
      assert(add.toString == "(3.0 + x)")
    }

    it("should print a multiplication in parentheses and with the operator in infix notation") {
      val mult = Mult(Lit(3.0), Var("x"))
      assert(mult.toString == "(3.0 * x)")
    }

    it("should print a negation in parentheses and with the operator in prefix notation") {
      val neg = Neg(Lit(3.0))
      assert(neg.toString == "(-3.0)")
    }

    it("should print a reciprocal in parentheses and with the operator in prefix notation") {
      val rec = Rec(Lit(3.0))
      assert(rec.toString == "(/ 3.0)")
    }
  }

  describe("Eval") {
    it("should return the value of a literal") {
      val lit = Lit(3.0)
      assert(eval(lit, Map.empty) == 3.0)
    }

    it("should return the value for a variable if exists in bindings") {
      val expr = Var("x")
      val bindings = Map("x" -> 10.0)
      assert(eval(expr, bindings) == 10.0)
    }

    it("should throw an exception if a variable is not found in bindings") {
      val expr = Var("x")
      assertThrows[IllegalArgumentException] {
        eval(expr, Map.empty)
      }
    }

    it("should add two literals") {
      val expr = Add(Lit(2.0), Lit(3.0))
      assert(eval(expr, Map.empty) == 5.0)
    }

    it("should add a literal and a variable") {
      val expr = Add(Lit(2.0), Var("x"))
      val bindings = Map("x" -> 3.0)
      assert(eval(expr, bindings) == 5.0)
    }

    it("should multiply two literals") {
      val expr = Mult(Lit(2.0), Lit(4.0))
      assert(eval(expr, Map.empty) == 8.0)
    }

    it("should multiply a literal and a variable") {
      val expr = Mult(Lit(2.0), Var("x"))
      val bindings = Map("x" -> 3.0)
      assert(eval(expr, bindings) == 6.0)
    }

    it("should negate a literal") {
      val expr = Neg(Lit(5.0))
      assert(eval(expr, Map.empty) == -5.0)
    }

    it("should negate a variable") {
      val expr = Neg(Var("x"))
      val bindings = Map("x" -> 5.0)
      assert(eval(expr, bindings) == -5.0)
    }

    it("should return the reciprocal of a literal") {
      val expr = Rec(Lit(4.0))
      assert(eval(expr, Map.empty) == 0.25)
    }

    it("should return the reciprocal of a variable") {
      val expr = Rec(Var("x"))
      val bindings = Map("x" -> 4.0)
      assert(eval(expr, bindings) == 0.25)
    }

    it("should throw an exception for division by zero in reciprocal") {
      val expr = Rec(Lit(0.0))
      assertThrows[ArithmeticException] {
        eval(expr, Map.empty)
      }
    }

    it("should handle a complex expression") {
      val expr = Add(Mult(Lit(2.0), Var("x")), Rec(Lit(4.0)))
      val bindings = Map("x" -> 3.0)
      assert(eval(expr, bindings) == 6.25)
    }
  }
}
