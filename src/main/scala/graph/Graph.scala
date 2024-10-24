package graph

import scala.annotation.tailrec
import scala.collection.immutable.Queue

trait Graph {
  type N // abstract type for nodes

  val nodes: Set[N]
  val edges: Set[(N, N)]

  private lazy val successorMap: Map[N, Set[N]] = nodes
    .map(n => (n, edges
      .filter((f, _) => f == n)
      .map((_, t) => t)))
    .toMap

  // Task 3.1: Method successors
  def successors(node: N): Set[N] =
    successorMap(node)

  // Task 3.2: Method computeDists
  def computeDists(start: N): Map[N, Int] = {
    @tailrec
    def distsRec(queue: Queue[N], result: Map[N, Int]): Map[N, Int] =
      if (queue.isEmpty) result
      else {
        val n = queue.head

        val succs = successors(n).removedAll(queue).removedAll(result.keySet)
        val updQueue = queue.tail.appendedAll(succs)

        val nResult = result(n)
        val udpResult = result ++ succs.map(s => (s, nResult + 1))

        distsRec(updQueue, udpResult)
      }

    distsRec(Queue(start), Map(start -> 0))
  }

  // Task 3.3: Method computePaths
  def computePaths(start: N): Map[N, List[N]] = {
    @tailrec
    def pathsRec(queue: Queue[N], result: Map[N, List[N]]): Map[N, List[N]] =
      if (queue.isEmpty) result
      else {
        val n = queue.head

        val succs = successors(n).removedAll(queue).removedAll(result.keySet)
        val updQueue = queue.tail.appendedAll(succs)

        val nResult = result(n)
        val udpResult = result ++ succs.map(s => (s, s :: nResult))

        pathsRec(updQueue, udpResult)
      }

    pathsRec(Queue(start), Map(start -> List(start)))
  }

  // Task 3.4: Methods compute Values
  // TODO Generic computeValues
  def computeValues[R](start: N, startValue: R, fn: (R, N) => R): Map[N, R] = ???

  def computeDistsG(start: N): Map[N, Int] =
    computeValues[Int](start, 0, (rn, s) => rn + 1)

  def computePathsG(start: N): Map[N, List[N]] =
    computeValues[List[N]](start, List(start), (rn, s) => s :: rn)

  // optimizations -------------------------------------------------------------------------------

}

trait IntGraph extends Graph {
  type N = Int
}


