package gameoflife

import gameoflife.GameOfLife.Universe

import scala.util.Try

case class VectorUniverse(universe: Vector[Vector[Liveness]], rows: Int, columns: Int) extends Universe {

  override def apply(cell: Cell): Liveness = Try(universe(cell.x)(cell.y)).getOrElse(Dead)

  def next: VectorUniverse = {
    val nextU = GameOfLife.next(this)
    val u: Vector[Vector[Liveness]] = Vector.range(0, rows).map(
      r => Vector.range(0, columns).map(c => nextU(Cell(r, c)))
    )

    this.copy(universe = u)
  }
}

object VectorUniverse {

  def from(universe: String): VectorUniverse = {
    val board = RleParser(universe)
    val rows = board.size
    val columns = board.max(Ordering.by[(Vector[Liveness]), Int](_.size)).size
    VectorUniverse(board, rows, columns)
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