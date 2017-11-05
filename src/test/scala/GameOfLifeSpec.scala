
import GameOfLife.{Universe, Alive => A, Dead => D}

import org.scalatest.FlatSpec

class GameOfLifeSpec extends FlatSpec {
  "An isolated cell" should "die" in {
    val isolatedCell = Cell(1, 2)

    assert(GameOfLife.next(universe)(isolatedCell) === D)
  }

  val universe: Universe =
    cell => {
      val space = Vector(
        Vector(D, D, D, D),
        Vector(D, D, A, D),
        Vector(D, D, D, A),
        Vector(D, D, A, D)
      )
      space(cell.x)(cell.y)
    }

}