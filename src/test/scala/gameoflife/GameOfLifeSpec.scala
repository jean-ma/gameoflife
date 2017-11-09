package gameoflife

import gameoflife.GameOfLife.Universe
import gameoflife.{Alive => ♡, Dead => ☠}
import org.scalatest.FlatSpec

import scala.util.Try

class GameOfLifeSpec extends FlatSpec {

  "An isolated cell" should "die" in {
    assert(GameOfLife.next(universe)(isolatedCell) === ☠)
  }

  "Neighbors" should "return the number of alive neighbors" in {
    assert(GameOfLife.countNeighbors(universe, isolatedCell) === 1)
  }

  "universe" should "give life to a dead cell with 3 Alive neighbors" in {
    assert(GameOfLife.next(universe)(deadToAliveCell) === ♡)
  }

  "universe" should "kill a living cell with 4 or more neighbors" in {
    assert(GameOfLife.next(universe)(aliveToDeadCell) === ☠)
  }

  "universe" should "keep alive a cell with 2 neighbors" in {
    assert(GameOfLife.next(universe)(keepAliveCell) === ♡)
  }

  "universe" should "give expected universe"in {
    val actual = GameOfLife.next(universe)

    assertEqual(expectedUniverse, actual, 5)
  }

  "universe" should "give expected universe of second generation"in {
    val actual = GameOfLife.next(GameOfLife.next(universe))

    assertEqual(expectedUniverse2Gen, actual, 5)
  }

  val universe: Universe =
    cell => {
      val space = Vector(
        Vector(♡, ♡, ☠, ☠, ☠),
        Vector(♡, ♡, ☠, ♡, ☠),
        Vector(♡, ☠, ☠, ☠, ♡),
        Vector(☠, ☠, ☠, ♡, ☠)
      )

      Try {
        space(cell.x)(cell.y)
      } getOrElse ☠
    }

  val expectedUniverse: Universe = {
    cell => {
      val space = Vector(
        Vector(♡, ♡, ♡, ☠, ☠),
        Vector(☠, ☠, ♡, ☠, ☠),
        Vector(♡, ♡, ♡, ♡, ♡),
        Vector(☠, ☠, ☠, ☠, ☠)
      )

      Try {
        space(cell.x)(cell.y)
      } getOrElse ☠
    }
  }

  val expectedUniverse2Gen: Universe = {
    cell => {
      val space = Vector(
        Vector(♡, ♡, ♡, ☠, ☠),
        Vector(☠, ☠, ☠, ☠, ☠),
        Vector(♡, ♡, ♡, ♡, ☠),
        Vector(☠, ♡, ♡, ♡, ☠)
      )

      Try {
        space(cell.x)(cell.y)
      } getOrElse ☠
    }
  }

  val isolatedCell = Cell(1, 3)

  val borderCell = Cell(2, 4)

  val deadToAliveCell = Cell(2, 3)

  val aliveToDeadCell = Cell(1, 1)

  val keepAliveCell = Cell(2, 0)

  private def assertEqual(expected: Universe, actual: Universe, size: Int) = {
    for {
      i <- 0 to size - 1
      j <- 0 to size - 1
    } yield {
      val cell = Cell(i, j)
      assert(
        expected(cell) === actual(cell),
        s"failed on $cell"
      )
    }
  }
}