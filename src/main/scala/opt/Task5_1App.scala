package opt

import java.util.Scanner

// Task 5.1: Alternative values for None

object Task5_1App extends App {

  private val scn = new Scanner(System.in)
  val bds: Map[String, Int] = Map("x" -> 1, "y" -> 4, "z" -> 0)

  // a) Test the value and then access it with get.
  private val optX: Option[Int] = bds.get("x")
  if (optX.isDefined) println(optX)
  else println("Not defined x")

  private val optU = bds.get("u")
  if (optU.isDefined) println(optU)
  else println("Not defined u")

  // b)	Use the method getOrElse to specify an alternative value in case of missing value
  private val xOptOrElse: Option[Int] = bds.get("x").orElse({
    print("Input: ")
    option {
      scn.nextInt()
    }
  })

  println(xOptOrElse)

  // c)	Use the method elseGet to read in an alternative value from the user (with Scanner scn) if a value is missing
  private val uOptOrElse: Int = bds.get("u").orElse({
    print("Input: ")
    option {
      scn.nextInt()
    }
  }).getOrElse(-1)

  println(uOptOrElse)
}

