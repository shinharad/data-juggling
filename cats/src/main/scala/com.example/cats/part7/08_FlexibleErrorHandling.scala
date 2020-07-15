package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

trait FlexibleErrorHandling[Error, Mechanism[+_, +_]] {
  final type MultipleErrorsOr[+A] = Mechanism[NonEmptyChain[Error], A]

  final implicit class FixedApplicativeIdOps[A](private val a: A) {
    def good(implicit F: Applicative[MultipleErrorsOr]): MultipleErrorsOr[A] =
      F.pure(a)
  }

  final implicit protected class FixedApplicativeErrorIdOps(private val e: Error) {
    def bad[A](implicit F: ApplicativeError[MultipleErrorsOr, _ >: NonEmptyChain[Error]]): MultipleErrorsOr[A] =
      F.raiseError(e.pure[NonEmptyChain])
  }

}

object `08_FlexibleErrorHandling` extends App {
  println("-" * 100)

  type DependentErrorHandling = FlexibleErrorHandling[String, Either]
  type IndependentErrorHandling = FlexibleErrorHandling[String, Validated]

  final case class Person(
      firstName: String,
      lastName: String,
      age: Int
    )
  final case class SafePerson(
      firstName: String,
      lastName: String,
      age: Int
    )

  object BuisinessLogic extends IndependentErrorHandling {
  // object BuisinessLogic extends DependentErrorHandling {
    def safe(person: Person): MultipleErrorsOr[SafePerson] = {
      import person._

      (
        safeFirstName(firstName),
        safeLastName(lastName),
        safeAge(age)
      ).mapN(SafePerson)
    }

    def safeFirstName(firstName: String): MultipleErrorsOr[String] =
      if (firstName.isEmpty)
        "firstName must not be empty".bad
      else
        firstName.good

    def safeLastName(lastName: String): MultipleErrorsOr[String] =
      if (lastName.isEmpty)
        "lastName must not be empty".bad
      else
        lastName.good

    def safeAge(age: Int): MultipleErrorsOr[Int] =
      if (age <= 0)
        "age must be positive".bad
      else
        age.good

  }

  println(BuisinessLogic.safe(Person("", "", -10)))
  println(BuisinessLogic.safe(Person("Bob", "Marley", 36)))

  println("-" * 100)
}
