package opt

import expr.{Add, Expr, Lit, Neg, Mult, Rec, Var}

import scala.util.{Try, Success, Failure}

// Task 5.5: Expression evaluation with Option
def evalTry(expr: Expr, bds: Map[String, Double]): Try[Double] =
  expr match
    case Lit(v) => Success(v)
    case Var(n) => bds.get(n) match
      case Some(v) => Success(v)
      case None => Failure(new Exception(s"Variable $n not found"))
    case Add(l, r) => evalTry(l, bds).flatMap(lv =>
      evalTry(r, bds).map(rv => lv + rv))
    case Mult(l, r) => evalTry(l, bds).flatMap(lv =>
      evalTry(r, bds).map(rv => lv * rv))
    case Neg(s) => evalTry(s, bds).map(sv => -sv)
    case Rec(s) => evalTry(s, bds).flatMap {
      case 0.0 => Failure(new Exception("Division by zero"))
      case sv => Success(1.0 / sv)
    }

object Task5_5ExprEvalTry extends App {

  val bds = Map("x" -> 2.0, "y" -> 3.0, "z" -> 0.0)

  // 1 + (-x) = 1 - 2 = -1.0
  val e1 = Add(Lit(1), Neg(Var("x")))
  private val tryR1 = evalTry(e1, bds)
  println (s"${e1.toString} = ${tryR1.map(_.toString).getOrElse("undefined")}")

  // 1 * (-u) = 1 * None = undefined
  val e2 = Mult(Lit(1), Neg(Var("u")))
  private val tryR2 = evalTry(e2, bds)
  println (s"${e2.toString} = ${tryR2.map(_.toString).getOrElse("undefined")}")

  // 1 * (-z) = 1 * (-0) = 0.0
  val e3 = Mult(Lit(1), Neg(Var("z")))
  private val tryR3 = evalTry(e3, bds)
  println (s"${e3.toString} = ${tryR3.map(_.toString).getOrElse("undefined")}")
}
