import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks._

import scala.io.Source

class CalculatorSpec extends AnyFlatSpec with Matchers {

  behavior of "Calculator"

  it should "return correct values for part 1" in {
    forAll(
      Table(
        ("line", "value"),
        ("1abc2", 12),
        ("pqr3stu8vwx", 38),
        ("a1b2c3d4e5f", 15),
        ("treb7uchet", 77)
      )
    ) { (line, value) => CalculatorLogic.lineValue(line) shouldBe value }
  }

  it should "return correct values for part 2" in {
    forAll(
      Table(
        ("line", "value"),
        ("two1nine", 29),
        ("eightwothree", 83),
        ("abcone2threexyz", 13),
        ("xtwone3four", 24),
        ("4nineeightseven2", 42),
        ("zoneight234", 14),
        ("7pqrstsixteen", 76)
      )
    ) { (line, value) => CalculatorLogic.lineValue(line) shouldBe value }
  }

  it should "handle files" in {
    FileCalculator.sumFile(Source.fromResource("test1.txt")) shouldBe 142
    FileCalculator.sumFile(Source.fromResource("test2.txt")) shouldBe 281
  }

}
