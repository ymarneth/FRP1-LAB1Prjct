package fract

import fract.Fract.~/~

object FractApp {

  def main(args: Array[String]): Unit = {
    // To check the functionality of the code, please refer to the test cases in FractTest.scala
    // There are instructions in the README.md file on how to run the tests
    // These are just some examples of how to use the code

    println("\n===================================================================================================")
    println("     Simplification and rules     ")
    println("===================================================================================================\n")

    println(s"Fract(8, 12)                     : ${Fract(8, 12)}")
    println(s"Fract(-1, 2)                     : ${Fract(-1, 2)}")
    println(s"Fract(1, -2)                     : ${Fract(1, -2)}")
    println(s"Fract(-1, -2)                    : ${Fract(-1, -2)}")
    println(s"3 ~/~ 4                          : ${3 ~/~ 4}")

    println("\n===================================================================================================")
    println("     equals and hashCode     ")
    println("===================================================================================================\n")

    println(s"Fract(1, 2).equals(Fract(1, 2))            : ${Fract(1, 2).equals(Fract(1, 2))}")
    println(s"Fract(1, 2).equals(Fract(2, 3))            : ${Fract(1, 2).equals(Fract(2, 3))}")
    println(s"Fract(1, 2).equals(\"1/2\")                  : ${Fract(1, 2).equals("1/2")}")
    println(s"Fract(1, 2).equals(\"1/2\")                  : ${Fract(1, 2).equals("1/2")}")
    println(s"fract1.hashCode() != fract2.hashCode()     : ${Fract(1, 2).hashCode() != Fract(2, 3).hashCode()}")

    println("\n===================================================================================================")
    println("     arithmetic operations     ")
    println("===================================================================================================\n")

    println(s"Fract(1, 2) + Fract(2, 2)                   : ${Fract(1, 2) + Fract(2, 2)}")
    println(s"Fract(1, 2) + Fract(3, 4)                   : ${Fract(1, 2) + Fract(3, 4)}")
    println(s"Fract(3, 4) - Fract(1, 4)                   : ${Fract(3, 4) - Fract(1, 4)}")
    println(s"Fract(2, 5) - Fract(1, 4)                   : ${Fract(2, 5) - Fract(1, 4)}")
    println(s"Fract(1, 3) * Fract(3, 5)                   : ${Fract(1, 3) * Fract(3, 5)}")
    println(s"Fract(1, 2) / Fract(1, 6)                   : ${Fract(1, 2) / Fract(1, 6)}")
    println(s"3 + Fract(1, 2)                             : ${3 + Fract(1, 2)}")
    println(s"3 - Fract(1, 2)                             : ${3 - Fract(1, 2)}")
    println(s"3 * Fract(1, 2)                             : ${3 * Fract(1, 2)}")
    println(s"3 / Fract(1, 2)                             : ${3 / Fract(1, 2)}")

    println("\n===================================================================================================")
    println("     Complexer arithmetic operations     ")
    println("===================================================================================================\n")

    println(s"1~/~2 + 2~/~3 * 1~/~4                         : ${1 ~/~ 2 + 2 ~/~ 3 * 1 ~/~ 4}")
    println(s"1 + 2~/~3 / 1~/~4                             : ${1 + 2 ~/~ 3 / 1 ~/~ 4}")
    println(s"5 * (-2~/~3 - 1~/~4)                          : ${5 * (-2 ~/~ 3 - 1 ~/~ 4)}")

    println("\n-------------------")
  }
}
