
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

  "universe" should "kill a living cell with 4 or more neighbors" in {
    assert(GameOfLife.next(universe)(aliveToDeadCell) === D)
  }

  "universe" should "keep alive a cell with 2 neighbors" in {
    assert(GameOfLife.next(universe)(keepAliveCell) === A)
  }

  "universe" should "give expected universe"in {
    val actual = GameOfLife.next(universe)

    for {
      i <- 0 to 4
      j <- 0 to 3
    } yield {
      val cell = Cell(i, j)
      assert(
        expectedUniverse(cell) === actual(cell),
        s"failed on $cell"
      )
    }
  }

  "universe" should "give expected universe of second generation"in {
    val actual = GameOfLife.next(GameOfLife.next(universe))

    for {
      i <- 0 to 4
      j <- 0 to 3
    } yield {
      val cell = Cell(i, j)
      assert(
        expectedUniverse2Gen(cell) === actual(cell),
        s"failed on $cell"
      )
    }
  }

  val universe: Universe =
    cell => {
      val space = Vector(
        Vector(A, A, D, D, D),
        Vector(A, A, D, A, D),
        Vector(A, D, D, D, A),
        Vector(D, D, D, A, D)
      )

      Try {
        space(cell.x)(cell.y)
      } getOrElse D
    }

  val expectedUniverse: Universe = {
    cell => {
      val space = Vector(
        Vector(A, A, A, D, D),
        Vector(D, D, A, D, D),
        Vector(A, A, A, A, A),
        Vector(D, D, D, D, D)
      )

      Try {
        space(cell.x)(cell.y)
      } getOrElse D
    }
  }

  val expectedUniverse2Gen: Universe = {
    cell => {
      val space = Vector(
        Vector(A, A, A, D, D),
        Vector(D, D, D, D, D),
        Vector(A, A, A, A, A),
        Vector(D, A, A, A, A)
      )

      Try {
        space(cell.x)(cell.y)
      } getOrElse D
    }
  }

  val isolatedCell = Cell(1, 3)

  val borderCell = Cell(2, 4)

  val deadToAliveCell = Cell(2, 3)

  val aliveToDeadCell = Cell(1, 1)

  val keepAliveCell = Cell(2, 0)
}