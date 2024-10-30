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

  // a)	x / y = 1 / 4 =>  should be 0 (integer division)
  private val optXY: Option[Int] =
    option {
      val x = bds("x")
      val y = bds("y")
      x / y
    }

  optXY match {
    case Some(xy) => println(s"x / y = $xy")
    case None => println("x / y failed")
  }

  // b)	x / z = 1 / 0 => should fail
  private val optXZ: Option[Int] =
    option {
      val x = bds("x")
      val z = bds("z")
      x / z
    }

  optXZ match {
    case Some(xz) => println(s"x / z = $xz")
    case None => println("x / z failed")
  }

  // c)	x / u = 1 / None => should fail
  private val optXU: Option[Int] =
    option {
      val x = bds("x")
      val u = bds("u")
      x / u
    }

  optXU match {
    case Some(xu) => println(s"x / u = $xu")
    case None => println("x / u failed")
  }
}
