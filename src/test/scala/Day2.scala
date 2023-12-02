import scala.io.Source

class Day2PartOneSuite extends munit.FunSuite {
  var game = Day2Game("Game 2: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

  test("it will parse the game with the correct game id") {
    assertEquals(game.id, 2)
  }

  test("it will have the correct number of rounds") {
    assertEquals(game.rounds.size, 3)
  }

  test("the first round will have the correct color data") {
    var round = game.rounds(0)

    assertEquals(round.red, 4)
    assertEquals(round.green, 0)
    assertEquals(round.blue, 3)
  }

  test("the second round will have the correct color data") {
    var round = game.rounds(1)

    assertEquals(round.red, 1)
    assertEquals(round.green, 2)
    assertEquals(round.blue, 6)
  }

  test("the third round will have the correct color data") {
    var round = game.rounds(2)

    assertEquals(round.red, 0)
    assertEquals(round.green, 2)
    assertEquals(round.blue, 0)
  }

  test("it will return a new round filled with the max count for each color") {
    var maxRound = game.getMaxRound()
    assertEquals(maxRound.red, 4)
    assertEquals(maxRound.green, 2)
    assertEquals(maxRound.blue, 6)
  }

  test("it will return true if the round is valid with a given data") {
    assert(game.isValid(10, 10, 10))
  }

  test("the game is valid if all the numbers are the same") {
    assert(game.isValid(4, 2, 6))
  }

  test("it will return false if the round is not valid with a given data") {
    assert(!game.isValid(6, 1, 1))
  }

  test("it will calculate the sub on the test data") {
    var source = Source.fromResource("day2.part1.test.input")
    var result = Day2.sumFile(source)

    assertEquals(result, 8)
  }

  test("it will calculate the sum on the data") {
    var source = Source.fromResource("day2.input")
    var result = Day2.sumFile(source)

    assertEquals(result, 2156)
  }
}

class Day2PartTwoSuite extends munit.FunSuite {
  test("will return the power for the game") {
    var power = Day2Game("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green").getPower()
    assertEquals(power, 48)
  }

  test("it will calculate the sub on the test data") {
    var source = Source.fromResource("day2.part2.test.input")
    var result = Day2.sumMultipliedFile(source)

    assertEquals(result, 2286)
  }

  test("it will calculate the sum on the data") {
    var source = Source.fromResource("day2.input")
    var result = Day2.sumMultipliedFile(source)

    assertEquals(result, 66909)
  }
}
