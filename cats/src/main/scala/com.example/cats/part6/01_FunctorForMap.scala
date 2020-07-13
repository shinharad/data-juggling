package com.example.cats.part6

import cats._
import cats.data._
import cats.implicits._

object `01_FunctorForMap` extends App {
  println("-" * 100)

  println(Some(1))
  println(None)

  println(1.some)
  println(none)
  println(none[Int])

  println(Some(null))
  println((null: String).some)

  println(Option(null))

  // @see FPLibrary.scala

  println("-" * 100)
}
