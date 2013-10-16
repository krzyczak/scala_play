package com.typesafe.training.scalatrain

case class Hop(from: Station, to: Station, train: Train) {
  require(from != to)
  require(train.backToBackStations.contains((from, to)), s"$from -> $to must be backToBack station of $train")

  val departureTime: Time = train.departureTimes(from)
  val arrivalTime: Time = train.departureTimes(to)

}
