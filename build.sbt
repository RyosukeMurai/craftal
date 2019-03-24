name := """craftal-project"""

lazy val commonSettings = Seq(
  organization := "com.craftal",
  version := "1.0-SNAPSHOT",
  libraryDependencies ++= Seq(
    specs2 % Test
  ),
  libraryDependencies += guice,
  libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  libraryDependencies += "com.h2database" % "h2" % "1.4.197",
  libraryDependencies ++= Seq(
    "joda-time" % "joda-time" % "2.10.1",
    "org.joda" % "joda-convert" % "2.2.0"
  ),
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-slick" % "3.0.1",
    "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1",
    "com.typesafe.slick" %% "slick-codegen" % "3.2.1",
    "mysql" % "mysql-connector-java" % "5.1.34"
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .enablePlugins(PlayScala)
  .dependsOn(common, core, identityaccess)
  .aggregate(common, core, identityaccess)

lazy val common = (project in file("modules/common"))
  .settings(commonSettings)
  .enablePlugins(PlayScala)

lazy val core = (project in file("modules/core"))
  .settings(commonSettings)
  .enablePlugins(PlayScala)
  .dependsOn(common)

lazy val identityaccess = (project in file("modules/identityaccess"))
  .settings(commonSettings)
  .enablePlugins(PlayScala)
  .dependsOn(common)

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += Resolver.jcenterRepo

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.6")

libraryDependencies ++= Seq(
  "be.objectify" %% "deadbolt-scala" % "2.6.0",
  "com.mohiva" %% "play-silhouette" % "5.0.5",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "5.0.5",
  "com.mohiva" %% "play-silhouette-persistence" % "5.0.5",
  "com.mohiva" %% "play-silhouette-crypto-jca" % "5.0.5",
  "org.webjars" %% "webjars-play" % "2.6.3",
  "org.webjars" % "bootstrap" % "4.1.3" exclude("org.webjars", "jquery"),
  "org.webjars" % "jquery" % "3.2.1",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "com.iheart" %% "ficus" % "1.4.3",
  "com.typesafe.play" %% "play-mailer" % "6.0.1",
  "com.typesafe.play" %% "play-mailer-guice" % "6.0.1",
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.1-akka-2.5.x",
  "com.adrianhurt" %% "play-bootstrap" % "1.4-P26-B3-SNAPSHOT",
  "com.mohiva" %% "play-silhouette-testkit" % "5.0.5" % "test",
  ehcache,
  filters
)