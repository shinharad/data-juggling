package com.example.cats.part6

import cats._
import cats.data._
import cats.implicits._

object `02_ApplicativeFunctorForPure` extends App {
  println("-" * 100)

  println(implicitly[Applicative[Option]].pure(1))
  println(Applicative[Option].pure(1))
  println(1.pure[Option])

  println(List(1, 2, 3))
  println(List(1))
  println(1.pure[List])

  println("-" * 100)
}