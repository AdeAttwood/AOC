import scala.io.Source
import scala.io.BufferedSource
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

class Day2Round(input: String) {
  var red   = 0
  var green = 0
  var blue  = 0

  private var roundPattern = """(\d+)\s(red|green|blue)""".r

  parseRound(input)

  private def parseRound(input: String) = {
    val data = roundPattern.findAllMatchIn(input).map(m => (m.group(2), m.group(1))).foreach {
      case ("red", count)   => this.red = count.toInt
      case ("green", count) => this.green = count.toInt
      case ("blue", count)  => this.blue = count.toInt
    }
  }
}

class Day2Game(input: String) {
  var id                      = 0
  var rounds: List[Day2Round] = List()

  private var gamePattern = """Game (\d+): (.*?)$""".r

  parseGame(input)

  /** Parse the game data into the game object
    *
    * This will extract the game id and all of the round information from the game data. This is an example of
    * the game data
    *
    * Game 2: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    */
  private def parseGame(input: String) = {
    input match {
      case gamePattern(gameId, roundsData) => {
        this.id = gameId.toInt
        this.rounds = roundsData.split(";").map(round => Day2Round(round)).toList
      }
    }
  }

  /** Create a new round with red green and blue populated with the max count from all the rounds
    *
    * This will go though all of the rounds and get pick out the max number of each of the colors. It will
    * then create a new round with each of these values populated.
    */
  def getMaxRound(): Day2Round = {
    var round = Day2Round("")

    this.rounds.foreach(currentRound => {
      if currentRound.red > round.red then {
        round.red = currentRound.red
      }

      if currentRound.green > round.green then {
        round.green = currentRound.green
      }

      if currentRound.blue > round.blue then {
        round.blue = currentRound.blue
      }
    })

    return round;
  }

  /** Get the power of the game
    *
    * The power of a set of cubes is equal to the numbers of red, green, and blue cubes multiplied together
    * from the max round
    */
  def getPower(): Int = {
    var round = this.getMaxRound()
    round.red * round.green * round.blue
  }

  /** Tests to see if this game is valid
    *
    * A game is valid when all of the rounds contain red, green and blue numbers that are less then the given
    * numbers passed in.
    */
  def isValid(red: Int, green: Int, blue: Int): Boolean = {
    var maxRound = this.getMaxRound()

    maxRound.red <= red && maxRound.green <= green && maxRound.blue <= blue
  }
}

/** Day two
  *
  * @see
  *   https://adventofcode.com/2023/day/2
  */
object Day2 extends Day {
  def sumFile(source: BufferedSource): Int = {
    source.getLines
      .map(line => {
        var game = Day2Game(line)
        if game.isValid(12, 13, 14) then {
          game.id
        } else {
          0
        }
      })
      .sum
  }

  def sumMultipliedFile(source: BufferedSource): Int = {
    source.getLines.map(line => Day2Game(line).getPower()).sum
  }

  def run(): Unit = {
    var partOne = this.sumFile(Source.fromResource("day2.input"))
    var partTwo = this.sumMultipliedFile(Source.fromResource("day2.input"))

    println(s"Part One: $partOne")
    println(s"Part Two: $partTwo")
  }
}
