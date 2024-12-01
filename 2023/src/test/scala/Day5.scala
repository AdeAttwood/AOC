import scala.io.Source

class Day5PartOneSuite extends munit.FunSuite {
  var source  = Source.fromResource("day5.part1.test.input")
  var almanac = Day5Almanac(source)
  almanac.parse()

  test("it will get the list of seeds") {
    assertEquals(almanac.seeds.size, 4)
  }

  test("it will get the correct number of maps") {
    assertEquals(almanac.maps.size, 7)
  }

  test("it will get the seed key into the map") {
    assert(almanac.maps.get("seed").nonEmpty)
  }

  test("it will get the correct dest") {
    var seed = almanac.maps("seed")
    assertEquals(seed.destination, "soil")
  }

  test("it will get the map if we have been given it") {
    var seed = almanac.maps("seed")
    assertEquals(seed.getMapRange(97), BigInt(99))
  }

  test("it will use the first number if we have overlaps") {
    var seed = almanac.maps("seed")
    assertEquals(seed.getMapRange(98), BigInt(50))
  }

  test("it will map a number to") {
    var result = almanac.mapTo("seed", "location", 79)
    assertEquals(result, BigInt(82))
  }

  test("it will get the lowest seed map with test data") {
    var result = almanac.lowestSeedNumber()
    assertEquals(result, BigInt(35))
  }

  test("it will do this with actual input") {
    var source  = Source.fromResource("day5.input")
    var almanac = Day5Almanac(source)
    almanac.parse()

    var result = almanac.lowestSeedNumber()
    assertEquals(result, BigInt("551761867"))
  }
}

class Day5PartTwoSuite extends munit.FunSuite {
  test("it will calculate the range with test data") {
    var source  = Source.fromResource("day5.part1.test.input")
    var almanac = Day5Almanac(source)
    almanac.parse()

    var result = almanac.lowestSeedRange();
    assertEquals(result, BigInt("46"))
  }

  // test("it will calculate the range with real data") {
  //   var source  = Source.fromResource("day5.input")
  //   var almanac = Day5Almanac(source)
  //   almanac.parse()
  //
  //   var result = almanac.lowestSeedRange();
  //   assertEquals(result, BigInt("46"))
  // }
}
