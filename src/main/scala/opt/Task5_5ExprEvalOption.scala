package opt

import expr.{Add, Expr, Lit, Neg, Mult, Rec, Var}

import java.util.Scanner

// Task 5.5: Expression evaluation with Option

def evalOption(expr: Expr, bds: Map[String, Double]): Option[Double] =
  expr match
    case Lit(v) => Some(v)
    case Var(n) => bds.get(n)
    //case Add(l, bds) => evalOption(l, bds).flatMap(lv => evalOption(r, bds).map(rv => lv + rv))

object Task5_5ExprEvalOption extends App {

  val bds = Map("x" -> 2.0, "y" -> 3.0, "z" -> 0.0)

  val e1 = Add(Lit(1), Neg(Var("x")))
  val optR1 = evalOption(e1, bds)
  println(s"${e1.toString} = ${optR1.map(_.toString).getOrElse("undefined")}")

  val e2 = Mult(Lit(1), Neg(Var("u")))
  val optR2 = evalOption(e2, bds)
  println(s"${e2.toString} = ${optR2.map(_.toString).getOrElse("undefined")}")

  val e3 = Mult(Lit(1), Rec(Var("z")))
  val optR3 = evalOption(e3, bds)
  println(s"${e3.toString} = ${optR3.map(_.toString).getOrElse("undefined")}")

}
