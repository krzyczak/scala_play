/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.{ Matchers, WordSpec }

class JourneyPlannerSpec extends WordSpec with Matchers {

  "stations" should {
    "be initialized correctly" in {
      planner.stations shouldEqual Set(munich, nuremberg, frankfurt, cologne, essen)
    }
  }

  "hops" should {
    "be initialized correctly" in {
      //      planner.hops shouldBe ()
    }
  }

  //  "Calling connections" should {
  //    "return empty connections" in {
  //      planner.connections(munich, cologne, Time(8, 50)) shouldBe (Set())
  //    }
  //
  //    "return the correct connections" in {
  //      planner.connections(munich, nuremberg, Time(8, 45)) shouldBe (Set())
  //    }
  //  }

  "Calling trainsAt" should {
    "return the correct trains" in {
      planner.trainsAt(munich) shouldEqual Set(ice724, ice726)
      planner.trainsAt(cologne) shouldEqual Set(ice724)
    }
  }

  "Calling trainsAt on String" should {
    "use implicit conversion" in {
      planner trainsAt "Munich" shouldEqual Set(ice724, ice726)

      Time.fromMinutes(("11:00": Time) minus "01:00") shouldEqual Time(10, 0)
    }
  }

  "Calling stopsAt" should {
    "return the correct stops" in {
      planner.stopsAt(munich) shouldEqual Set(ice724MunichTime -> ice724, ice726MunichTime -> ice726)
    }
  }

  "Calling isShortTrip" should {
    "return false for more than one station in between" in {
      planner.isShortTrip(munich, cologne) shouldBe false
      planner.isShortTrip(munich, essen) shouldBe false
    }
    "return true for zero or one stations in between" in {
      planner.isShortTrip(munich, nuremberg) shouldBe true
      planner.isShortTrip(munich, frankfurt) shouldBe true
      planner.isShortTrip(nuremberg, frankfurt) shouldBe true
      planner.isShortTrip(nuremberg, essen) shouldBe true
    }
  }
}
