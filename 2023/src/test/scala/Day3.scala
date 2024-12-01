import scala.io.Source

class Day3PartOneSuite extends munit.FunSuite {
  test("it will work on the test input") {
    var source = Source.fromResource("day3.part1.test.input")
    var result = Day3.sumFile(source)

    assertEquals(result, 4361)
  }

  test("it will work on the actual input") {
    var source = Source.fromResource("day3.input")
    var result = Day3.sumFile(source)

    assertEquals(result, 553079)
  }
}

class Day3PartTwoSuite extends munit.FunSuite {
  test("it will work on the test input") {
    var source = Source.fromResource("day3.part1.test.input")
    var result = Day3.sumGearsFromFile(source)

    assertEquals(result, 467835)
  }

  test("it will work on the actual input") {
    var source = Source.fromResource("day3.input")
    var result = Day3.sumGearsFromFile(source)

    assertEquals(result, 84363105)
  }
}
