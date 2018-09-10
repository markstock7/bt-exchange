name := "bt-exchange"
organization := "com.blocktrending"
scalaVersion := "2.12.4"
scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation")

lazy val commonSettings = Seq(
  organization := "com.blocktrending",
  scalacOptions ++= compilerOptions
)

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val root =(project in file("."))
    .settings(commonSettings)
    .aggregate(
      `bt-exchange-base`,
      `bt-exchange-binance`,
    )

lazy val `bt-exchange-base` = (project in file("bt-exchange-base"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "com.squareup.retrofit2" % "retrofit" % "2.3.0"
    ) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
  )

lazy val `bt-exchange-binance` = (project in file("bt-exchange-binance"))
  .settings(commonSettings: _*)
  .settings(
    assemblySettings,
    libraryDependencies ++= Seq(
      "com.squareup.retrofit2" % "retrofit" % "2.3.0"
    ) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
  )
  .dependsOn(`bt-exchange-base`)


lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)

