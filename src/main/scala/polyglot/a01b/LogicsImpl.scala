package polyglot.a01b

import polyglot.OptionToOptional
import util.Optionals.Optional as ScalaOptional
import util.Sequences.*
import Sequence.*

import scala.jdk.javaapi.OptionConverters
import scala.util.Random

trait Logicss:
  def hit(x: Int, y: Int): java.util.Optional[Integer]
  def won: Boolean

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01b/sol2/ */
class LogicsImpl(private val size: Int, private val mines: Int) extends Logicss:
  private val random = new Random()
  private val mineCells: Sequence[(Int, Int)] = defineMinesCell
  private var selectedCells: Sequence[(Int,Int)] = Nil()
  private def defineMinesCell: Sequence[(Int,Int)] =
    var minesCellTemplate: Sequence[(Int,Int)] = Nil()
    while minesCellTemplate.size != mines do
      val randCoordinate = (random.nextInt(size),random.nextInt(size))
      if !minesCellTemplate.contains(randCoordinate) then
        minesCellTemplate = Cons(randCoordinate, minesCellTemplate)
    println(minesCellTemplate)
    minesCellTemplate

  private def minesNear(x: Int, y: Int): Int = {
    (for {
      xx <- (x - 1) to (x + 1)
      yy <- (y - 1) to (y + 1)
      if mineCells.contains((xx, yy))
    } yield 1).size
  }

  def hit(x: Int, y: Int): java.util.Optional[Integer] = if mineCells.contains((x,y)) then
    OptionToOptional(ScalaOptional.Empty()) else
    selectedCells = Cons((x,y),selectedCells)
    OptionToOptional(ScalaOptional.Just(minesNear(x,y)))
  def won: Boolean = selectedCells.size == ((size*size)-mines)
