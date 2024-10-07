package fract

object FractApp {

  def main(args: Array[String]): Unit = {
    val r = Fract(1,2) + Fract(2, 3) * Fract(4, 1).rec
    println (s"1/2 + 2/3 * (4/1 * -1) = $r")
  }
}
