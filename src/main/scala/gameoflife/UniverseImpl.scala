package gameoflife

import gameoflife.GameOfLife.Universe

case class UniverseImpl(universe: Array[Array[Liveness]], rows: Int, columns: Int) extends Universe {

  override def apply(cell: Cell): Liveness = {
    if(cell.x < 0 || cell.x >= rows || cell.y < 0 || cell.y >= columns) {
      Dead
    } else {
      universe(cell.x)(cell.y)
    }
  }

  def next: UniverseImpl = {
    val nextU = GameOfLife.next(this)
    val u = Array.ofDim[Liveness](rows, columns)
    for {
      r <- 0 until rows
      c <- 0 until columns
    } yield {
      u(r)(c) = nextU(Cell(r, c))
    }

    this.copy(universe = u)
  }
}

object UniverseImpl {

  def from(universe: String): UniverseImpl = {
    val board = RleParser(universe)
    val rows = board.size
    val columns = board.max(Ordering.by[(List[Liveness]), Int](_.size)).size
    UniverseImpl(board.map(_.toArray).toArray, rows, columns)
  }

  def toString(universe: UniverseImpl): String = {
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