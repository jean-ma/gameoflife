name := "craftmanship"

version := "1.0"

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

enablePlugins(ScalaJSPlugin)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"