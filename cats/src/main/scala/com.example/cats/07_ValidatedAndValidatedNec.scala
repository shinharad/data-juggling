package com.example.cats

import cats._
import cats.data._
import cats.implicits._

object `07_ValidatedAndValidatedNec` extends App {
  println("-" * 100)

  import Validated._

  println(Validated.Valid(1))
  println(Validated.Invalid("error"))

  println(Valid(1))
  println(Invalid("error"))

  println(1.valid[String])
  println("error".invalid[Int])

  println(1.validNec[String])
  println("error".invalidNec[Int])

  println(
    "error".pure[NonEmptyChain].raiseError[ValidatedNec[String, *], String]
  )

  println("-" * 100)
}
