import scala.io.Source
import scala.io.BufferedSource
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import scala.math.min

class Day5Range(var start: BigInt, var destinationStart: BigInt, var length: Int) {
  var end = this.start + BigInt(this.length)

  def contains(number: BigInt): Boolean = {
    number >= this.start && number <= this.end
  }

  def map(number: BigInt): BigInt = {
    this.destinationStart + (number - this.start)
  }
}

class Day5Map(var source: String, var destination: String) {
  var ranges: List[Day5Range] = List()
  def addMapRange(start: BigInt, end: BigInt, length: Int) = {
    ranges = ranges :+ Day5Range(start, end, length)
  }

  def getMapRange(start: BigInt): BigInt = {
    this.ranges.find(_.contains(start)) match {
      case Some(range) => range.map(start)
      case None        => start
    }
  }
}

class Day5Almanac(source: BufferedSource) {
  var seeds: List[BigInt]        = List()
  var maps: Map[String, Day5Map] = Map()

  var currentMap: Option[Day5Map] = None

  private var seedPattern     = """^(seeds:) (.*?)$""".r
  private var mapStartPattern = """(\S+)-to-(\S+) map:""".r
  private var numberPattern   = """(\d+) (\d+) (\d+)""".r

  def parse() = {
    for line <- source.getLines do {
      line match {
        case seedPattern(_, seeds) => this.seeds = seeds.split(" ").map(BigInt(_)).toList

        case mapStartPattern(source, destination) => {
          if this.currentMap != None then {
            this.maps += (this.currentMap.get.source -> this.currentMap.get)
          }

          this.currentMap = Some(new Day5Map(source, destination))
        }

        case numberPattern(destinationStart, sourceStart, length) => {
          this.currentMap.get.addMapRange(BigInt(sourceStart), BigInt(destinationStart), length.toInt)
        }

        // Skip anything that dose not match any of our patterns
        case _ => {}
      }
    }

    // Add the last one to the map list
    this.maps += (this.currentMap.get.source -> this.currentMap.get)
  }

  def mapTo(source: String, destination: String, number: BigInt): BigInt = {
    var map = this.maps.get(source)
    if map.isEmpty then {
      throw new Exception(s"Could not find map for $source")
    }

    var current   = map.get
    var mapNumber = number

    while current.destination != destination do {
      mapNumber = current.getMapRange(mapNumber)

      var next = this.maps.get(current.destination)
      if next.isEmpty then {
        throw new Exception(s"Could not find map for $source")
      }

      current = next.get
    }

    current.getMapRange(mapNumber)
  }

  def lowestSeedNumber(): BigInt = {
    this.seeds.map(seed => this.mapTo("seed", "location", seed)).min
  }

  def lowestSeedRange(): BigInt = {
    this.seeds
      .grouped(2)
      .toList
      .map(item => (item.head, item.head + item.last))
      .map((start, end) => {
        var lowest: Option[BigInt] = None
        for i <- start to end do {
          var result = this.mapTo("seed", "location", i);
          if lowest.isEmpty || result < lowest.get then {
            lowest = Some(result)
          }
        }

        lowest.getOrElse(BigInt(0))
      })
      .min
  }
}

/** Day five
  *
  * @see
  *   https://adventofcode.com/2023/day/5
  */
object Day5 extends Day {
  def partOne(source: BufferedSource): BigInt = {
    var almanac = Day5Almanac(source)
    almanac.parse()

    almanac.lowestSeedNumber()
  }

  def partTwo(source: BufferedSource): BigInt = {
    var almanac = Day5Almanac(source)
    almanac.parse()

    almanac.lowestSeedRange()
  }

  def run(): Unit = {
    var partOne = this.partOne(Source.fromResource("day5.input"))
    // var partTwo = this.partTwo(Source.fromResource("day5.input"))

    println(s"Part One: $partOne")
    // println(s"Part Two: $partTwo")
  }
}
