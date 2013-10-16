/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

class JourneyPlanner(trains: Set[Train]) {

  val stations: Set[Station] =
    // Could also be expressed in short notation: trains flatMap (_.stations)
    trains flatMap (train => train.stations)

  def trainsAt(station: Station): Set[Train] =
    // Could also be expressed in short notation: trains filter (_.stations contains station)
    trains filter (train => train.stations contains station)

  def stopsAt(station: Station): Set[(Time, Train)] =
    for {
      train <- trains
      (time, s) <- train.schedule if s == station
    } yield (time, train)

  def isShortTrip(from: Station, to: Station): Boolean =
    trains exists (train =>
      train.stations dropWhile (station => station != from) match {
        case `from` +: `to` +: _      => true
        case `from` +: _ +: `to` +: _ => true
        case _                        => false
      }
    )

  val hops: Map[Station, Set[Hop]] = {
    val allHops = for {
      t <- trains
      (from, to) <- t.backToBackStations
    } yield {
      Hop(from, to, t)
    }
    allHops.groupBy(_.from)
  }

  // TODO implement this using streams
  def connections(from: Station, to: Station, departureTime: Time): Set[Seq[Hop]] = {
    require(from != to)

    def trConnections(soFar: Vector[Hop]): Set[Seq[Hop]] = {
      val last = soFar.last
      if (last.to == to) {
        Set(soFar)
      } else {
        val visited = soFar.head.from +: soFar.map(_.to)
        val allHops = hops.getOrElse(last.to, Set())
        val possibleHops = allHops filter {
          hop =>
            hop.departureTime <= last.arrivalTime && !visited.contains(hop.to)
        }
        possibleHops flatMap {
          hop => trConnections(soFar :+ hop)
        }
      }
    }

    val allHops = hops.getOrElse(from, Set())
    val possibleHops = allHops filter {
      _.departureTime >= departureTime
    }

    possibleHops flatMap {
      hop => trConnections(Vector(hop))
    }
  }
}
