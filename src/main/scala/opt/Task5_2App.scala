package opt

// Task 5.2: Pattern matching on Option values

object Task5_2App extends App {

  val bds: Map[String, Int] = Map("x" -> 1, "y" -> 4, "z" -> 0)

  val optX = bds.get("x")

  // Pattern matching optX
  optX match
    case Some(0) => println("Zero")
    case Some(v) if v > 0 => println("positive")
    case Some(v) => println("negative")
    case None => println("No value")
}

