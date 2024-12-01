import scala.io.Source

class Day1PartOneSuite extends munit.FunSuite {
  test("it will return the first and last numbers") {
    var number = Day1.calculateNumberFromLine("1abc2")
    assertEquals(number, 12)
  }

  test("it will return the correct number if the numbers are in the middle of a string") {
    var number = Day1.calculateNumberFromLine("pqr3stu8vwx")
    assertEquals(number, 38)
  }

  test("it will return the correct number if there are more than two numbers") {
    var number = Day1.calculateNumberFromLine("a1b2c3d4e5f")
    assertEquals(number, 15)
  }

  test("ti will return the correct number if there is only one number in the string") {
    var number = Day1.calculateNumberFromLine("treb7uchet")
    assertEquals(number, 77)
  }

  test("it will calculate the sub of all the numbers from a file") {
    var source = Source.fromResource("day1.part1.test.input")
    var result = Day1.sumFile(source)
    assertEquals(result, 142)
  }

  test("it will calculate the actual value") {
    var source = Source.fromResource("day1.input")
    var result = Day1.sumFile(source)
    assertEquals(result, 54951)
  }
}

class Day1PartTwoSuite extends munit.FunSuite {
  test("it will return the first and last numbers") {
    var number = Day1.calculateNumberFromWordInLine("two1nine")
    assertEquals(number, 29)
  }

  test("it will return the first and last numbers if they are in the middle") {
    var number = Day1.calculateNumberFromWordInLine("eightwothree")
    assertEquals(number, 83)
  }

  test("it will return the number if the word if before the digit") {
    var number = Day1.calculateNumberFromWordInLine("abcone2threexyz")
    assertEquals(number, 13)
  }

  test("it will return the number if some words are overlapping") {
    var number = Day1.calculateNumberFromWordInLine("xtwone3four")
    assertEquals(number, 24)
  }

  test("it will return the number if there are digits at the start and end") {
    var number = Day1.calculateNumberFromWordInLine("4nineeightseven2")
    assertEquals(number, 42)
  }

  test("it will return the number if there is a word at the start and numbe at the end") {
    var number = Day1.calculateNumberFromWordInLine("zoneight234")
    assertEquals(number, 14)
  }

  test("it will return the number if there is a diget at the start and word at the end") {
    var number = Day1.calculateNumberFromWordInLine("7pqrstsixteen")
    assertEquals(number, 76)
  }

  test("it will return the correct number if the overlap is at the end") {
    var number = Day1.calculateNumberFromWordInLine("four7four92lfqkmgponeightbf")
    assertEquals(number, 48)
  }

  test("it will calculate the sub of all the numbers from a file") {
    var source = Source.fromResource("day1.part2.test.input")
    var result = Day1.sumFileWithWords(source)
    assertEquals(result, 281)
  }

  test("it will calculate the actual value") {
    var source = Source.fromResource("day1.input")
    var result = Day1.sumFileWithWords(source)
    assertEquals(result, 55218)
  }
}
