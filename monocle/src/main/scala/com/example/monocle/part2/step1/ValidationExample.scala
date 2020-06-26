package com.example.monocle.part2.step1

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

object ValidationExample {

  def person(nested: Person.Raw.Nested): ValidatedNec[String, Person] =
    (
      intOrErrorMessage(nested.age, "age")
        .map(Person.Age)
        .toValidatedNec,
      intOrErrorMessage(nested.heightInCm, "heightInCm")
        .map(Person.HeightInCm)
        .toValidatedNec
    ).mapN(
      (age, heightInCm) =>
        nested
          .into[Person]
          .withFieldConst(_.age, age)
          .withFieldConst(_.heightInCm, heightInCm)
          .transform
    )

  def intOrErrorMessage(
      field: String,
      fieldName: => String
    ): Either[String, Int] =
    Either
      .fromOption(
        field.toIntOption,
        s"${Console.RED}$fieldName must be a valid integer, which $field was not.${Console.RESET}"
      )
      .filterOrElse(
        _ >= 0,
        s"${Console.RED}$fieldName must be >= 0, which $field was not.${Console.RESET}"
      )

}
