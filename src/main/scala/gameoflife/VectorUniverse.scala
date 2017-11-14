package gameoflife

import gameoflife.GameOfLife.Universe

import scala.util.Try

case class VectorUniverse(universe: Vector[Vector[Liveness]], rows: Int, columns: Int) extends Universe {

  override def apply(cell: Cell): Liveness = Try(universe(cell.x)(cell.y)).getOrElse(Dead)
}

object VectorUniverse {

  def from(universe: String): VectorUniverse = {
    val board = RleParser(universe)
    val rows = board.size * 2
    val columns = board.max(Ordering.by[(Vector[Liveness]), Int](_.size)).size * 2
    VectorUniverse(board, rows, columns)
  }

  def next(universe: VectorUniverse): VectorUniverse = {
    val nextU = GameOfLife.next(universe)
    val u: Vector[Vector[Liveness]] = (0 until universe.rows).toVector.map(
      r => (0 until universe.columns).toVector.map(c => nextU(Cell(r, c)))
    )

    universe.copy(universe = u)
  }

  def toString(universe: VectorUniverse): String = {
    (0 until universe.rows).map { r =>
      (0 until universe.columns).map { c =>
        universe(Cell(r, c)) match {
          case Alive => "o"
          case Dead => " "
        }
      }.mkString
    }.mkString(",\n")
  }
}