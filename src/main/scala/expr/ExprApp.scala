package expr

import expr.*

object ExprApp {

  def main(args: Array[String]): Unit = {
    // To check the functionality of the code, please refer to the test cases in ExprTest.scala
    // There are instructions in the README.md file on how to run the tests
    // These are just some examples of how to use the code

    println("\n===================================================================================================")
    println("     ToString & Infix     ")
    println("===================================================================================================\n")


    println(s"Lit(3.0)                     : ${Lit(3.0)}")
    println(s"Var(x)                       : ${Var("x")}")
    println(s"Add(Lit(3.0), Var(x))        : ${Add(Lit(3.0), Var("x"))}")
    println(s"Mult(Lit(3.0), Var(x))       : ${Mult(Lit(3.0), Var("x"))}")
    println(s"Neg(Lit(3.0))                : ${Neg(Lit(3.0))}")
    println(s"Rec(Lit(3.0))                : ${Rec(Lit(3.0))}")

    println("\n===================================================================================================")
    println("     Eval     ")
    println("===================================================================================================\n")

    println(s"Lit(3.0)                                   : ${eval(Lit(3.0), Map.empty)}")
    println(s"Var(x)                                     : ${eval(Var("x"), Map("x" -> 10.0))}")
    println(s"Add(Lit(2.0), Lit(3.0))                    : ${eval(Add(Lit(2.0), Lit(3.0)), Map.empty)}")
    println(s"Add(Var(x), Lit(3.0))                      : ${eval(Add(Var("x"), Lit(3.0)), Map("x" -> 10.0))}")
    println(s"Mult(Lit(2.0), Lit(3.0))                   : ${eval(Mult(Lit(2.0), Lit(3.0)), Map.empty)}")
    println(s"Mult(Var(x), Lit(3.0))                     : ${eval(Mult(Var("x"), Lit(3.0)), Map("x" -> 10.0))}")
    println(s"Neg(Lit(3.0))                              : ${eval(Neg(Lit(3.0)), Map.empty)}")
    println(s"Rec(Lit(3.0))                              : ${eval(Rec(Lit(3.0)), Map.empty)}")
    println(s"Add(Mult(Lit(2.0), Var(x)), Rec(Lit(4.0))) : ${eval(Add(Mult(Lit(2.0), Var("x")), Rec(Lit(4.0))), Map("x" -> 3.0))}")

    println("\n===================================================================================================")
    println("     Simplify     ")
    println("===================================================================================================\n")

    println(s"Add(Var(a), Lit(0.0))        : ${simplify(Add(Var("a"), Lit(0.0)))}")
    println(s"Add(Lit(0.0), Var(a))        : ${simplify(Add(Lit(0.0), Var("a")))}")
    println(s"Mult(Var(a), Lit(0.0))       : ${simplify(Mult(Var("a"), Lit(0.0)))}")
    println(s"Mult(Lit(0.0), Var(a))       : ${simplify(Mult(Lit(0.0), Var("a")))}")
    println(s"Mult(Var(a), Lit(1.0))       : ${simplify(Mult(Var("a"), Lit(1.0)))}")
    println(s"Mult(Lit(1.0), Var(a))       : ${simplify(Mult(Lit(1.0), Var("a")))}")
    println(s"Neg(Neg(Var(a)))             : ${simplify(Neg(Neg(Var("a"))))}")
    println(s"Rec(Rec(Var(a)))             : ${simplify(Rec(Rec(Var("a"))))}")
    println(s"Mult(Lit(2.0), Lit(3.0))     : ${simplify(Mult(Lit(2.0), Lit(3.0)))}")

    println("\n-------------------")
  }
}
