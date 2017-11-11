package gameoflife

import gameoflife.GameOfLife.Universe
import gameoflife.GameOfLife.next
import org.scalajs.dom
import dom.document
import org.scalajs.dom.html.Canvas

import scala.util.Try

object Serializer {
  val initUniverse: Vector[Vector[Liveness]] = {
    """
      |o     oo    oo     oo,
      |      o   ooo    ooo ,
      |  oo                 ,
      |  ooo                ,
      |                     ,
      |                     ,
      |                     ,
      |                     ,
      |                     ,
      |o  oo               o,
    """.stripMargin.split(",\n").map(_.toList.map {
      case 'o' => Alive
      case _ => Dead
    }.toVector).toVector
  }

  def main(args: Array[String]): Unit = {
    val universe: Universe = (cell: Cell) => {
      Try(initUniverse(cell.x)(cell.y)).getOrElse(Dead)
    }

    val height = initUniverse.length
    val width = initUniverse.headOption.getOrElse(Vector.empty).length

    println(toString(universe, height, width))

    toHtml(universe, height, width)
  }

  def toString(universe: Universe, rows: Int, columns: Int): String = {
    (0 until rows).map { r =>
      (0 until columns).map { c =>
        universe(Cell(r, c)) match {
          case Alive => "o"
          case Dead => " "
        }
      }.mkString
    }.mkString("\n")
  }

  def toHtml(universe: Universe, rows: Int, columns: Int): Unit = {
    val canvas = init()

    dom.window.setTimeout(
      () => {
        loop(canvas, universe, rows, columns)
      },
      1000)

    dom.window.setTimeout(
      () => {
        loop(canvas, next(universe), rows, columns)
      },
      2000)

    dom.window.setTimeout(
      () => {
        loop(canvas, next(next(universe)), rows, columns)
      },
      3000)
  }

  private def init(): Canvas = {
    val canvas = document.createElement("canvas").asInstanceOf[Canvas]
    document.body.style.backgroundColor = "black"
    canvas.setAttribute("id", "ui")
    canvas.width = (0.95 * dom.window.innerWidth).toInt
    canvas.height = (0.95 * dom.window.innerHeight).toInt

    dom.document.body.appendChild(canvas)

    println(s"${canvas.width} - ${canvas.height}")
    canvas
  }

  private def loop(canvas: Canvas, universe: Universe, rows: Int, columns: Int): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    ctx.fillStyle = "black"
    ctx.fillRect(0,0,canvas.width,canvas.height)
    ctx.fillStyle = "white"

    val cellLength = canvas.width / columns

    (0 until rows).foreach { r =>
      (0 until columns).foreach { c =>
        universe(Cell(r, c)) match {
          case Alive =>
            println(s"${r * cellLength}, ${c * cellLength}, $cellLength, $cellLength")
            ctx.fillRect(c * cellLength, r * cellLength, cellLength, cellLength)
          case _ => Unit
        }
      }
    }
  }
}
