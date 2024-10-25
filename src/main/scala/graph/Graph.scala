package graph

import scala.annotation.tailrec
import scala.collection.immutable.Queue

trait Graph {
  type N // abstract type for nodes

  private lazy val successorMap: Map[N, Set[N]] = nodes
    .map(n => (n, edges
      .filter((f, _) => f == n)
      .map((_, t) => t)))
    .toMap

  val nodes: Set[N]
  val edges: Set[(N, N)]

  // Task 3.2: Method computeDists
  def computeDistances(start: N): Map[N, Int] = {
    @tailrec
    def distancesRecursive(queue: Queue[N], result: Map[N, Int]): Map[N, Int] =
      if (queue.isEmpty) result
      else {
        val current = queue.head

        val newSuccessors = successors(current).removedAll(queue).removedAll(result.keySet)
        val updatedQueue = queue.tail.appendedAll(newSuccessors)

        val currentResult = result(current)
        val updatedResult = result ++ newSuccessors.map(s => (s, currentResult + 1))

        distancesRecursive(updatedQueue, updatedResult)
      }

    distancesRecursive(Queue(start), Map(start -> 0))
  }

  // Task 3.3: Method computePaths
  def computePaths(start: N): Map[N, List[N]] = {
    @tailrec
    def pathsRecursive(queue: Queue[N], result: Map[N, List[N]]): Map[N, List[N]] =
      if (queue.isEmpty) result
      else {
        val current = queue.head

        val newSuccessors = successors(current).removedAll(queue).removedAll(result.keySet)
        val updatedQueue = queue.tail.appendedAll(newSuccessors)

        val currentResult = result(current)
        val updatedResult = result ++ newSuccessors.map(s => (s, s :: currentResult))

        pathsRecursive(updatedQueue, updatedResult)
      }

    pathsRecursive(Queue(start), Map(start -> List(start)))
  }

  // Task 3.1: Method successors
  def successors(node: N): Set[N] =
    successorMap(node)

  def computeDistsG(start: N): Map[N, Int] =
    computeValues[Int](start, 0, (rn, s) => rn + 1)

  def computePathsG(start: N): Map[N, List[N]] =
    computeValues[List[N]](start, List(start), (rn, s) => s :: rn)

  // Task 3.4: Methods compute Values
  private def computeValues[R](start: N, startValue: R, fn: (R, N) => R): Map[N, R] = {
    @tailrec
    def valuesRecursive(queue: Queue[N], result: Map[N, R]): Map[N, R] =
      if (queue.isEmpty) result
      else {
        val current = queue.head

        val newSuccessors = successors(current).removedAll(queue).removedAll(result.keySet)
        val updatedQueue = queue.tail.appendedAll(newSuccessors)

        val currentResult = result(current)
        val udpResult = result ++ newSuccessors.map(s => (s, fn(currentResult, s)))

        valuesRecursive(updatedQueue, udpResult)
      }

    valuesRecursive(Queue(start), Map(start -> startValue))
  }
}

trait IntGraph extends Graph {
  type N = Int
}
