package gameoflife

import scala.util.parsing.combinator.RegexParsers

object RleParser extends RegexParsers {
  def apply(input: String): Vector[Vector[Liveness]] = parseAll(rleContent, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => scala.sys.error(failure.msg)
  }

  private def rleContent: Parser[Vector[Vector[Liveness]]] = (comments ~ grid) ^^ {
    case _ ~ grid => grid
  }

  private def comments: Parser[_] = rep("#.*".r)

  private def grid: Parser[Vector[Vector[Liveness]]] = rep(line ~ newLines) ^^ {
    lines => lines.flatMap {
      case line ~ newLines => line +: newLines
    }.toVector
  }

  private def line: Parser[Vector[Liveness]] = rep(factorState) ^^ { line => line.toVector.flatten }

  private def newLines: Parser[Vector[Vector[Liveness]]] = factor ~ "[$!]".r ^^ {
    case factor ~ _ if factor == 1 => Vector.empty
    case factor ~ _ => Vector.fill(factor - 1)(Vector.empty)
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
