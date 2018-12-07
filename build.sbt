import Dependencies._
import BuildSettings._

lazy val root =(project in file("."))
  .settings(commonSettings: _*)
  .aggregate(
    `exchange-base`,
    `exchange-binance`,
    `exchange-okex`,
    `exchange-bitfinex`,
    `exchange-bibox`,
    `exchange-bittrex`
  )

lazy val `exchange-base`          = module("exchange-base", Seq())

lazy val `exchange-binance`       = module("exchange-binance", Seq(`exchange-base`))

lazy val `exchange-okex`          = module("exchange-okex", Seq(`exchange-base`))

lazy val `exchange-bitfinex`      = module("exchange-bitfinex", Seq(`exchange-base`))

lazy val `exchange-huobipro`      = module("exchange-huobipro", Seq(`exchange-base`))

lazy val `exchange-bibox`         = module("exchange-bibox", Seq(`exchange-base`))

lazy val `exchange-bittrex`         = module("exchange-bittrex", Seq(`exchange-base`))

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)

