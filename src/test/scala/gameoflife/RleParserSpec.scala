package gameoflife

import org.scalatest.FlatSpec

class RleParserSpec extends FlatSpec {
  "parser" should "parse single element" in {
    val expected = Vector(Vector(Alive))
    val inputGrid = "o!"

    assert(RleParser(inputGrid) === expected)
  }

  "parser" should "parse Dead element with factor" in {
    val expected = Vector(Vector(Alive, Alive))
    val inputGrid = "2o!"

    assert(RleParser(inputGrid) === expected)
  }

  "parser" should "parse dead and alive elements with factor" in {
    val expected = Vector(Vector(Dead, Dead, Alive))
    val inputGrid = "2bo!"

    assert(RleParser(inputGrid) === expected)
  }

  "parser" should "parse 3 lines" in {
    val expected = Vector(
      Vector(Dead, Dead, Alive),
      Vector(Alive, Dead, Alive),
      Vector(Alive, Dead, Dead, Alive, Dead))
    val inputGrid = "2bo$obo$o2bob!"

    assert(RleParser(inputGrid) === expected)
  }

  "parser" should "parse empty lines" in {
    val expected = Vector(
      Vector(Dead, Dead, Alive),
      Vector.empty[Liveness],
      Vector(Alive, Dead, Dead, Alive, Dead))
    val inputGrid = "2bo$$o2bob!"

    assert(RleParser(inputGrid) === expected)
  }

  "parser" should "parse with new line" in {
    val expected = Vector(
      Vector(Dead, Dead, Alive),
      Vector.empty[Liveness],
      Vector(Alive, Dead, Dead, Alive, Dead))
    val inputGrid =
      """
        |2bo$$
        |o2bob!
        |""".stripMargin

    assert(RleParser(inputGrid) === expected)
  }

  "parser" should "parse with new line with factor" in {
    val expected = Vector(
      Vector(Dead, Dead, Alive),
      Vector.empty[Liveness],
      Vector.empty[Liveness],
      Vector(Alive, Dead, Dead, Alive, Dead))
    val inputGrid =
      """
        |2bo3$
        |o2bob!
        |""".stripMargin

    assert(RleParser(inputGrid) === expected)
  }

  "parser" should "parse a file with comments" in {
    val expected = Vector(
      Vector(Dead, Dead, Alive),
      Vector.empty[Liveness],
      Vector.empty[Liveness],
      Vector(Alive, Dead, Dead, Alive, Dead))
    val inputGrid =
      """
        |# here is a comment
        |2bo3$
        |o2bob!
        |""".stripMargin

    assert(RleParser(inputGrid) === expected)
  }
}