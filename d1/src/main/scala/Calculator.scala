import scala.annotation.tailrec
import scala.io.Source

object Calculator extends App {

  Console.println(FileCalculator.sumFile(Source.fromResource("input.txt")))
}

object FileCalculator {
  def sumFile(source: Source): Int =
    source
      .getLines()
      .foldLeft(0) { case (acc, line) => acc + CalculatorLogic.lineValue(line) }
}

object CalculatorLogic {
  private val numbers: List[(String, Int)] = List(
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine"
  ).zipWithIndex.map { case (n, v) => (n, v + 1) }

  def lineValue(line: String): Int = {
    @tailrec
    def replaceLine(in: String): String = {
      val firstFound = numbers
        .map { case (n, v) => (n, v, in.indexOf(n)) }
        .filterNot { case (_, _, p) => p == -1 }
        .sortBy { case (_, _, p) => p }
        .headOption

      if (firstFound.isDefined) {
        val (n, v, _) = firstFound.get
        replaceLine(in.replaceFirst(n, v.toString))
      } else
        in
    }

    val replaced = replaceLine(line)

    val number = replaced.filter(_.isDigit)

    val first = number.head
    val last = number.last

    val result = s"$first$last".toInt
    result
  }
}
