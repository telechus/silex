
val scala212Version = "2.12.15"
val scala213Version = "2.13.8"

val commonSettings = Seq(
  version            := "0.6",
  scalaVersion       := scala213Version,
  crossScalaVersions := Seq(scala212Version, scala213Version),
  organization       := "ch.epfl.lara",
)

lazy val silex = project
  .in(file("."))
  .settings(
    commonSettings,
    name               := "silex",

    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked"
    ),

    Compile / doc / scalacOptions ++= Seq(
      "-groups",
      "-sourcepath", baseDirectory.value.getAbsolutePath,
      "-doc-source-url", "https://raw.githubusercontent.com/epfl-lara/silex/master€{FILE_PATH}.scala",
      "-doc-root-content", baseDirectory.value + "/project/root-doc.txt"
    ),

    Compile / doc / target := baseDirectory.value / "docs",

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.9" % Test,
    ),

    licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0")),

  )


