package com.example.chimney.step5

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

object UseChimneyForIsomorphisms extends App {
  println("─" * 100)

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

  def mapToValidatedNec[Result](
      either: Either[String, Int],
      transform: Int => Result
    ): ValidatedNec[String, Result] =
    either.map(transform).toValidatedNec

  val flat: Person.Raw.Flat =
    Person
      .Raw
      .Flat(
        name = "Bob",
        age = "37",
        heightInCm = "170",
        phone = "+1-541-754-3010",
        cityName = "New York",
        cityZipCode = "10007",
        streetName = "5th Ave",
        streetNumber = "711"
      )

  val raw: Person.Raw.Nested =
    flat.toNested

  val person: ValidatedNec[String, Person] =
    Apply[ValidatedNec[String, *]].map2(
      mapToValidatedNec(intOrErrorMessage(raw.age, "age"), Person.Age),
      mapToValidatedNec(
        intOrErrorMessage(raw.heightInCm, "heightInCm"),
        Person.HeightInCm
      )
    )((age, heightInCm) =>
      raw
        .into[Person]
        .withFieldConst(_.age, age)
        .withFieldConst(_.heightInCm, heightInCm)
        .transform
    )

  println(flat)
  println(raw)
  
  println("─" * 100)
  println(person)

  println("─" * 100)
}
