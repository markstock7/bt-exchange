organization in ThisBuild := "org.bt"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `exchange` = (project in file("."))
  .aggregate(
    `exchange-base`,
    `exchange-binance`,
    // `exchange-bitfinex`
  )
//
//lazy val `exchange-api` = (project in file("exchange-api"))
//  .settings(
//    libraryDependencies ++= Seq(
//      lagomScaladslApi
//    )
//  )
//
//lazy val `exchange-impl` = (project in file("exchange-impl"))
//  .enablePlugins(LagomScala)
//  .settings(
//    libraryDependencies ++= Seq(
//      lagomScaladslPersistenceCassandra,
//      lagomScaladslKafkaBroker,
//      lagomScaladslTestKit,
//      macwire,
//      scalaTest
//    )
//  )
//  .settings(lagomForkedTestSettings: _*)
//  .dependsOn(
//    `exchange-api`,
//    `exchange-binance`,
//    // `exchange-bitfinex`,
//    // `exchange-huobipro`,
//    // `exchange-okex`
//  )
//
//lazy val `exchange-stream-api` = (project in file("exchange-stream-api"))
//  .settings(
//    libraryDependencies ++= Seq(
//      lagomScaladslApi
//    )
//  )
//
//lazy val `exchange-stream-impl` = (project in file("exchange-stream-impl"))
//  .enablePlugins(LagomScala)
//  .settings(
//    libraryDependencies ++= Seq(
//      lagomScaladslTestKit,
//      macwire,
//      scalaTest
//    )
//  )
//  .dependsOn(`exchange-stream-api`, `exchange-api`)
//

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

// lazy val `exchange-bitfinex` = (project in file("exchange-bitfinex"))
//   .settings(
//     libraryDependencies ++= Seq(
//       "com.squareup.retrofit2" % "retrofit" % "2.3.0"
//     ) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
//   )
//   .dependsOn(`exchange-base`)

// lazy val `exchange-huobipro` = (project in file("exchange-huobipro"))
//   .settings(
//     libraryDependencies ++= Seq(
//       "com.squareup.retrofit2" % "retrofit" % "2.3.0"
//     ) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
//   )
//   .dependsOn(`exchange-base`)

// lazy val `exchange-okex` = (project in file("exchange-okex"))
//   .settings(
//     libraryDependencies ++= Seq(
//       "com.squareup.retrofit2" % "retrofit" % "2.3.0"
//     ) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
//   )
//   .dependsOn(`exchange-base`)
