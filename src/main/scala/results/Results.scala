package results

import scala.io.Source
import scala.language.postfixOps

case class Results(id: Int, name: String, points: IndexedSeq[Int])

enum Grade:
  case EXCELLENT, GOOD, SATISFACTORY, SUFFICIENT, INSUFFICIENT

object ResultsAnalysis {

  def main(args: Array[String]): Unit = {

    val source = Source.fromFile("files/results.csv")
    val lines: List[String] = source.getLines.toList
    source.close()

    println("\n===================================================================================================")
    println("     Task 4.1: List of Results objects     ")
    println("===================================================================================================\n")

    val resultList: List[Results] = lines.drop(1)
      .map(_.split(",").map(_.trim).filter(_.length != 12))
      .map(arr => Results(arr(0).toInt, arr(1), arr.drop(2).map(_.toInt).toVector))

    println(s"Resultlist: $resultList")

    println("\n===================================================================================================")
    println("     Task 4.2: Number of solved tasks     ")
    println("===================================================================================================\n")

    val nSolvedPerStnd: Map[String, Int] = resultList
      .map(r => (r.name, r.points.count(p => p >= 3))).toMap
      .map((k, v) => (k, v))

    println(s"nSolvedPerStnd: $nSolvedPerStnd")

    println("\n===================================================================================================")
    println("     Task 4.3: Sufficient tasks solved     ")
    println("===================================================================================================\n")

    val sufficientSolved: (Set[String], Set[String]) = {
      val (s, ns) = resultList.partition(r => r.points.count(p => p >= 3) >= 8)
      (s.map(_.name).toSet, ns.map(_.name).toSet)
    }

    println(s"sufficient: ${sufficientSolved._1}")
    println(s"insufficient: ${sufficientSolved._2}")

    println("\n===================================================================================================")
    println("     Task 4.4: Grading     ")
    println("===================================================================================================\n")

    val grades: Map[String, Grade] = resultList
      .map(r => (r.name, computeGrade(r.points)))
      .toMap

    println(grades)

    println("\n===================================================================================================")
    println("     Task 4.5: Grade statistics     ")
    println("===================================================================================================\n")

    val nStudentsWithGrade: Map[Grade, Int] = {
      val grades = resultList.map(r => (r.name, computeGrade(r.points)))
      grades.groupMap(_._2)(_._1).map((k, v) => (k, v.size))
    }

    println(nStudentsWithGrade)

    println("\n===================================================================================================")
    println("     Task 4.6: Number solved per assignment     ")
    println("===================================================================================================\n")

    val nSolvedPerAssnmt: List[(Int, Int)] = (1 to 10).map(i => {
      val valids = resultList.map(_.points(i - 1)).count(_ >= 3)
      (i, valids)
    }).toList

    println(nSolvedPerAssnmt)

    println("\n===================================================================================================")
    println("     Task 4.7.: Average points per assignment     ")
    println("===================================================================================================\n")

    val avrgPointsPerAssnmt: Map[Int, Double] = (1 to 10).map(i => {
      val valids = resultList.map(_.points(i - 1)).filter(_ >= 0)
      (i, valids.sum.toDouble / valids.size)
    }).toMap

    println(avrgPointsPerAssnmt)
  }

  private def computeGrade(points: IndexedSeq[Int]): Grade = {
    if points.count(p => p >= 3) < 8 then Grade.INSUFFICIENT
    else {
      val avrg = points.sorted.drop(2).sum / 8
      if avrg < 5.0 then Grade.INSUFFICIENT
      else if avrg < 6.5 then Grade.SUFFICIENT
      else if avrg < 8.0 then Grade.SATISFACTORY
      else if avrg < 9.0 then Grade.GOOD
      else Grade.EXCELLENT
    }
  }
}
