package polyglot.a05b

import polyglot.a05b.Logics
import util.Sequences.*
import Sequence.*

import scala.util.Random

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a05b/sol2/ */
class LogicsImpl(private val size: Int) extends Logics:

  private val random: Random = scala . util . Random (42)
  private val initialPoint: (Int, Int) = initialPointAss()
  private var tickCount = 0


  private def initialPointAss() : (Int,Int) =
    (random.nextInt(size),random.nextInt(size))
  override def tick(): Unit = this.tickCount += 1
  override def isOver: Boolean = initialPoint._2 - tickCount < 0 || initialPoint._2 + tickCount >= this.size ||
    initialPoint._1 - tickCount < 0 || initialPoint._1 + tickCount >= this.size;

  override def hasElement(x: Int, y: Int): Boolean =
    (x == initialPoint._1 && Math.abs(y- initialPoint._2) <= tickCount) ||
    (y == initialPoint._2 && Math.abs(x - initialPoint._1) <= tickCount) ||
    (x - y == initialPoint._1 - initialPoint._2 && Math.abs(x - initialPoint._1) <= tickCount) ||
    (x + y == initialPoint._1 + initialPoint._2 && Math.abs(x - initialPoint._1) <= tickCount);
