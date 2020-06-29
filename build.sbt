import Dependencies._

ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.0.1-SNAPSHOT"

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  // "-language:_",
  // "-unchecked",
  // "-Wunused:_",
  // "-Xfatal-warnings",
  "-Ymacro-annotations"
)

lazy val root =
  project.in(file("."))
    .aggregate(
      chimney,
      monocle
    )

lazy val commonSettings = Seq(
  addCompilerPlugin(org.augustjune.`context-applied`),
  addCompilerPlugin(org.typelevel.`kind-projector`),
  Compile / console / scalacOptions --= Seq(
    "-Wunused:_",
    "-Xfatal-warnings"
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
  libraryDependencies ++= Seq(
    com.github.alexarchambault.`scalacheck-shapeless_1.14`,
    org.scalacheck.scalacheck,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-14`,
    org.typelevel.`discipline-scalatest`
  ).map(_ % Test)
)

lazy val chimney =
  project
    .in(file("chimney"))
    .settings(name := "chimney")
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        org.typelevel.`cats-core`,
        `io.scalaland`.chimney
      )
    )

lazy val monocle =
  project
    .in(file("monocle"))
    .settings(name := "monocle")
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        org.typelevel.`cats-core`,
        `io.scalaland`.chimney,
        org.typelevel.kittens,
        com.github.`julien-truffaut`.`monocle-macro`,
        com.github.`julien-truffaut`.`monocle-law`,
      )
    )
