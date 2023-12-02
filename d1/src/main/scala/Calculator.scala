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
  val regex = """\d|one|two|three|four|five|six|seven|eight|nine""".r
  val digitsMap = Map(
    "one" -> 1,
    "two" -> 2,
    "three" -> 3,
    "four" -> 4,
    "five" -> 5,
    "six" -> 6,
    "seven" -> 7,
    "eight" -> 8,
    "nine" -> 9
  )

  def lineValue(line: String): Int = {
    val all = Vector.unfold(line) { line =>
      for {
        m <- regex.findFirstMatchIn(line)
        ds = m.matched
        di = digitsMap.getOrElse(ds, ds.toInt)
      } yield (di, line.substring(m.start + 1))
    }

    val f = all.head
    val l = all.last
    f * 10 + l
  }
}
