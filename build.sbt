organization := "com.typesafe.training"

name := "scala-train"

version := "3.0.0"

scalaVersion := Version.scala

libraryDependencies ++= Dependencies.scalaTrain

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-language:_",
  "-target:jvm-1.6",
  "-encoding", "UTF-8"
)

retrieveManaged := true

parallelExecution in Test := false

initialCommands in console := "import com.typesafe.training.scalatrain._,misc._"

initialCommands in (Test, console) := (initialCommands in console).value + ",TestData._"
