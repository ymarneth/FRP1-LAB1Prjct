package opt

import java.util.Scanner

// Task 5.4: Chaining unsafe access operations
object Task5_4App extends App {

  val scn = new Scanner(System.in)
  val bds: Map[String, Int] = Map("x" -> 1, "y" -> 4, "z" -> 0)

  // a) x / y = 1 / 4 => should be 0 (integer division)
  private val optR1: Option[Int] = bds.get("x")
    .flatMap(x => bds.get("y")
      .flatMap(y => option {
        x / y
      })
    )

  optR1 match {
    case Some(r) => println(s"x / y = $r")
    case None => println("x / y failed")
  }

  // b) x / z = 1 / 0 => should fail
  private val optR2: Option[Int] = bds.get("x")
    .flatMap(x => bds.get("z")
      .filter(z => z != 0)
      .map(z => x / z)
    )

  optR2 match {
    case Some(r) => println(s"x / z = $r")
    case None => println("x / z failed")
  }


  // c) x / u = 1 / None => should fail
  val optR3: Option[Int] = bds.get("x")
    .flatMap(x => bds.get("u")
      .flatMap(u => option {
        x / u
      })
    )

  optR3 match {
    case Some(r) => println(s"x / u = $r")
    case None => println("x / u failed")
  }

  // d) x / (y * z) = 1 / ( 4 / 0 ) => should fail
  private val optR4: Option[Int] = bds.get("x")
    .flatMap(x => bds.get("y")
      .flatMap(y => bds.get("z")
      .flatMap(z => option {
        x / (y * z)
      })))

  optR4 match {
    case Some(r) => println(s"x / (y * z) = $r")
    case None => println("x / (y * z) failed")
  }

}

