package com.example.cats.part6

import cats._
import cats.data._
import cats.implicits._

object `06_EitherNec` extends App {
  println("-" * 100)

  val a: Either[NonEmptyChain[String], Int] = Left(NonEmptyChain("error1", "error2"))
  val b: EitherNec[String, Int] = Left(NonEmptyChain("error1", "error2"))
  println(a)
  println(b)

  println(Left(NonEmptyChain("error")))
  println(Left[NonEmptyChain[String], Int](NonEmptyChain("error")))
  println(NonEmptyChain("error").asLeft[Int])
  println("error".asLeft[Int].leftMap(NonEmptyChain.one))
  println("error".asLeft[Int].toEitherNec)
  println("error".leftNec[Int]) // winner
  println("error".pure[NonEmptyChain].raiseError[EitherNec[String, *], String])

  println(1.asRight[String])
  println(1.rightNec[String])

  println("-" * 100)
}
