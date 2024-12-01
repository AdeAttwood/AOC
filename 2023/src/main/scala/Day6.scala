import scala.io.{ BufferedSource, Source }
import scala.util.control.Breaks.*

/** Day five
  *
  * @see
  *   https://adventofcode.com/2023/day/5
  */
object Day6 extends Day {
  var partOneRacesTest = List((7, 9), (15, 40), (30, 200))
  var partOneRaces     = List((59, 543), (68, 1020), (82, 1664), (74, 1022))

  var partTwoTest = (71530, 940200L)
  var partTwoData = (59688274, 543102016641022L)

  /** Calculate the total distance the boat would have travel
    *
    * Given the amount of time we are holding the power button this will calculate the distance a boat would
    * travel
    *
    * @param chargeTime
    *   The amount of time we need to hold down the charge button
    * @param duration
    *   The total time of the race
    *
    * @return
    *   The distance the boat could have traveled in the duration given the amount of charge it has
    */
  def calculateDistance(chargeTime: Int, duration: Long): Long = {
    chargeTime * (duration - chargeTime)
  }

  def getWinningCharges(time: Int, distance: Int): List[Long] = {
    0.to(time).map(this.calculateDistance(_, time)).filter(_ > distance).toList
  }

  def partOne(input: List[(Int, Int)]): Int = {
    input.map((a, b) => getWinningCharges(a, b).size).product
  }

  def partTwo(time: Int, distance: Long): Long = {
    var previousDistance: Option[Long] = None;
    var count                          = 0;
    breakable {
      for i <- 0 to time do {
        var currentDistance = this.calculateDistance(i, time)
        if currentDistance > distance then {
          count += 1
        }

        // We are looping thought he numbers in order so when our distance
        // start to decrease then we know its not going to be going back up
        // again.
        if previousDistance.nonEmpty && currentDistance < distance && previousDistance.get > currentDistance
        then {
          break
        }

        previousDistance = Some(currentDistance)
      }
    }

    count
  }

  def run(): Unit = {
    var partOne = this.partOne(this.partOneRaces)
    var partTwo = this.partTwo(59688274, 543102016641022L)

    println(s"Part One: $partOne")
    println(s"Part Two: $partTwo")
  }
}
