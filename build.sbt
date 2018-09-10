import sbt.Keys.{initialCommands, parallelExecution}

organization in ThisBuild := "org.bt"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

lazy val `exchange` = (project in file("."))
  .aggregate(
    `exchange-base`,
    `exchange-binance`,
    // `exchange-bitfinex`
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

