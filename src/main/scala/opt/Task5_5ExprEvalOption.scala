package opt

import expr.{Add, Expr, Lit, Neg, Mult, Rec, Var}

// Task 5.5: Expression evaluation with Option
def evalOption(expr: Expr, bds: Map[String, Double]): Option[Double] =
  expr match
    case Lit(v) => Some(v)
    case Var(n) => bds.get(n)
    case Add(l, r) => evalOption(l, bds).flatMap(lv =>
      evalOption(r, bds).map(rv => lv + rv))
    case Mult(l, r) => evalOption(l, bds).flatMap(lv =>
      evalOption(r, bds).map(rv => lv * rv))
    case Neg(s) => evalOption(s, bds).map(sv => -sv)
    case Rec(s) => evalOption(s, bds).flatMap {
      case 0.0 => None
      case sv => Some(1.0 / sv)
    }

object Task5_5ExprEvalOption extends App {

  val bds = Map("x" -> 2.0, "y" -> 3.0, "z" -> 0.0)

  // 1 + (-x) = 1 - 2 = -1.0
  private val e1 = Add(Lit(1), Neg(Var("x")))
  val optR1 = evalOption(e1, bds)
  println(s"${e1.toString} = ${optR1.map(_.toString).getOrElse("undefined")}")

  // 1 * (-u) = 1 * None = undefined
  private val e2 = Mult(Lit(1), Neg(Var("u")))
  val optR2 = evalOption(e2, bds)
  println(s"${e2.toString} = ${optR2.map(_.toString).getOrElse("undefined")}")

  // 1 * (1 / z) = 1 * (1 / 0) = undefined
  private val e3 = Mult(Lit(1), Rec(Var("z")))
  val optR3 = evalOption(e3, bds)
  println(s"${e3.toString} = ${optR3.map(_.toString).getOrElse("undefined")}")

  // x + y = 2 + 3 = 5.0
  private val e4 = Add(Var("x"), Var("y"))
  val optR4 = evalOption(e4, bds)
  println(s"${e4.toString} = ${optR4.map(_.toString).getOrElse("undefined")}")

  // (x * y) + 1 = (2 * 3) + 1 = 7.0
  private val e5 = Add(Mult(Var("x"), Var("y")), Lit(1))
  private val optR5 = evalOption(e5, bds)
  println(s"${e5.toString} = ${optR5.map(_.toString).getOrElse("undefined")}")

  // 1 / x = 1 / 2 = 0.5
  private val e6 = Rec(Var("x"))
  private val optR6 = evalOption(e6, bds)
  println(s"${e6.toString} = ${optR6.map(_.toString).getOrElse("undefined")}")

  // 1 / (x - x) = 1 / 0 = undefined
  private val e7 = Rec(Add(Var("x"), Neg(Var("x"))))
  private val optR7 = evalOption(e7, bds)
  println(s"${e7.toString} = ${optR7.map(_.toString).getOrElse("undefined")}")
}
