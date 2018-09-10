ThisBuild / organizationHomepage := Some(
  url("https://github.com/NoonTechnology/bt-exchange")
)

resolvers += "Artifactory" at "http://artifactory:8081/artifactory/sbt-release/"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/NoonTechnology/bt-exchange"),
    "scm:git@github.com:NoonTechnology/bt-exchange.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "markstock",
    name = "Mark Stock",
    email = "markstock7@hotmail.com",
    url = url("http://your.url")
  )
)

ThisBuild / description := "Some descripiton about your project."
ThisBuild / licenses := List(
  "Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("https://github.com/example/project"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ =>
  false
}

ThisBuild / publishTo := Some(
  "Artifactory Realm" at "http://artifactory:8081/artifactory/sbt-release")

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)

ThisBuild / publishMavenStyle := true
