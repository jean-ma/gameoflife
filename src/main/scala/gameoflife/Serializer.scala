package gameoflife

import gameoflife.GameOfLife.Universe
import gameoflife.GameOfLife.next
import org.scalajs.dom
import dom.document
import org.scalajs.dom.html.Canvas

import scala.util.Try

object Serializer {
  val initUniverse = """
      |o     oo    oo     oo               ,
      |      o   ooo    ooo                ,
      |  oo                                ,
      |  ooo                               ,
      |                                    ,
      |                                    ,
      |                                    ,
      |                       oo ooo       ,
      |                       oooo         ,
      |o  oo               o               ,
      |oo ooo                              ,
      |  oooo                              ,
      |                                    ,
      |                                    ,
    """.stripMargin

  val (rows, columns) = getSize(initUniverse)

  def getSize(universe: String) = {
    val u = universe.split(",\n").map(_.toList)
    (u.length, u.headOption.getOrElse(List.empty).length)
  }

  def main(args: Array[String]): Unit = {

    val universe = fromString(initUniverse)

    toHtml(universe)
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

  def fromString(universe: String): Universe = {
    (cell: Cell) =>
      Try {
        universe.split(",\n").map(_.toList.map {
          case 'o' => Alive
          case _ => Dead
        }.toVector).toVector(cell.x)(cell.y)
      }.getOrElse(Dead)
  }

  def toHtml(universe: Universe): Unit = {
    val canvas = init()

    var varU = toString(universe, rows, columns)

    dom.window.setInterval(
      () => {
        val u = fromString(varU)
        loop(canvas, u, rows, columns)
        val previous = varU
        varU = toString(next(u), rows, columns)

        if(stationary(previous, varU)) {
          varU = toString(universe, rows, columns)
        }
      },
      1000
    )
  }

  private def stationary(universe1: String, universe2: String): Boolean = universe1 == universe2

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
