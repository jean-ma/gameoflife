package gameoflife

import org.scalajs.dom
import dom.document
import org.scalajs.dom.html.Canvas

import scala.util.Random

object HtmlAnimation {

  def loop(canvas: Canvas, universe: UniverseImpl): gameoflife.UniverseImpl = {
    val u = universe.next
    render(canvas, universe)

    if(stationary(u, universe)) {
      randomUniverse
    } else {
      u
    }
  }

  def main(args: Array[String]): Unit = {
    val canvas = init()

    var universe = randomUniverse

    dom.window.setInterval(
      () => {
        universe = loop(canvas, universe)
      },
      500
    )
  }

  private def stationary(u1: UniverseImpl, u2: UniverseImpl): Boolean = {
    for {
      r <- 0 until Math.max(u1.rows, u2.rows)
      c <- 0 until Math.max(u1.columns, u2.columns)
    } yield {
      Cell(r, c)
    }
  } forall { cell: Cell => u1(cell) == u2(cell) }

  private def randomUniverse: UniverseImpl = {
    val allUniverses = SampleUniverses.all

    val random = new Random().nextInt(allUniverses.length)

    UniverseImpl.from(
      SampleUniverses.all(random))
  }

  private def init(): Canvas = {
    val canvas = document.createElement("canvas").asInstanceOf[Canvas]
    document.body.style.backgroundColor = "black"
    canvas.setAttribute("id", "universe")
    canvas.width = (0.95 * dom.window.innerWidth).toInt
    canvas.height = (0.95 * dom.window.innerHeight).toInt

    dom.document.body.appendChild(canvas)

    canvas
  }

  private def render(canvas: Canvas, universe: UniverseImpl): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    ctx.fillStyle = "black"
    ctx.fillRect(0,0,canvas.width,canvas.height)
    ctx.fillStyle = "white"

    val cellLength = Math.min(canvas.width / universe.columns, canvas.height / universe.rows)
    val leftMargin = (canvas.width - cellLength * universe.columns) / 2
    val topMargin = (canvas.height - cellLength * universe.rows) / 2

    (0 until universe.rows).foreach { r =>
      (0 until universe.columns).foreach { c =>
        universe(Cell(r, c)) match {
          case Alive => ctx.fillRect(leftMargin + c * cellLength, topMargin + r * cellLength, cellLength, cellLength)
          case _ => Unit
        }
      }
    }
  }
}
