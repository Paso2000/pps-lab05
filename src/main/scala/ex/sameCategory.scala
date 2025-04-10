package ex
import util.Sequences.*
import util.Sequences.Sequence.*

object sameCategory:
  def unapply(seq:Sequence[(String,String)]): Option[String] = seq match
    case Nil() => None
    case Cons((_, category), tail) =>
        Some(category).filter(_ => tail.forall((_, c) => c == category))


@main def examplesUnapply(): Unit =

  val courses = Sequence(("course1","italian"), ("course2","math"), ("course3","math"))
   courses match
    case sameCategory(cat) => println (s" $courses have same category $cat")
    case _ => println (s" $courses have different categories")

