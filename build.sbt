name := """bcd-shop"""
organization := "com.niso1985"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "com.stripe" % "stripe-java" % "14.0.1"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.niso1985.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.niso1985.binders._"
