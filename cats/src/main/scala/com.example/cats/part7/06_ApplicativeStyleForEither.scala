package com.example.cats.part7

import cats._
import cats.data._
import cats.data.Validated._
import cats.implicits._

object `06_ApplicativeStyleForEither` extends App {
  println("-" * 100)

  import com.example.cats.MonadForOption._

  type ValidatedErrorsOr[+A] = ValidatedNec[String, A]
  type EitherErrorsOr[+A] = EitherNec[String, A]

  def product[A, B](fa: ValidatedErrorsOr[A], fb: ValidatedErrorsOr[B]): ValidatedErrorsOr[(A, B)] =
    (fa, fb) match {
      case (Valid(a), Valid(b))                 => Valid(a -> b)
      case (Invalid(errors), Valid(_))          => Invalid(errors)
      case (Valid(_), Invalid(errors))          => Invalid(errors)
      case (Invalid(errorsA), Invalid(errorsB)) => Invalid(errorsA ++ errorsB)
    }

  def product2[A, B](fa: EitherErrorsOr[A], fb: EitherErrorsOr[B]): EitherErrorsOr[(A, B)] =
    (fa, fb) match {
      case (Right(a), Right(b))     => Right(a -> b)
      case (Left(errors), Right(_)) => Left(errors)
      case (Right(_), Left(errors)) => Left(errors)
      case (Left(errorsA), _)       => Left(errorsA)
    }

  def flatMap[A, B](fa: EitherErrorsOr[A])(f: A => EitherErrorsOr[B]): EitherErrorsOr[B] =
    fa match {
      case Right(a)     => f(a)
      case Left(errors) => Left(errors)
    }

  println("-" * 100)
}
