import sbt.taskKey

name := "ConwaysGameOfLife"

version := "1.0"

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

enablePlugins(ScalaJSPlugin)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"

def copy(finder: PathFinder, dst: File) = {
  IO.copy(finder.get map {f => (f, dst / f.getName)})
}

val copyJsToDocs = taskKey[Unit]("copy js files")

lazy val copyJs = (project in file(".")).settings(
  (fastOptJS in Compile) := {
    copy((fastOptJS in Compile).value.data, new File("./docs"))
    (fastOptJS in Compile).value
  }
)