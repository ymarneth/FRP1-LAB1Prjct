package expr

sealed trait Expr

case class Lit(v: Double) extends Expr

case class Var(n: String) extends Expr

sealed trait BinExpr(val left: Expr, val right: Expr) extends Expr

case class Add(addend: Expr, augend: Expr) extends BinExpr(addend, augend)

case class Mult(multiplend: Expr, multiplier: Expr) extends BinExpr(multiplend, multiplier)

sealed trait UnyExpr(sub: Expr) extends Expr

case class Min(s: Expr) extends UnyExpr(s)

case class Rec(s: Expr) extends UnyExpr(s)

def infix(expr: Expr): String = ??? // TODO:

def eval(expr: Expr, bds: Map[String, Double]): Double = ???

def simplify(expr: Expr): Expr = ???
  
