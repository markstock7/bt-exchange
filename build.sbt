name := "bt-exchange"
version := "0.0.1"
organization := "org.bt"
scalaVersion := "2.12.4"
scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation")
publishMavenStyle := true

lazy val `exchange` = (project in file("."))
  .aggregate(
    `exchange-base`,
    `exchange-binance`,
  )

lazy val `exchange-base` = (project in file("exchange-base"))
  .settings(
    libraryDependencies ++= Seq(
      "com.squareup.retrofit2" % "retrofit" % "2.3.0"
    ) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
  )


lazy val `exchange-binance` = (project in file("exchange-binance"))
  .settings(
    libraryDependencies ++= Seq(
      "com.squareup.retrofit2" % "retrofit" % "2.3.0"
    ) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
  )
  .dependsOn(`exchange-base`)

