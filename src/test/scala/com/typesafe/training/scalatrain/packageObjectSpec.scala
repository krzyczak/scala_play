package com.typesafe.training.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class packageObjectSpec extends WordSpec with Matchers {

  "isIncreasing" should {
    "return true" in {
      //      isIncreasing(List(1, 2, 3)) shouldBe (true)
      isIncreasing(Vector(Time(8, 0), Time(10, 0), Time(12, 0))) shouldBe (true)
    }
    //
    "return false" in {
      //          isIncreasing(List(4, 2, 3)) shouldBe (false)
      isIncreasing(List(Time(16, 0), Time(10, 0), Time(12, 0))) shouldBe (false)
    }
  }

}
