import sbt._
import Keys._
import Dependencies.btutil

object BuildSettings {
	val globalScalaVersion = "2.12.4"

	def buildSettings = Defaults.coreDefaultSettings ++ Seq(
		organization := "com.blocktrending",
		name         := "bt-exchange",
		version      := "1.0-SNAPSHOT",
		scalaVersion := globalScalaVersion,
		javacOptions += "-Xlint:unchecked",
		resolvers ++= Dependencies.Resolvers.commons
	)

	val compilerOptions = Seq(
		"-deprecation",
		"-unchecked",
		"-feature",
		"-language:_",
		"-Xfatal-warnings",
		"-Ywarn-dead-code",
//		"-Ywarn-unused-import",
//		"-Ywarn-unused",
		"-Xlint:missing-interpolator",
//		"-Ywarn-unused-import",
//		"-Ybackend:GenBCode",
		"-Ydelambdafy:method",
		"-target:jvm-1.8"
	)

	lazy val commonSettings = Seq(
		organization := "com.blocktrending",
		scalacOptions ++= compilerOptions
	)

	def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
	def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

	def module(name: String, deps: Seq[sbt.ClasspathDep[sbt.ProjectReference]] = Seq.empty) =
		Project(name, file(name)).dependsOn(deps: _*)
			.settings(commonSettings: _*)
			.settings(
			libraryDependencies ++= Seq(
				"com.squareup.retrofit2" % "retrofit" % "2.3.0",
				"com.typesafe.akka" %% "akka-actor" % "2.5.17",
				"com.typesafe.play" %% "play" % "2.6.19",
				"org.scalatest" %% "scalatest" % "3.0.5" % "test",
				"org.scalactic" %% "scalactic" % "3.0.5",
				btutil
			) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
		)

	val srcMain = Seq(
		scalaSource in Compile := (sourceDirectory in Compile).value,
		scalaSource in Test := (sourceDirectory in Test).value
	)
}