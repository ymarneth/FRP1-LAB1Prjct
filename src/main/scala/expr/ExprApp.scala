package expr

import expr._

object ExprApp {

  def main(args: Array[String]): Unit = {
    println ("Hello World")
    val e1 = Add(Lit(1), Neg(Var("x")))
    println(infix(e1))
  }

}
