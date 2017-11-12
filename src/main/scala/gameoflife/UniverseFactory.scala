package gameoflife

import java.io.File

import gameoflife.GameOfLife.Universe

import scala.util.parsing.combinator.RegexParsers

object UniverseFactory {
  def from(universe: String): Universe = ???
  def from(file: File): Universe = ???
  def fromCompressed(universe: String): Universe = ???
  def fromCompressed(file: File): Universe = ???
  def next(universe: Universe): Universe = ???
}

object RLEParser extends RegexParsers {
  def factor: Parser[Int] = """\d*""".r ^^ { token =>
    if(token.nonEmpty) {
      token.toInt
    } else {
      1
    }
  }
  def state: Parser[Liveness] = "[ob]".r ^^ {
    case "o" => Dead
    case _ => Alive
  }

  def stateTimes: Parser[Vector[Liveness]] = factor ~ state ^^ { case factor ~ state => Vector.fill(factor)(state) }

  def line: Parser[Vector[Liveness]] = rep(stateTimes) ~ "[$!]".r ^^ { case line ~ _ => line.toVector.flatten }

  def grid: Parser[Vector[Vector[Liveness]]] = rep(line) ^^ { case lines => lines.toVector }

  def apply(input: String): Vector[Vector[Liveness]] = parseAll(grid, input) match {
    case Success(result, _) => result
    case failure : NoSuccess => scala.sys.error(failure.msg)
  }

}