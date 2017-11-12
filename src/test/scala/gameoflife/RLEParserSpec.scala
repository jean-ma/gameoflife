package gameoflife

import org.scalatest.FlatSpec

class RLEParserSpec extends FlatSpec {
  "parser" should "parse single element" in {
    val expected = Vector(Vector(Dead))
    val inputGrid = "o!"

    assert(RLEParser(inputGrid) === expected)
  }

  "parser" should "parse Dead element with factor" in {
    val expected = Vector(Vector(Dead, Dead))
    val inputGrid = "2o!"

    assert(RLEParser(inputGrid) === expected)
  }

  "parser" should "parse dead and alive elements with factor" in {
    val expected = Vector(Vector(Alive, Alive, Dead))
    val inputGrid = "2bo!"

    assert(RLEParser(inputGrid) === expected)
  }

  "parser" should "parse 3 lines" in {
    val expected = Vector(
      Vector(Alive, Alive, Dead),
      Vector(Dead, Alive, Dead),
      Vector(Dead, Alive, Alive, Dead, Alive))
    val inputGrid = "2bo$obo$o2bob!"

    assert(RLEParser(inputGrid) === expected)
  }
}