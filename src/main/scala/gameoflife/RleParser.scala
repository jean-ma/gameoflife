package gameoflife

import scala.util.parsing.combinator.RegexParsers

object RleParser extends RegexParsers {
  case class Size(x: Int, y: Int)

  def apply(input: String): Vector[Vector[Liveness]] = parseAll(rleContent, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => scala.sys.error(failure.msg)
  }

  def complete(lines: List[List[Liveness]], size: Size): List[List[Liveness]] = {
    lines.map {
      line => line ::: List.fill(size.x - line.size)(Dead)
    } ::: {
      List.fill(size.y - lines.size)(List.fill(size.x)(Dead))
    }
  }

  private def rleContent: Parser[Vector[Vector[Liveness]]] = (comments ~ size ~ grid) ^^ {
    case _ ~ size ~ grid => complete(grid, size).map(_.toVector).toVector
  }

  private def size: Parser[Size] = " *x *= *".r ~ number ~ ", *y *= *".r ~ number ^^ {
    case _ ~ x ~ _ ~ y => Size(x, y)
  }

  private def number: Parser[Int] = """\d+""".r ^^ { token => token.toInt }

  private def comments: Parser[_] = rep("#.*".r)

  private def grid: Parser[List[List[Liveness]]] = rep(line ~ newLines) ^^ {
    lines => lines.flatMap {
      case line ~ newLines => line +: newLines
    }
  }

  private def line: Parser[List[Liveness]] = rep(factorState) ^^ { line => line.flatten }

  private def newLines: Parser[List[List[Liveness]]] = factor ~ "[$!]".r ^^ {
    case factor ~ _ if factor == 1 => List.empty
    case factor ~ _ => List.fill(factor - 1)(List.empty)
  }

  private def factorState: Parser[Vector[Liveness]] = factor ~ state ^^ {
    case factor ~ state => Vector.fill(factor)(state)
  }

  private def factor: Parser[Int] =
    """\d*""".r ^^ { token =>
      if (token.nonEmpty) {
        token.toInt
      } else {
        1
      }
    }

  private def state: Parser[Liveness] = "[ob]".r ^^ {
    case "o" => Alive
    case _ => Dead
  }
}
