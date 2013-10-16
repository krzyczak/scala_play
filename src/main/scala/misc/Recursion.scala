package misc

/**
 * Created with IntelliJ IDEA.
 * User: marcinkubala
 * Date: 13-10-16
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */

import scala.collection.immutable.Seq
import scala.annotation.tailrec

object Recursion {

  def map[A, B](as: Seq[A], f: A => B): Seq[B] = {

    as.foldLeft(Seq[B]())((acc, rightElem) => {
      f(rightElem) +: acc
    })

    //    @tailrec
    //    def trMap(as: Seq[A], acc: Seq[B]): Seq[B] = {
    //      as match {
    //        case head :: tail => trMap(tail, f(head) +: acc)
    //        case _            => acc
    //      }
    //    }
    //
    //    trMap(as, Vector())
  }

  def flatMap[A, B](as: Seq[A], f: A => Seq[B]): Seq[B] = {
    as.foldLeft(Seq.empty[B])((acc, rightElem) => {
      f(rightElem) ++ acc
    })
    //    as match {
    //      case head +: tail => f(head) ++ flatMap(tail, f)
    //      case _            => Seq()
    //    }
  }
  //
  def filter[A](as: Seq[A], f: A => Boolean): Seq[A] = {
    as.foldLeft(Seq[A]())((acc, rightElem) => {
      if (f(rightElem)) {
        rightElem +: acc
      } else {
        acc
      }
    })
    //    as match {
    //      case head +: tail => if (f(head)) head +: filter(tail) else filter(tail)
    //      case _            => Seq()
    //    }
  }

}
