package misc

import collection.immutable.Seq
import java.util.NoSuchElementException

object Queue {

  def apply[A](as: Seq[A]): Queue[A] = new Queue(as)

  def apply[A](as: A*): Queue[A] = new Queue(Seq(as: _*))

}

class Queue[+A] private (private val as: Seq[A]) {

  def dequeue: (A, Queue[A]) = as match {
    case Seq() => throw new NoSuchElementException
    case _     => (as.head, Queue(as.tail))
  }

  def enqueue[B >: A](a: B): Queue[B] = Queue(as :+ a)

  override def toString: String = s"Queue(${as.mkString(", ")})"

  override def equals(p1: Any): Boolean = p1 match {
    case q: misc.Queue[A] => (q eq this) || (as == q.as)
    case _                => false
  }

  override def hashCode(): Int = as.hashCode

}
