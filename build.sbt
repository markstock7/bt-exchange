name := "bt-exchange"
organization := "org.bt"
scalaVersion := "2.12.4"
scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation")

resolvers ++= Seq(
  "spray" at "http://repo.spray.io/",
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/NoonTechnology/bt-exchange</url>
    <licenses>
      <license>
        <name>MIT</name>
        <url>http://opensource.org/licenses/MIT</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:NoonTechnology/bt-exchange.git</url>
      <connection>scm:git@github.com:NoonTechnology/bt-exchange.git</connection>
    </scm>
    <developers>
      <developer>
        <id>markstock7</id>
        <name>Mark Stock</name>
        <url>https://github.com/markstock7</url>
      </developer>
    </developers>)


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

