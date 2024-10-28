package results

import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.language.postfixOps

case class Results(id: Int, name: String, points: IndexedSeq[Int])

enum Grade:
  case EXCELLENT, GOOD, SATISFACTORY, SUFFICIENT, INSUFFICIENT

object ResultsAnalysis {

  def main(args: Array[String]): Unit = {

    val lines: List[String] = Source.fromFile("files/results.csv").getLines.toList

    println(lines)

    // Task 4.1: List of Results objects
    val resultList: List[Results] =
      lines.drop(1)
        .map(_.split(",").map(_.trim).filter(_.length != 12))
        .map(arr => Results(arr(0).toInt, arr(1), arr.drop(2).map(_.toInt).toVector))

    println(resultList)

    // Task 4.2: Number of solved tasks
    val nSolvedPerStnd: Map[String, Int] = resultList
      .map(r => (r.name, r.points.count(p => p >= 3))).toMap
      .map((k, v) => (k, v))

    println(nSolvedPerStnd)


    // Task 4.3: Sufficient tasks solved
    val sufficientSolved: (Set[String], Set[String]) = {
      val (s, ns) = resultList.partition(r => r.points.count(p => p >= 3) >= 8)
      (s.map(_.name).toSet, ns.map(_.name).toSet)
    }

    println(sufficientSolved)

    // Task 4.4: Grading
    val grades: Map[String, Grade] = ???
    println(grades)

    // Task 4.5: Grade statistics
    val nStudentsWithGrade: Map[Grade, Int] = ???
    println(nStudentsWithGrade)

    // Task 4.6: Number solved per assignment
    val nSolvedPerAssnmt: List[(Int, Int)] = ???
    println(nSolvedPerAssnmt)

    // Task 4.7.: Average points per assignment
    val avrgPointsPerAssnmt: Map[Int, Double] =
      (1 to 10).map(i => {
        val valids = resultList.map(_.points(i)).filter(_ >= 0)
        (i, valids.sum.toDouble / valids.size)
      }).toMap

    println(avrgPointsPerAssnmt)
  }

  private def computeGrade(points: IndexedSeq[Int]): Grade = {
    if (points.count(p => p >= 3) < 8) then Grade.INSUFFICIENT
    else {
      val avrg = points.sorted.drop(2).sum / 8
      if (avrg < 5.0) then Grade.INSUFFICIENT
      else if (avrg < 6.5) then Grade.SUFFICIENT
      else if (avrg < 8.0) then Grade.SATISFACTORY
      else if (avrg < 9.0) then Grade.GOOD
      else Grade.EXCELLENT
    }
  }

}
