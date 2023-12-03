import scala.io.Source
import scala.io.BufferedSource
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import scala.math.max
import scala.math.min

/** Day three
  *
  * @see
  *   https://adventofcode.com/2023/day/3
  */
object Day3 extends Day {
  var numberPatter = """\d+""".r
  var symbolPatter = """.*[^\d\w\.].*""".r
  var startPattern = """\*""".r

  def sumFile(source: BufferedSource): Int = {
    var lines = source.getLines.toList
    lines.zipWithIndex
      .map((line, index) => {
        numberPatter
          .findAllMatchIn(line)
          .map(m => {
            // Get the start and end positions, this is plus or minus one from the
            // match so we can check around the number in all directions. The
            // directions we need to check are up, down, left, right, including
            // diagonals.
            var start = if m.start > 0 then m.start - 1 else 0
            var end   = if m.end < line.length - 1 then m.end + 1 else m.end

            // Find the char to the left. If we are at the start of the string the
            // start and match start will be the same and substring will return an
            // empty string
            var left = line.substring(start, m.start)

            // Find the char to the right. If we are at the start of the string the
            // start and match start will be the same and substring will return an
            // empty string
            var right = line.substring(m.end, end)

            // Find the hole string on the line above that overlaps this string. If
            // we are on the first line return an empty string
            var top = if index > 0 then lines(index - 1).substring(start, end) else ""

            // Find the hole string on the line below that overlaps this string. If
            // we are on the last line return an empty string
            var bottom = if index < lines.length - 1 then lines(index + 1).substring(start, end) else ""

            s"$left$right$top$bottom" match {
              case symbolPatter() => m.toString.toInt
              case _              => 0
            }
          })
          .sum
      })
      .sum
  }

  def sumGearsFromFile(source: BufferedSource): Int = {
    var lines = source.getLines.toList
    lines.zipWithIndex
      .map((line, x) => {
        var top    = max(0, x - 1)
        var bottom = min(lines.length, x + 2)

        // Map over all of the `*` chars in the current line
        startPattern
          .findAllMatchIn(line)
          .map(startMatch => {
            var point = startMatch.start
            var left  = max(0, point - 1)
            var right = min(line.length - 1, point + 1)

            var numbersOverlapping = lines
              // Get the lines above and below the current lines
              .slice(top, bottom)
              .map(line => {
                // Get all the numbers out of the lines
                numberPatter
                  .findAllMatchIn(line)
                  // Check to see if the match is in the range of points we
                  // need to check the left and right points to so we can
                  // capture the diagonal matches
                  .filter(m =>
                    m.start < left && m.end > left || m.start <= point && m.end >= point || m.start <= right && m.end >= right
                  )
                  .map(_.toString.toInt)
                  .toList
              })
              .flatten

            if numbersOverlapping.size == 2 then numbersOverlapping.reduce(_ * _) else 0
          })
          .sum
      })
      .sum
  }

  def run(): Unit = {
    var partOne = this.sumFile(Source.fromResource("day3.input"))
    var partTwo = this.sumGearsFromFile(Source.fromResource("day3.input"))

    println(s"Part One: $partOne")
    println(s"Part Two: $partTwo")
  }
}
