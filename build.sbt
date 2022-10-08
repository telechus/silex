
val scala212Version = "2.12.15"
val scala213Version = "2.13.10"
val scala3Version = "3.2.0"

val commonSettings = Seq(
  scalaVersion := scala3Version,
  crossScalaVersions := Seq(scala212Version, scala213Version, scala3Version),
  organization := "dev.procgen",
  organizationName := "ProcGen",
  organizationHomepage := Some(url("https://projects.procgen.dev/")),

  scmInfo := Some(
    ScmInfo(
      url("https://github.com/telechus/silex"),
      "scm:git@github.com:telechus/silex.git"
    )
  ),

  developers := List(
    Developer(
      id = "telechus",
      name = "Brian Hoff",
      email = "telechus@procgen.dev",
      url = url("https://github.com/telechus")
    )
  ),

  description := "A fork of EPFL's silex, which is a Scala library for writing lexers.",

)

lazy val silex = project
  .in(file("."))
  .settings(
    commonSettings,
    name := "silex",

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
      "org.scalatest" %% "scalatest" % "3.2.14" % Test,
    ),

    licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0")),

    versionScheme := Some(VersionScheme.EarlySemVer),

    publishTo := {
      val nexus = "https://s01.oss.sonatype.org/"
      if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
      /*
      if (isSnapshot.value)
        Some(Resolver.file("local-ivy", file(Path.userHome.absolutePath + "/.ivy2/local/snapshots"))(Resolver.ivyStylePatterns))
      else
        Some(Resolver.file("local-ivy", file(Path.userHome.absolutePath + "/.ivy2/local/releases"))(Resolver.ivyStylePatterns))
       */
    },

    ThisBuild / publishMavenStyle := true,

    releasePublishArtifactsAction := PgpKeys.publishLocalSigned.value,

    usePgpKeyHex("D4CCC34729CBF6A632863E506CB956CFB2863F7A"),
  )


