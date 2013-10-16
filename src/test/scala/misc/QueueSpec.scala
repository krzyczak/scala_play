package misc

import org.scalatest.{ Matchers, WordSpec }

import collection.immutable.Seq

class QueueSpec extends WordSpec with Matchers {

  "Call toString" should {
    "return Queue()" in {
      misc.Queue(Seq.empty[String]).toString shouldBe ("Queue()")
    }

    "return Queue(1, 2, 3)" in {
      misc.Queue(Vector(1, 2, 3)).toString shouldBe ("Queue(1, 2, 3)")
    }
  }

  "Call enqueue" should {
    "append element at the end of queue" in {
      misc.Queue(1).enqueue(2) shouldBe (misc.Queue(1, 2))
    }

    "append differend type element at the end of queue" in {
      misc.Queue(1).enqueue("test") shouldBe (misc.Queue(1, "test"))
    }
  }

}
