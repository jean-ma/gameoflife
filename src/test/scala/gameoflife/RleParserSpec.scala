package gameoflife

import org.scalatest.FlatSpec

class RleParserSpec extends FlatSpec {

  "parser" should "parse with new line with factor" in {
    val expected = Vector(
      Vector(Dead, Dead, Alive, Dead, Dead),
      Vector.fill(5)(Dead),
      Vector.fill(5)(Dead),
      Vector(Alive, Dead, Dead, Alive, Dead),
      Vector.fill(5)(Dead))

    val inputGrid =
      """
        |x = 5, y = 5
        |2bo3$
        |o2bob!
        |""".stripMargin

    val actual = RleParser(inputGrid)


    expected.zip(actual.zipWithIndex).foreach {
      case (le, (la, indexX)) => le.zip(la.zipWithIndex).foreach {
        case (a, (e, indexY)) => assert(e === a, s"Error at index ($indexX, $indexY)")
      }
    }
  }

  "parser" should "parse a file with x, y information" in {
    val inputGrid =
      """
        |# here is a comment
        |x = 10, y = 10
        |2bo3$
        |o2bob!
        |""".stripMargin

    assert(10 === RleParser(inputGrid).size)
    assert(10 === RleParser(inputGrid).head.size)
  }
}