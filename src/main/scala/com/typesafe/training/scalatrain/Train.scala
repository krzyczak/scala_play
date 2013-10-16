/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.collection.immutable.Seq

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.size >= 2, "schedule must contain at least two elements")
  // TODO Verify that `schedule` is strictly increasing in time

  val stations: Seq[Station] = schedule.map(_._2)

  val backToBackStations: Seq[(Station, Station)] = stations zip stations.tail

  val departureTimes: Map[Station, Time] = schedule.map(_.swap)(collection.breakOut)

}

object TrainInfo {

  case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo

  case class RegionalExpress(number: Int) extends TrainInfo

  case class BavarianRegional(number: Int) extends TrainInfo
}

sealed abstract class TrainInfo {

  def number: Int
}

case class Station(name: String) extends AnyVal

object Station {
  implicit def stringToStation(name: String) = Station(name)
}
