package com.example.cats

import cats._
import cats.data._
import cats.implicits._

object `05_ApplicativeErrorForRaiseError` extends App {
  println("-" * 100)

  type EitherStringOr[+A] = Either[String, A]

  println("error".raiseError[EitherStringOr, String]) // Left(error)
  println("error".raiseError[Either[String, *], String])

  println(1.pure[Either[String, *]])
  println("error".raiseError[Either[String, *], String])

  println(1.asRight[String].map(_ + 1))
  println("error".asLeft[Int].leftMap(_.reverse))

  println(1.asRight[String].flatMap(one => "I don't care that it worked before ... it's an error now".asLeft[Int]))
  // Left(I don't care that it worked before ... it's an error now)
  println("error".asLeft[Int].leftFlatMap(error => 1337.asRight[String]))
  // Right(1337)
  println("error".asLeft[Int].leftFlatMap(error => 'M'.asRight[String]))
  // Right(M)

  println("-" * 100)

  println(Right(1).toOption)
  println(Left("error").toOption)

  println(Either.fromOption(Some(1), ifNone = "error"))
  println(Either.fromOption(None, ifNone = "error"))

  println(1.some.toRight(left = "error"))
  println(none.toRight(left = "error"))

  println("error".some.toLeft(right = 1))
  println(none.toLeft(right = 1))

  println(Either.catchOnly[ArithmeticException](1 / 0))
  println(Either.catchOnly[Throwable](1 / 0))
  println(Either.catchNonFatal(1 / 0))

  println("-" * 100)
}
