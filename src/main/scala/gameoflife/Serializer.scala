package gameoflife

import gameoflife.GameOfLife.Universe
import org.scalajs.dom
import dom.document
import org.scalajs.dom.html.Canvas

import scala.util.Random

object Serializer {

  def main(args: Array[String]): Unit = {
    val canvas = init()

    var varU = randomUniverse

    dom.window.setInterval(
      () => {
        val u = UniverseImpl.next(varU, 100, 100)
        loop(canvas, varU, 100, 100)

        if(stationary(u, varU, 20, 20)) {
          varU = randomUniverse
        } else {
          varU = u
        }
      },
      1000
    )
  }

  private def stationary(universe1: Universe, universe2: Universe, rows: Int, columns: Int): Boolean = {
    for {
      r <- 0 until rows
      c <- 0 until columns
    } yield {
      Cell(r, c)
    }
  } forall { cell: Cell => universe1(cell) == universe2(cell) }

  private def randomUniverse: Universe = {
    val allUniverses = InitUniverse.all

    val random = new Random().nextInt(allUniverses.length)

    UniverseImpl.from(
      InitUniverse.all(random))
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
