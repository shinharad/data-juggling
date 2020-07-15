package com.example.cats.part7

import cats._
import cats.data._
import cats.data.Validated._
import cats.implicits._

object `07_SemigroupForCombine` extends App {
  println("-" * 100)

  import com.example.cats.MonadForOption._

  println(
    (
      "error 1".invalidNec[Int],
      "error 2".invalidNec[Int],
      "error 3".invalidNec[Int]
    ) mapN { (one, two, three) =>
      one + two + three
    }
  )
  // Invalid(Chain(error 1, error 2, error 3))

  println(
    (
      "error 1".invalid[Int],
      "error 2".invalid[Int],
      "error 3".invalid[Int]
    ) mapN { (one, two, three) =>
      one + two + three
    }
  )
  // Invalid(error 1error 2error 3)

  case object Whatever

  implicit val horribleButCompiles: Semigroup[Whatever.type] =
    (w1, w2) => w1

  println(
    (
      Whatever.invalid[Int],
      Whatever.invalid[Int],
      Whatever.invalid[Int]
    ) mapN { (one, two, three) =>
      one + two + three
    }
  )
  // Invalid(Whatever)

  println("-" * 100)
}
