package gameoflife
/**
 * The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in
 * one of two possible states, alive or dead, or "populated" or "unpopulated". Every cell interacts with its eight
 * neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the
 * following transitions occur:
 *
 *  - Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
 *  - Any live cell with two or three live neighbours lives on to the next generation.
 *  - Any live cell with more than three live neighbours dies, as if by overpopulation.
 *  - Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */

object GameOfLife {

  type Universe = Cell => Liveness

  def countNeighbors(universe: Universe, cell: Cell): Int = {
      for {
        i <- -1 to 1
        j <- -1 to 1
        if i != 0 || j != 0
      } yield {
        Cell(cell.x + i, cell.y + j)
      }
    } count {
      cell => universe(cell) == Alive
    }

  def next(u: Universe): Universe =
    (cell: Cell) => countNeighbors(u, cell) match {
      case n if n < 2 => Dead
      case n if n == 2 => u(cell)
      case n if n == 3 => Alive
      case n if n > 3 => Dead
      case _ => Dead
    }

}

case class Cell(x: Int, y: Int)

sealed trait Liveness
object Alive extends Liveness
object Dead extends Liveness