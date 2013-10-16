/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.util.control.Exception
import scala.util.parsing.json.{ JSONObject => JsonObject }
import scala.annotation.tailrec

object Time {

  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)

  def fromJson(json: JsonObject): Option[Time] =
    for {
      hoursAny <- json.obj get "hours"
      hours <- Exception.allCatch opt hoursAny.toString.toInt
      minutesAny <- json.obj get "minutes"
      minutes <- Exception.allCatch opt minutesAny.toString.toInt
    } yield Time(hours, minutes)

  //  @tailrec def isIncreasing(as: Seq[Time]): Boolean = as match {
  //    case t1 +: t2 +: _ => (t1 < t2) && isIncreasing(_)
  //    case _ => true
  //  }

  def isIncreasingSliding(as: Seq[Time]): Boolean =
    as.sliding(2).forall({
      case (first: Time) +: (sec: Time) +: _ => first < sec
      case _                                 => true
    })

  implicit def stringToTime(t: String) = {
    val timePattern = """(\d{1,2}):(\d{1,2})""".r
    t match {
      case timePattern(hours, minutes) => Time(hours.toInt, minutes.toInt)
      case _                           => throw new Exception(s"Invalid time format: $t")
    }
  }
}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours < 24, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes < 60, "minutes must be within 0 and 59")

  val asMinutes: Int =
    hours * 60 + minutes

  override lazy val toString: String =
    f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)

  // TODO This "pollutes" the API; in the Advanced Scala course we learn a better solution
  def toJson: JsonObject =
    JsonObject(Map("hours" -> hours, "minutes" -> minutes))

  override def compare(that: Time): Int =
    this - that
}
