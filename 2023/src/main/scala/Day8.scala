import scala.io.{ BufferedSource, Source }
import scala.util.control.Breaks.*

class Day8Node(input: String) {
  var key: String   = ""
  var left: String  = ""
  var right: String = ""

  private var nodePattern = """([A-Z]+) = \(([A-Z]+), ([A-Z]+)\)""".r

  input match {
    case nodePattern(key, left, right) => {
      this.key = key
      this.left = left
      this.right = right
    }
  }
}

class InstrctionIterator extends Iterator[String] {
  var instrctions =
    "LRLRRLRLRLLRRRLLLRLLRRLLRRRLRLRLRRRLRRLRLRRRLRRRLRRRLRRRLRRLRRRLRRRLRRLLLRLLRLRRRLRRRLRRLRLRLRLRRRLRRRLRRRLRRLRRLRRLLRRLRRRLLRRLRRRLRRRLRRRLRLRRLRLRRRLRRLLRLRLLRLRLRRRLRLRRLLRRRLRLRLRLRLRLRRLRLRRLLLLRRLRRLRRRLRRLRRLRRRLRRLRRRLLRLRRLLRLRRLRRLRRLLRRRLRLRLRRRLRRLRLLRLRRRR"

  var index            = 0
  def hasNext: Boolean = true

  def nextIndex(): Int = {
    if index >= instrctions.length - 1 then {
      0
    } else {
      index + 1
    }
  }

  def next(): String = {
    var item = instrctions(index).toString
    index = nextIndex()

    item
  }
}

/** Day eight
  *
  * @see
  *   https://adventofcode.com/2023/day/8
  */
object Day8 extends Day {

  def partOne(): Int = {
    var input = Source.fromResource("day8.input").getLines.map(Day8Node(_)).map(n => n.key -> n).toMap

    var output = InstrctionIterator()
      .scanLeft((0, input.get("AAA")))((_current, item) => {
        var (index, current) = _current

        (
          index + 1,
          current match {
            case None => None
            case Some(node) =>
              item match {
                case "L" => input.get(node.left)
                case "R" => input.get(node.right)
              }
          }
        )
      })
      .takeWhile((item) => {
        item._2 match {
          case None => false
          case Some(node) => {
            println(node.key)
            node.key != "ZZZ"
          }
        }
      })
      .toList
      .last

    println(output)

    0
  }

  def run(): Unit = {
    var partOne = this.partOne()
    // var partOne = this.partOne(this.partOneRaces)
    // var partTwo = this.partTwo(59688274, 543102016641022L)
    //
    println(s"Part One: $partOne")
    // println(s"Part Two: $partTwo")
    //
  }
}
