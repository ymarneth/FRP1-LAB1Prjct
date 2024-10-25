package graph

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.shouldEqual

class GraphTest extends AnyFunSpec {

  describe("Test compute distances") {
    it("should compute distances correctly with generic method") {
      val dists = SimpleGraph.computeDistsG(1)
      dists shouldEqual  Map(1 -> 0, 2 -> 1, 3 -> 1, 4 -> 2)
    }

    it("should compute distances correctly with non-generic method") {
      val dists = SimpleGraph.computeDistances(1)
      dists shouldEqual  Map(1 -> 0, 2 -> 1, 3 -> 1, 4 -> 2)
    }

    it("should handle start node with no successors") {
      val dists = SimpleGraph.computeDistances(4)
      dists shouldEqual Map(4 -> 0)
    }

    it("should handle no edges") {
      object NoEdgesSimpleGraph extends IntGraph {
        val nodes: Set[N] = Set(1, 2, 3)
        val edges: Set[(N, N)] = Set()
      }

      val dists = NoEdgesSimpleGraph.computeDistances(1)
      dists shouldEqual Map(1 -> 0)
    }

    it("should handle disconnected graph") {
      object DisconnectedSimpleGraph extends IntGraph {
        val nodes: Set[N] = Set(1, 2, 3, 4, 5)
        val edges: Set[(N, N)] = Set(
          (1, 2),
          (2, 3),
          (4, 5)
        )
      }

      val dists = DisconnectedSimpleGraph.computeDistances(1)
      dists shouldEqual Map(1 -> 0, 2 -> 1, 3 -> 2)

      val resultFrom4 = DisconnectedSimpleGraph.computeDistances(4)
      resultFrom4 shouldEqual Map(4 -> 0, 5 -> 1)
    }

    it("should handle a cycle in the graph") {
      object CyclicSimpleGraph extends IntGraph {
        val nodes: Set[N] = Set(1, 2, 3)
        val edges: Set[(N, N)] = Set(
          (1, 2),
          (2, 3),
          (3, 1)
        )
      }

      val dists = CyclicSimpleGraph.computeDistances(1)
      dists shouldEqual Map(1 -> 0, 2 -> 1, 3 -> 2)
    }
  }
  
  describe("Test compute paths") {
    it("should compute paths correctly with generic method") {
      val dists = SimpleGraph.computePathsG(1)
      dists shouldEqual Map(1 -> List(1), 2 -> List(2, 1), 3 -> List(3, 1), 4 -> List(4, 2, 1))
    }

    it("should compute paths correctly with non-generic method") {
      val dists = SimpleGraph.computePaths(1)
      dists shouldEqual Map(1 -> List(1), 2 -> List(2, 1), 3 -> List(3, 1), 4 -> List(4, 2, 1))
    }

    it("should handle no edges") {
      object NoEdgesSimpleGraph extends IntGraph {
        val nodes: Set[N] = Set(1, 2, 3)
        val edges: Set[(N, N)] = Set()
      }

      val dists = NoEdgesSimpleGraph.computePaths(1)
      dists shouldEqual Map(1 -> List(1))
    }

    it("should handle disconnected graph") {
      object DisconnectedSimpleGraph extends IntGraph {
        val nodes: Set[N] = Set(1, 2, 3, 4, 5)
        val edges: Set[(N, N)] = Set(
          (1, 2),
          (2, 3),
          (4, 5)
        )
      }

      val dists = DisconnectedSimpleGraph.computePaths(1)
      dists shouldEqual Map(1 -> List(1), 2 -> List(2, 1), 3 -> List(3, 2, 1))

      val resultFrom4 = DisconnectedSimpleGraph.computePaths(4)
      resultFrom4 shouldEqual Map(4 -> List(4), 5 -> List(5, 4))
    }

    it("should handle a cycle in the graph") {
      object CyclicSimpleGraph extends IntGraph {
        val nodes: Set[N] = Set(1, 2, 3)
        val edges: Set[(N, N)] = Set(
          (1, 2),
          (2, 3),
          (3, 1)
        )
      }

      val dists = CyclicSimpleGraph.computePaths(1)
      dists shouldEqual Map(1 -> List(1), 2 -> List(2, 1), 3 -> List(3, 2, 1))
    }
  }
}
