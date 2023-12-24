import scala.io.Source

class Day6PartOneSuite extends munit.FunSuite {
  test("it will calculate the correct distance for a 7ms race") {
    var result = Day6.calculateDistance(1, 7)
    assertEquals(result, 6L)
  }

  test("it will get all of the winning charge times") {
    var result = Day6.getWinningCharges(7, 9)
    assertEquals(result.size, 4)
  }

  test("it will get the correct product") {
    assertEquals(Day6.partOne(Day6.partOneRacesTest), 288)
  }

  test("it will get the correct product with actual data") {
    assertEquals(Day6.partOne(Day6.partOneRaces), 275724)
  }
}

class Day6PartTwoSuite extends munit.FunSuite {
  test("it will use the new algo on the old small set of data") {
    assertEquals(Day6.partTwo(7, 9), 4L)
  }

  test("it will do it on a really big number") {
    assertEquals(Day6.partTwo(71530, 940200L), 71503L)
  }

  test("it will do it on the actuall numbers") {
    assertEquals(Day6.partTwo(59688274, 543102016641022L), 37286485L)
  }
}
