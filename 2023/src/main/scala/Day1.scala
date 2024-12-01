import scala.io.Source
import scala.io.BufferedSource
import scala.collection.mutable.ListBuffer

/** Day one
  *
  * @see
  *   https://adventofcode.com/2023/day/1
  */
object Day1 extends Day {

  var WORD_MAP = Map(
    "1"     -> 1,
    "2"     -> 2,
    "3"     -> 3,
    "4"     -> 4,
    "5"     -> 5,
    "6"     -> 6,
    "7"     -> 7,
    "8"     -> 8,
    "9"     -> 9,
    "one"   -> 1,
    "two"   -> 2,
    "three" -> 3,
    "four"  -> 4,
    "five"  -> 5,
    "six"   -> 6,
    "seven" -> 7,
    "eight" -> 8,
    "nine"  -> 9
  )

  def getNumberFromList(list: List[Int]): Int = {
    if list.isEmpty then {
      0
    } else if list.size == 1 then {
      s"${list.head}${list.head}".toInt
    } else {
      s"${list.head}${list.last}".toInt
    }
  }

  def calculateNumberFromLine(line: String): Int = {
    var numbers = line.map(x => x.toInt - '0').filter(i => i <= 9)
    getNumberFromList(numbers.toList)

  }

  def calculateNumberFromWordInLine(line: String): Int = {
    var numbers: ListBuffer[Int] = ListBuffer()
    for i <- 0 until line.size do {
      for (key, value) <- WORD_MAP do {
        if i + key.size <= line.size && line.substring(i, i + key.size) == key then {
          numbers += value
        }
      }
    }

    getNumberFromList(numbers.toList)
  }

  def sumFile(source: BufferedSource): Int = {
    source.getLines.map(this.calculateNumberFromLine).sum
  }

  def sumFileWithWords(source: BufferedSource): Int = {
    source.getLines.map(this.calculateNumberFromWordInLine).sum
  }

  def run(): Unit = {
    var partOne = this.sumFile(Source.fromResource("day1.input"))
    var partTwo = this.sumFileWithWords(Source.fromResource("day1.input"))

    println(s"Part One: $partOne")
    println(s"Part Two: $partTwo")
  }
}
