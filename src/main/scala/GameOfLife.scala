/**
 * The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in
 * one of two possible states, alive or dead, or "populated" or "unpopulated". Every cell interacts with its eight
 * neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the
 * following transitions occur:
 *
 *  Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
 *  Any live cell with two or three live neighbours lives on to the next generation.
 *  Any live cell with more than three live neighbours dies, as if by overpopulation.
 *  Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */

object GameOfLife {
  def neighbors(universe: Universe, cell: Cell): Int = List(
    universe(Cell(cell.x - 1, cell.y - 1)),
    universe(Cell(cell.x - 1, cell.y)),
    universe(Cell(cell.x - 1, cell.y + 1)),
    universe(Cell(cell.x, cell.y - 1)),
    universe(Cell(cell.x, cell.y + 1)),
    universe(Cell(cell.x + 1, cell.y - 1)),
    universe(Cell(cell.x + 1, cell.y)),
    universe(Cell(cell.x + 1, cell.y + 1))
  ).count(_ == Alive)

  type Universe = Cell => Liveness

  sealed trait Liveness
  object Alive extends Liveness
  object Dead extends Liveness

  def next(u: Universe): Universe =
    (cell: Cell) => cell match {
      case Cell(1, 2) => Dead
      case _ => u(cell)
  }

}

case class Cell(x: Int, y: Int)