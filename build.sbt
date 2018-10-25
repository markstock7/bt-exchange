import Dependencies._
import BuildSettings._

lazy val root =(project in file("."))
    .aggregate(
      `bt-exchange-base`,
      `bt-exchange-binance`,
    )

lazy val `bt-exchange-base` = module("bt-exchange-base", Seq())

lazy val `bt-exchange-binance` = module("bt-exchange-binance", Seq(`bt-exchange-base`))

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)

