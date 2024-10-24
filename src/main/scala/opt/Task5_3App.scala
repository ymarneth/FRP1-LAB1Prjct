package opt

// Task 5.3: Method option
def option[A](body: => A): Option[A] = {
  try {
    Some(body)
  } catch
    case e: Exception => None
}

object Task5_3App extends App {

  val bds: Map[String, Int] = Map("x" -> 1, "y" -> 4, "z" -> 0)

  // a)	x / y
  private val optXY: Option[Int] =
    option {
      val x = bds.get("x").get
      val y = bds.get("y").get
      x / y
    }

  optXY match {
    case Some(xy) => println(s"x / y = $xy")
    case None => println("x / y failed")
  }

  // b)	x / z
  val optXZ: Option[Int] = ???

  optXZ match {
    case Some(xz) => println(s"x / z = $xz")
    case None => println("x / z failed")
  }

  // c)	x / u
  val optXU: Option[Int] = ???

  optXU match {
    case Some(xu) => println(s"x / u = $xu")
    case None => println("x / u failed")
  }

}

