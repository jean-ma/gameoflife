package gameoflife

import org.scalatest.FlatSpec

class UniverseImplSpec extends FlatSpec {
  "parser" should "parse single element" in {
    val rows = 200
    val columns = 100
    val u = UniverseImpl.next(
      UniverseImpl.from(InitUniverse.glider), rows, columns
    )

    val count = {
      for {
        i <- 0 until rows
        j <- 0 until columns
      } yield {
        u(Cell(i, j))
      }
    }.count { _ == Alive }

    assert( count > 0)
  }
}
