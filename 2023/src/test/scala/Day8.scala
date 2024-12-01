import scala.io.Source

class Day8NodeSuite extends munit.FunSuite {
  var node = Day8Node("AAA = (BBB, CCC)")

  test("it will parse the node key") {
    assertEquals(node.key, "AAA")
  }

  test("it will parse the left") {
    assertEquals(node.left, "BBB")
  }

  test("it will parse the right") {
    assertEquals(node.right, "CCC")
  }
}
