package gameoflife

import gameoflife.GameOfLife.Universe

import scala.util.Try

case class UniverseImpl(universe: Vector[Vector[Liveness]], rows: Int, columns: Int) extends Universe {

  override def apply(cell: Cell): Liveness = Try(universe(cell.x)(cell.y)).getOrElse(Dead)
}

object UniverseImpl {

  def from(universe: String): Universe = {
    val board = RleParser(universe)
    val rows = board.size * 2
    val columns = board.max(Ordering.by[(Vector[Liveness]), Int](_.size)).size * 2
    UniverseImpl(board, rows, columns)
  }

  def next(universe: Universe, rows: Int, columns: Int): Universe = {
    val nextU = GameOfLife.next(universe)
    val u: Vector[Vector[Liveness]] = (0 until rows).toVector.map(
      r => (0 until columns).toVector.map(c => nextU(Cell(r, c)))
    )

    UniverseImpl(u, rows, columns)
  }

  def toString(universe: Universe, rows: Int, columns: Int): String = {
    (0 until rows).map { r =>
      (0 until columns).map { c =>
        universe(Cell(r, c)) match {
          case Alive => "o"
          case Dead => " "
        }
      }.mkString
    }.mkString(",\n")
  }
}