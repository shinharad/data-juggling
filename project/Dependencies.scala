import sbt._

object Dependencies {

  case object `io.scalaland` {
    val chimney =
      "io.scalaland" %% "chimney" % "0.3.4"
  }

  case object com {
    case object github {
      case object alexarchambault {
        val `scalacheck-shapeless_1.14` =
          "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.5"
      }
      case object `julien-truffaut` {
        def Dependency(artifact: String): ModuleID =
          "com.github.julien-truffaut" %% artifact % "2.0.0"

        val `monocle-core` = Dependency("monocle-core")
        val `monocle-macro` = Dependency("monocle-macro")

        val `monocle-law` = Dependency("monocle-law")

        val `monocle-generic` = Dependency("monocle-generic")
        val `monocle-refined` = Dependency("monocle-refined")
        val `monocle-state` = Dependency("monocle-state")
        val `monocle-unsafe` = Dependency("monocle-unsafe")
      }
    }

    case object softwaremill {
      val quicklens = "com.softwaremill.quicklens" %% "quicklens" % "1.4.12"
    }
  }

  case object org {
    case object augustjune {
      val `context-applied` =
        "org.augustjune" %% "context-applied" % "0.1.4"
    }

    case object scalacheck {
      val scalacheck =
        "org.scalacheck" %% "scalacheck" % "1.14.3"
    }

    case object scalatest {
      val scalatest =
        "org.scalatest" %% "scalatest" % "3.1.2"
    }

    case object scalatestplus {
      val `scalacheck-1-14` =
        "org.scalatestplus" %% "scalacheck-1-14" % "3.1.2.0"
    }

    case object typelevel {
      val `cats-core` =
        "org.typelevel" %% "cats-core" % "2.1.0"

      val `cats-effect` =
        "org.typelevel" %% "cats-effect" % "2.0.0"

      val `discipline-scalatest` =
        "org.typelevel" %% "discipline-scalatest" % "1.0.1"

      val `kind-projector` =
        "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full

      val kittens =
        "org.typelevel" %% "kittens" % "2.0.0"
    }

  }

}
