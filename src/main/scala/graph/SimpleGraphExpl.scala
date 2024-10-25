package graph

object SimpleGraph extends IntGraph {
  val nodes: Set[N] = Set(1, 2, 3, 4)
  val edges: Set[(N, N)] = Set((1, 2), (1, 3), (2, 4), (3, 4))
}

object SimpleGraphApp extends App {

  import SimpleGraph._

  private val s1 = successors(1)
  println(s1)

  private val distances = computeDistances(1)
  println(distances)

  private val paths = computePaths(1)
  println(paths)

  private val distancesGeneric = computeDistsG(1)
  println(distancesGeneric)

  private val pathsGeneric = computePathsG(1)
  println(pathsGeneric)
}

