import scala.io.Source
import scala.io.BufferedSource
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import scala.math.pow

class Day4Card(input: String) {
  var winningNumbers: List[Int] = List()
  var myNumbers: List[Int]      = List()

  var numberPattern: Regex = """\d+(?![^:]*:)""".r

  parse(input)

  def parse(input: String) = {
    var sides = input.split('|')
    winningNumbers = numberPattern.findAllMatchIn(sides(0)).map(_.toString.toInt).toList
    myNumbers = numberPattern.findAllMatchIn(sides(1)).map(_.toString.toInt).toList
  }

  def winners(): List[Int] = myNumbers.filter(n => winningNumbers.contains(n))

  def score(): Int = {
    winners().indices.inclusive.reduce((acc, item) => if acc == 0 then 1 else acc * 2)
  }
}

class Day4Deck(private var cards: List[Day4Card]) {
  var sum = 0

  private def processCard(index: Int, card: Day4Card): Unit = {
    var score = card.winners().length
    sum += 1

    var nextIndex = index + 1
    for i <- nextIndex until nextIndex + score do {
      // Don't really need a check here, but it makes me feel better
      if i < cards.length then {
        this.processCard(i, cards(i))
      }
    }
  }

  def score(): Int = {
    for (card, index) <- this.cards.zipWithIndex do {
      this.processCard(index, card)
    }

    this.sum
  }
}

/** Day four
  *
  * @see
  *   https://adventofcode.com/2023/day/4
  */
object Day4 extends Day {
  def sumScore(source: BufferedSource): Int = {
    source.getLines.map(line => Day4Card(line).score()).sum
  }

  def sumScratchcards(source: BufferedSource): Int = {
    var cards = source.getLines.map(line => Day4Card(line)).toList
    Day4Deck(cards).score()
  }

  def run(): Unit = {
    var partOne = this.sumScore(Source.fromResource("day4.input"))
    var partTwo = this.sumScratchcards(Source.fromResource("day4.input"))

    println(s"Part One: $partOne")
    println(s"Part Two: $partTwo")
  }
}
