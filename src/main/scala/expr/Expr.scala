package expr

sealed trait Expr {
  override def toString: String = infix(this)
}

case class Lit(value: Double) extends Expr

case class Var(name: String) extends Expr

sealed trait BinExpr(val left: Expr, val right: Expr) extends Expr

case class Add(addend: Expr, augend: Expr) extends BinExpr(addend, augend)

case class Mult(multiplend: Expr, multiplier: Expr) extends BinExpr(multiplend, multiplier)

sealed trait UnyExpr(sub: Expr) extends Expr

case class Neg(s: Expr) extends UnyExpr(s)

case class Rec(s: Expr) extends UnyExpr(s)

def infix(expr: Expr): String = expr match {
  case Lit(v) => v.toString
  case Var(n) => n
  case Add(l, r) => s"(${infix(l)} + ${infix(r)})"
  case Mult(l, r) => s"(${infix(l)} * ${infix(r)})"
  case Neg(s) => s"(-${infix(s)})"
  case Rec(s) => s"(/ ${infix(s)})"
  case null => throw new MatchError(expr)
}

def eval(expr: Expr, bds: Map[String, Double]): Double = {
  expr match
    case Lit(v) => v
    case Var(n) if bds.contains(n) => bds(n)
    case Var(n) => throw new IllegalArgumentException(s"Variable $n not found")
    case Add(l, r) => eval(l, bds) + eval(r, bds)
    case Mult(l, r) => eval(l, bds) * eval(r, bds)
    case Neg(s) => -eval(s, bds)
    case Rec(s) =>
      val sV = eval(s, bds)
      if (sV == 0.0) throw new ArithmeticException("Division by zero not allowed")
      else 1.0 / sV
}

def simplify(expr: Expr): Expr = ???

