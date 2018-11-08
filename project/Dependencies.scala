import sbt._

object Dependencies {

	object Resolvers {
		val typesafe = "typesafe.com" at "http://repo.typesafe.com/typesafe/releases/"
		val sonatype = "sonatype" at "https://oss.sonatype.org/content/repositories/releases"
		val sonatypeS = "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
		val awesomepom = "awesomepom" at "https://raw.githubusercontent.com/jibs/maven-repo-scala/master"
		val lilaMaven = "lila-maven" at "https://raw.githubusercontent.com/ornicar/lila-maven/master"
		val prismic = "Prismic.io kits" at "https://s3.amazonaws.com/prismic-maven-kits/repository/maven/"
		val artifactory = "Artifactory" at "http://artifactory:8081/artifactory/sbt-release/"
		val artima = "Artima Maven Repository" at "http://repo.artima.com/releases/"

		val commons = Seq(
			Resolver.mavenLocal,
			artima,
			artifactory,
			"Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
			"Typesafe repo" at "http://repo.typesafe.com/typesafe/releases",
			"micronautics/scala on bintray" at "http://dl.bintray.com/micronautics/scala/",
			sonatypeS,
			lilaMaven,
			sonatype,
			awesomepom,
			typesafe,
			prismic
		)
	}


	val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
	val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test
	val scalaTestPlus = "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
	lazy val btutil = "com.blocktrending" %% "bt-util" % "0.0.18"
	lazy val circe = Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")

}