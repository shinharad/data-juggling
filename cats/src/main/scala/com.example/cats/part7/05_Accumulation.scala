package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

object `05_Accumulation` extends App {
  println("-" * 100)

  import com.example.cats.MonadForOption._

  println(
    (
      1.some,
      2.some,
      3.some
    ).mapN { (one, two, three) =>
      one + two + three
    }
  )

  println(
    for {
      one <- 1.some
      two <- 2.some
      three <- 3.some
    } yield one + two + three
  )

  println(
    for {
      one <- "error 1".leftNec[Int]
      two <- "error 2".leftNec[Int]
      three <- "error 3".leftNec[Int]
    } yield one + two + three
  )
  // Left(Chain(error 1))

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
      "error 1".leftNec[Int],
      "error 2".leftNec[Int],
      "error 3".leftNec[Int]
    ) mapN { (one, two, three) =>
      one + two + three
    }
  )
  // Left(Chain(error 1))

  println("-" * 100)
}
