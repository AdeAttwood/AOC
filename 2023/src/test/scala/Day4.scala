import scala.io.Source

class Day4PartOneSuite extends munit.FunSuite {
  var card = Day4Card("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");

  test("it will get the correct number of winning numbers") {
    assertEquals(card.winningNumbers.size, 5)
  }

  test("it will contain the the correct number of my numbers") {
    assertEquals(card.myNumbers.size, 8)
  }

  test("it will contain the winning numbers") {
    assert(card.winningNumbers.contains(41))
  }

  test("it will get the correct winning number from my set") {
    assertEquals(card.winners().size, 4)
  }

  test("it will calculate the correct score") {
    assertEquals(card.score(), 8)
  }

  test("it will parse the card correct for this data") {
    var card = Day4Card("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1")
    assert(card.myNumbers.contains(1))
  }

  test("it will calculate the number of the test data") {
    var source = Source.fromResource("day4.part1.test.input")
    var result = Day4.sumScore(source)

    assertEquals(result, 13)
  }

  test("it will calculate the number of the actual data") {
    var source = Source.fromResource("day4.input")
    var result = Day4.sumScore(source)

    assertEquals(result, 21821)
  }
}

class Day4PartTwoSuite extends munit.FunSuite {
  test("it will process the test data") {
    var source = Source.fromResource("day4.part1.test.input")
    var cards  = source.getLines.map(line => Day4Card(line)).toList
    var result = Day4Deck(cards).score()

    assertEquals(result, 30)
  }

  test("it will process the real data") {
    var source = Source.fromResource("day4.input")
    var cards  = source.getLines.map(line => Day4Card(line)).toList
    var result = Day4Deck(cards).score()

    assertEquals(result, 5539496)
  }
}
