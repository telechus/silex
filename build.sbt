
val scala212Version = "2.12.15"
val scala213Version = "2.13.8"

val commonSettings = Seq(
  version            := "0.6.0",
  scalaVersion       := scala213Version,
  crossScalaVersions := Seq(scala212Version, scala213Version),
  organization       := "dev.procgen",
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
      "-doc-source-url", "https://raw.githubusercontent.com/telechus/silex/master€{FILE_PATH}.scala",
      "-doc-root-content", baseDirectory.value + "/project/root-doc.txt"
    ),

    Compile / doc / target := baseDirectory.value / "docs",

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.12" % Test,
    ),

    licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0")),

    versionScheme := Some(VersionScheme.EarlySemVer),

    publishTo := {
      if (isSnapshot.value)
        Some(Resolver.file("local-ivy", file(Path.userHome.absolutePath + "/.ivy2/local/snapshots")))
      else
        Some(Resolver.file("local-ivy", file(Path.userHome.absolutePath + "/.ivy2/local/releases")))
    },

    releasePublishArtifactsAction := PgpKeys.publishLocalSigned.value,
    usePgpKeyHex("D4CCC34729CBF6A632863E506CB956CFB2863F7A"),
  )


