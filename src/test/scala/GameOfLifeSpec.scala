
import GameOfLife.{Universe, Alive => A, Dead => D}
import org.scalatest.FlatSpec

import scala.util.Try

class GameOfLifeSpec extends FlatSpec {

  "An isolated cell" should "die" in {
    assert(GameOfLife.next(universe)(isolatedCell) === D)
  }

  "Neighbors" should "return the number of alive neighbors" in {
    assert(GameOfLife.neighbors(universe, isolatedCell) === 1)
  }

  "universe" should "give life to a dead cell with 3 Alive neighbors" in {
    assert(GameOfLife.next(universe)(deadToAliveCell) === A)
  }

  val universe: Universe =
    cell => {
      val space = Vector(
        Vector(D, D, D, D),
        Vector(D, D, A, D),
        Vector(D, D, D, A),
        Vector(D, D, A, D)
      )

      Try {
        space(cell.x)(cell.y)
      } getOrElse D
    }

  val isolatedCell = Cell(1, 2)

  val borderCell = Cell(2, 3)

  val deadToAliveCell = Cell(2, 2)
}